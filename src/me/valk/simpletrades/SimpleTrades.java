package me.valk.simpletrades;

import me.valk.simpletrades.commands.CmdSimpleTrades;
import me.valk.simpletrades.configs.ConfigManager;
import me.valk.simpletrades.listeners.ListenerSign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SimpleTrades extends JavaPlugin {
    public static File pluginFolder;
    public static ConfigManager signsCM;
    public static YamlConfiguration signsConfig;

    @Override
    public void onEnable(){
        pluginFolder = getDataFolder();
        registerListeners(getServer().getPluginManager());
        registerCommands();
        registerConfigs();
    }

    private void registerListeners(PluginManager pm){
        pm.registerEvents(new ListenerSign(), this);
    }

    private void registerCommands(){
        getCommand("simpletrades").setExecutor(new CmdSimpleTrades());
    }

    private void registerConfigs(){
        registerConfigSigns();
    }

    private void registerConfigSigns(){
        signsCM = new ConfigManager("signs");
        signsConfig = signsCM.getConfig();
        signsCM.saveConfig();
    }
}
