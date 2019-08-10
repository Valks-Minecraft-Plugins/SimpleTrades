package me.valk.simpletrades.configs;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigResource {
	public YamlConfiguration config;

	ConfigResource(ConfigManager cm) {
		this.config = cm.getConfig();
	}

	public String getPathParent(String path) {
		String[] items = path.split("\\.");
		StringBuilder newPath = new StringBuilder();
		int uncutElements = items.length - 1;
		for (int i = 0; i < uncutElements; i++) {
			newPath.append(items[i]).append(i == uncutElements - 1 ? "" : ".");
		}
		return newPath.toString();
	}

	void setConfigResource(String path) {
		config.set(path, null);
	}
}
