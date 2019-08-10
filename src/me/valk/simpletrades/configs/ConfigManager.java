package me.valk.simpletrades.configs;

import me.valk.simpletrades.SimpleTrades;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private final File file;
    private YamlConfiguration config;

    public ConfigManager(String name) {
        file = new File(SimpleTrades.pluginFolder, name + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setDefault(String path, Object value) {
        if (!config.isSet(path)) {
            config.set(path, value);
        }
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}
