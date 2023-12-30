package me.funwithalbi.infinityhax.module;

import me.funwithalbi.infinityhax.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoCrystalModule extends Module {
    private int placeRange = 5;
    private int delayBetweenPlacements = 0;
    private boolean onlyVisible = true;
    private boolean smartDelay = true;
    private boolean switchEnabled = true;
    private boolean killerEnabled = false;

    private int ticksToPlace = 0;
    private int ticksSinceLastPlace = 0;

    public AutoCrystalModule() {
        super("AutoCrystal", Category.COMBAT);
        setDescription("Automatically places and detonates crystals");
        addSettings();
        initialize();
    }

    private void addSettings() {
        addSetting(new Setting("Place Range", this, placeRange, 1, 15));
        addSetting(new Setting("Delay Between Placements", this, delayBetweenPlacements, 0, 5));
        addSetting(new Setting("Only Visible", this, onlyVisible));
        addSetting(new Setting("Smart Delay", this, smartDelay));
        addSetting(new Setting("Switch Enabled", this, switchEnabled));
        addSetting(new Setting("Killer", this, killerEnabled));
    }

    private void initialize() {
        registerEvents();
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {
        if (!isEnabled() || mc.player == null)
            return;

        if (ticksToPlace > 0) {
            ticksToPlace--;
            return;
        }

        EntityEnderCrystal crystal = findCrystal();
        if (crystal != null) {
            if (killerEnabled) {
                placeCrystal(crystal);
            } else {
                if (switchEnabled) {
                    predictPlayerMovement(crystal);
                } else {
                    placeCrystal(crystal);
                }
            }
            
            if (smartDelay)
                ticksToPlace = calculateSmartDelay();
            else
                ticksToPlace = delayBetweenPlacements;
        }
    }

    private EntityEnderCrystal findCrystal() {
        double maxRangeSq = Math.pow(placeRange, 2);
        EntityEnderCrystal targetCrystal = null;
        double closestDistSq = Double.MAX_VALUE;

        for (Entity entity : mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal) {
                double distSq = mc.player.getDistanceSq(entity);
                boolean isVisible = onlyVisible ? mc.player.canEntityBeSeen(entity) : true;

                if (distSq <= maxRangeSq && distSq < closestDistSq && isVisible) {
                    closestDistSq = distSq;
                    targetCrystal = (EntityEnderCrystal) entity;
                }
            }
        }
        return targetCrystal;
    }

    private void placeCrystal(EntityEnderCrystal crystal) {
        BlockPos blockPos = crystal.getPosition();
        EnumFacing enumFacing = EnumFacing.UP;

        mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, EnumHand.MAIN_HAND, 0, 0, 0));
        mc.player.swingArm(EnumHand.MAIN_HAND);
        ticksSinceLastPlace = 0;
    }

    private void predictPlayerMovement(EntityEnderCrystal crystal) {
        if (mc.player != null && mc.player.getDistanceSq(crystal) < 25) {
            BlockPos predictedPos = new BlockPos(
                mc.player.posX + mc.player.motionX * 2,
                mc.player.posY,
                mc.player.posZ + mc.player.motionZ * 2
            );
            placeCrystal(crystal, predictedPos);
        } else {
            placeCrystal(crystal);
        }
    }

    private void placeCrystal(EntityEnderCrystal crystal, BlockPos position) {
        mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(position, EnumFacing.UP, EnumHand.MAIN_HAND, 0, 0, 0));
        mc.player.swingArm(EnumHand.MAIN_HAND);
        ticksSinceLastPlace = 0;
    }

    private int calculateSmartDelay() {
        if (ticksSinceLastPlace < delayBetweenPlacements)
            return delayBetweenPlacements - ticksSinceLastPlace;
        else
            return 0;
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @Override
    public void onSettingChange(Setting setting) {
        super.onSettingChange(setting);

        if (setting.getName().equals("Place Range")) {
            placeRange = setting.getIntegerValue();
        } else if (setting.getName().equals("Delay Between Placements")) {
            delayBetweenPlacements = setting.getIntegerValue();
        } else if (setting.getName().equals("Only Visible")) {
            onlyVisible = setting.getBooleanValue();
        } else if (setting.getName().equals("Smart Delay")) {
            smartDelay = setting.getBooleanValue();
        } else if (setting.getName().equals("Switch Enabled")) {
            switchEnabled = setting.getBooleanValue();
        } else if (setting.getName().equals("Killer")) {
            killerEnabled = setting.getBooleanValue();
        }
    }
}
