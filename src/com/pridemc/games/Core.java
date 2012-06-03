package com.pridemc.games;

import ca.xshade.bukkit.util.TaskInjector;
import com.pridemc.games.arena.ArenaConfig;
import com.pridemc.games.arena.ArenaManager;
import com.pridemc.games.commands.ArenaCommandHandler;
import com.pridemc.games.commands.PlayerCommandHandler;
import com.pridemc.games.events.*;
import com.pridemc.games.pluginevents.JoinArena;
import com.pridemc.games.pluginevents.PortalCreation;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Core extends JavaPlugin {
	
	//HashMaps----------------------------------------------------------
	
	private Map<Player, String> editing = new HashMap<Player, String>();
	
	public Map<Player, String> getEditing() {
        
        return editing;

  	}
	
	//---------------------------------------------------------------------
	public static YamlConfiguration arenas;
	
	public static YamlConfiguration config;
	
	public static Core instance;
	
	public void onEnable(){
		TaskInjector.newInstance(this);

		config = (YamlConfiguration) getConfig();
		
		instance = this;
		
		arenas = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "arenas.yml"));
		ArenaConfig.loadArenas();
		
		//getConfig().options().copyDefaults(true);
		
		saveConfig();
		
		getLogger().info("has been enabled");
		
		getCommand("arena").setExecutor(new ArenaCommandHandler());
		
		getCommand("pg").setExecutor(new PlayerCommandHandler());
		
		getServer().getPluginManager().registerEvents(new JoinArena(), this);
		
		getServer().getPluginManager().registerEvents(new PortalCreation(), this);
		
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		
		getServer().getPluginManager().registerEvents(new BlockPlace(), this);

		getServer().getPluginManager().registerEvents(new Explosions(), this);
		
		getServer().getPluginManager().registerEvents(new Join(), this);
		
		getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
		
		getServer().getPluginManager().registerEvents(new Quit(), this);
		
		getServer().getPluginManager().registerEvents(new Teleportation(), this);
		
		try {

			arenas.save(new File(getDataFolder(), "arenas.yml"));

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	public void onDisable(){

		TaskInjector.cancelAll();

		ArenaManager.cleanupAllArenas();
		
		getLogger().info("is disabled");
		
		saveConfig();
		
		try {

			arenas.save(new File(getDataFolder(), "arenas.yml"));

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
}
