package com.pridemc.games.events;

import com.pridemc.games.arena.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener{
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){

		Player player = event.getPlayer();
		ArenaManager.removePlayerFromArena(player);
	}
		
}
