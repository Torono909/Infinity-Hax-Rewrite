package me.funwithalbi.infinityhax.module;

import java.util.ArrayList;
import me.funwithalbi.infinityhax.module.modules.movement.autosprint;
import me.funwithalbi.infinityhax.module.modules.movement.Flight;

public class ModuleManager{
  
  private static ArrayList<Module> modules;
  
  public ModuleManager() {
    modules = new ArrayList<Module>();
    //COMBAT
    
    //EXPLOIT
    
    //PLAYER
    
    //MOVEMENT
    newModule(new AutoSprint());
    //RENDER
    
    //MISC
    
  }
  public static void newModule(Module m) {
    modules.add(m);
  }
  public static ArrayList<Module> getModules() {
    return modules;
  }
  public static void onUpdate() {
    for(Module m : modules) {
      m.onUpdate();
    }
  }
  public static void onRender() {
    for(Module m : modules) {
      m.onRender();
    }
  }
  public static void onKey(int k) {
    for(Module m : modules) {
      if (m.getKey == k)
      m.toggle();
    }
  }
}
