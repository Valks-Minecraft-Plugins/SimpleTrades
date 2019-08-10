package me.valk.simpletrades.listeners;

import me.valk.simpletrades.SimpleTrades;
import me.valk.simpletrades.configs.ConfigItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ListenerSign implements Listener {
    @EventHandler
    private void signEvent(SignChangeEvent e){
        YamlConfiguration signsConfig = SimpleTrades.signsConfig;
        ConfigItem configItem = new ConfigItem(SimpleTrades.signsCM);

        ConfigurationSection configurationSection = signsConfig.getConfigurationSection("signs");
        for (String uuid : configurationSection.getKeys(false)){
            if (uuid.equals(e.getPlayer().getUniqueId().toString())) {
            	ItemStack main = configItem.get("signs." + uuid + ".mainhand");
            	ItemStack off = configItem.get("signs." + uuid + ".offhand");
            	
            	ItemMeta mainIM = main.getItemMeta();
            	ItemMeta offIM = off.getItemMeta();
            	
            	e.setLine(0, "[SimpleTrades]");
            	e.setLine(1, mainIM.getDisplayName() + " x " + main.getAmount());
            	e.setLine(2, "<->");
            	e.setLine(3, offIM.getDisplayName() + " x " + off.getAmount());
            	
            	
            }
        }
    }
}
