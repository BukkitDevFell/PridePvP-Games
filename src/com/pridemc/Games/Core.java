package com.pridemc.Games;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
	
	public static YamlConfiguration config;
	public static Core instance;
	public static YamlConfiguration arenas;
	
	public void onEnable(){
		
		getLogger().info("has been enabled");
		
		config = (YamlConfiguration) getConfig();
		
		instance = this;
		
		arenas = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "arenas.yml"));
		
		getConfig().options().copyDefaults(true);
		
		saveConfig();
		
		try {
			
			arenas.save(new File(getDataFolder(), "arenas.yml"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		getCommand("pridegames").setExecutor(new Commands());
		
		getCommand("arena").setExecutor(new AdminCommands());
		
		getServer().getPluginManager().registerEvents(new BlockListener(), this);
		
		getServer().getPluginManager().registerEvents(new RegionSelection(), this);
		
		getServer().getPluginManager().registerEvents(new SignListener(), this);
		
	}
	
	public void onDisable(){
		
		getLogger().info("has been disabled");
		
		saveConfig();
		
	}

}
