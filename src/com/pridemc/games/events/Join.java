package com.pridemc.games.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.pridemc.games.Core;

public class Join implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		
		if(Core.instance.getPlaying().containsKey(event.getPlayer())){
			
			event.getPlayer().teleport(Core.config.getVector("Spawn location").toLocation(Bukkit.getServer().getWorld(Core.config.getString("Spawn world"))));
			
		}
		
		/*
		 * Teleport player to Spawn
		 */
		
	}
}
