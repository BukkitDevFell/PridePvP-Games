package com.pridemc.games.events;

import com.pridemc.games.arena.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){

		Player player = event.getPlayer();
		if(ArenaManager.isInArena(player.getName())){

			player.teleport(ArenaManager.getGlobalSpawnPoint());
			
		}
		
		/*
		 * Teleport player to Spawn
		 */


	}
}
