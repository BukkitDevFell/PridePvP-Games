package com.pridemc.games;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin{
	
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
		
		getCommand("arena").setExecutor(new ArenaManagement());
		
		getCommand("pga").setExecutor(new AdminCommands());
		
		getServer().getPluginManager().registerEvents(new BlockEvents(), this);
		
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);

		try {

			arenas.save(new File(getDataFolder(), "arenas.yml"));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void onDisable(){
		
		getLogger().info("has been disabled");	
		
		saveConfig();
		
		try {
			
			arenas.save(new File(getDataFolder(), "arenas.yml"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
