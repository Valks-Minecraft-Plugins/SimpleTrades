package me.valk.simpletrades.commands;

import me.valk.simpletrades.SimpleTrades;
import me.valk.simpletrades.configs.ConfigItem;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class CmdSimpleTrades implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("simpletrades")){
            if (args.length < 1){
                sender.sendMessage("You need to specify arguments for this command.");
                return true;
            }

            if (args[0].equalsIgnoreCase("create")) {
                Player player = (Player) sender;
                EntityEquipment equipment = player.getEquipment();
                ItemStack mainhand = equipment.getItemInMainHand();
                ItemStack offhand = equipment.getItemInOffHand();

                if (mainhand.getType() == Material.AIR || offhand.getType() == Material.AIR) {
                    sender.sendMessage("You need to be holding a item in your off and main hands to do this!");
                    return true;
                }
                
                String playerUUID = player.getUniqueId().toString();

                YamlConfiguration signsConfig = SimpleTrades.signsConfig;
                ConfigItem configItem = new ConfigItem(SimpleTrades.signsCM);
                
                if (!signsConfig.isConfigurationSection(playerUUID + ".signs")) {
                	signsConfig.createSection(playerUUID + ".signs");
                } else {
                	ConfigurationSection configSection = signsConfig.getConfigurationSection(playerUUID + ".signs");
                	for (String element : configSection.getKeys(false)) {
                		if (element.equals(args[1])) {
                			sender.sendMessage("A sign shop with this name already exists!");
                			return true;
                		}
                	}
                }
                
                String path = playerUUID + ".signs." + args[1] + ".";
            	configItem.set(path + "main", mainhand);
            	configItem.set(path + "off", offhand);

                SimpleTrades.signsCM.saveConfig();

                sender.sendMessage("Created! Now place any sign!");
            }
            return true;
        }
        return true;
    }
}
