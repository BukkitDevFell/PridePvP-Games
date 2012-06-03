package com.pridemc.games.events;

import com.pridemc.games.Core;
import com.pridemc.games.arena.ArenaManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		
		if(Core.instance.getPlaying().containsKey(event.getPlayer())){

			event.getPlayer().teleport(ArenaManager.getGlobalSpawnPoint());
			
		}
		
		/*
		 * Teleport player to Spawn
		 */


	}
}
