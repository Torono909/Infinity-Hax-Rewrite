package me.funwithalbi.infinityhax.module.modules.movement;

import me.funwithalbi.infinityhax.module.Category;
import me.funwithalbi.infinityhax.module.Module;
import org.lwjgl.input.keyboard;

public class AutoSprint extends Module{
  public AutoSprint() {
    super("AutoSprint", Keyboard.KEY_O, Category.MOVEMENT);
  }
  public void onEnable() {
    if(this.isToggled()) {
      mc.thePlayer.setSprinting(true);
      super.onEnable();
    }
  }
  public void onDisable() {
    mc.thePlayer.setSprinting(false);
      super.onDisable();
  }
}
