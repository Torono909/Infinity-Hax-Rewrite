package me.funwithalbi.infinityhax.module;

import net.minecraft.client.Minecraft;

public class Module {
  /* Torono909/Google_was_my_idea is a legend */
  
  protected Minecraft mc = minecraft.getMinecraft();
  private String name;
  private int key;
  private boolean toggled;
  private Category category;
  
  public Module(String nm, int k, Category c) {
    name = nm;
    key = k;
    category = c;
    toggled = false;
  }
  
  public getMc() {
    return mc;
  }
  public void setMc(Minecraft mc){
    this.mc = mc;
  }
  public String getName() {
    return name;
  }
  public void setName(String name){
    this.name = name;
  }
  public int getKey() {
  return key;
  }
  public void setKey(int key) {
    this.key = key;
  }
  public Category category() {
  return category;
  }
  public void category(){
    this.category = category;
  }
}
