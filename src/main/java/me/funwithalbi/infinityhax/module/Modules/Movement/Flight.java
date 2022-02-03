package me.funwithalbi.infinityhax.module.Modules.Movement;

import net.minecraft.*
import me.funwithalbi.infinityhax.module.Category;
import me.funwithalbi.infinityhax.module.Module;
import org.lwjgl.input.Keyboard;

public class Flight extends Module{
  public Flight() {
    super("Flight", KEYBOARD.KEY_G, Category.MOVEMENT);
  }
  public static float ChangedSpeed = 0.1f;
  public void onUpdate(){
    if(this.isToggled()){
      mc.thePlayer.capabilites.isFlying = true;
      
      if(mc.gameSettings.keyBindJump.isPressed()){
        mc.thePlayer.motionY += 0.2;
      }
      if(mc.gameSettings.keyBindSneak.isPressed()){
        mc.thePlayer.motionY -= 0.2;
      }
      if(mc.gameSettings.keyBindForward.isPressed()){
        mc.thePlayer.capabilites.setFlySpeed(ChangedSpeed)
      }
    }
  }
}
