package me.funwithalbi.infinityhax.module;

import me.funwithalbi.infinityhax.*;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoCrystal extends Module {
    private boolean shouldSwitch = true;
    private int crystalSlot = -1;

    public AutoCrystal() {
        super("AutoCrystal", Category.COMBAT);
        setDescription("Automatically places and detonates crystals");
        addSetting(new Setting("Crystal Range", this, 4, 1, 6)); // Setting for crystal placement range
        addSetting(new Setting("Silent Switch", this, false)); // Setting for silent switch
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {
        if (isEnabled() && mc.player != null) {
            if (getSetting("Silent Switch").getBooleanValue()) {
                if (shouldSwitch) {
                    crystalSlot = findCrystalSlot();
                    shouldSwitch = false;
                }

                if (crystalSlot != -1) {
                    mc.player.inventory.currentItem = crystalSlot;
                    mc.playerController.updateController();
                }
            }

            for (EntityEnderCrystal crystal : mc.world.loadedEntityList.stream()
                    .filter(entity -> entity instanceof EntityEnderCrystal)
                    .map(entity -> (EntityEnderCrystal) entity)
                    .toArray(EntityEnderCrystal[]::new)) {
                if (mc.player.getDistance(crystal) <= getSetting("Crystal Range").getIntegerValue() && mc.player.canEntityBeSeen(crystal)) {
                    mc.playerController.attackEntity(mc.player, crystal);
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }

    private int findCrystalSlot() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.inventory.getStackInSlot(i);
            if (stack.getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        crystalSlot = -1;
    }

    @Override
    public void toggle() {
        super.toggle();
        shouldSwitch = true;
    }
}
