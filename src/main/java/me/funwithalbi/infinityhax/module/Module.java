package me.funwithalbi.infinityhax.module;

import me.funwithalbi.infinityhax.Infinityhax;
import me.funwithalbi.infinityhax.Category;
import me.funwithalbi.infinityhax.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Module {
    private String name;
    private String description;
    private Category category;
    private int bind;
    private boolean enabled;
    public final Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void enable() {
        this.setEnabled(true);
        this.onEnable();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void disable() {
        this.setEnabled(false);
        this.onDisable();
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public void toggle() {
        if (this.isEnabled()) {
            this.disable();
        } else {
            this.enable();
        }
    }

    public boolean nullCheck() {
        return this.mc.field_71439_g == null || this.mc.field_71441_e == null;
    }

    public void addSetting(Setting setting) {
        InfinityHax.settingManager.addSetting(setting);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getBind() {
        return this.bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
