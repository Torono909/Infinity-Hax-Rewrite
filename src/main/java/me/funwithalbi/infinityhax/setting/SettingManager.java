package me.funwithalbi.infinityhax.setting;

import java.util.ArrayList;
import me.funwithalbi.infinityhax.Infinityhax;
import me.funwithalbi.infinityhax.module.Module;
import me.funwithalbi.infinityhax.setting.Setting;

public class SettingManager {
    private final ArrayList<Setting> settings = new ArrayList();

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public ArrayList<Setting> getSettings(Module module) {
        ArrayList<Setting> sets = new ArrayList<Setting>();
        for (Setting setting : this.settings) {
            if (!setting.getModule().equals(module)) continue;
            sets.add(setting);
        }
        return sets;
    }

    public Setting getSettingEasy(String moduleName, int index) {
        return this.getSettings(Infinityhax.moduleManager.getModule(moduleName)).get(index);
    }

    public Setting getSetting(String moduleName, String name) {
        for (Setting setting : this.settings) {
            if (!setting.getModule().getName().equalsIgnoreCase(moduleName) || !setting.getName().equalsIgnoreCase(name)) continue;
            return setting;
        }
        return null;
    }

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }
}
