package com.pridemc.games.events;

import com.pridemc.games.arena.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class Teleportation implements Listener{
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		
		if(event.getCause().equals(TeleportCause.COMMAND)){
			Player player = event.getPlayer();
			player.getInventory().clear();
			if (ArenaManager.isInArena(player.getName())) {
				ArenaManager.cleanUpPlayer(player);
			}
		}
	}
}
