package me.funwithalbi.infinityhax.module;

import me.funwithalbi.infinityhax.*;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Jesus extends Module {
    public Jesus() {
        super("Jesus", Category.MOVEMENT, "Walk on water");
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity() == mc.player) {
            if (isEnabled() && mc.player.isInWater()) {
                mc.player.onGround = true; // Allow the player to walk on water
            }
        }
    }

    @Override
    public void onEnable() {
        // Subscribe to the LivingUpdateEvent when enabled
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        // Unsubscribe from the LivingUpdateEvent when disabled
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
