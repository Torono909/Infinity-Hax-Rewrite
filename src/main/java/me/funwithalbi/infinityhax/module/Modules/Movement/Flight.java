package me.funwithalbi.infinityhax.module.Modules.Movement;

import org.lwjgl.input.Keyboard;
import me.funwithalbi.infinityhax.module.Module;

public class Flight extends Module{
  public Flight() {
    super("Flight", KEYBOARD.KEY_G, Category.MOVEMENT);
  }
}
