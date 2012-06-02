package com.pridemc.games.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.pridemc.games.Core;
import com.pridemc.games.pluginevents.JoinArena;

public class Teleportation implements Listener{
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		
	if(event.getCause().equals(TeleportCause.COMMAND)){
				
				event.getPlayer().getInventory().clear();
				
				if(Core.instance.getPlaying().containsKey(event.getPlayer())){
				
					Core.instance.getPlaying().remove(event.getPlayer());
					
			/*
			 * If the location is the spawn or something, remove them from the playerList if they're in-games
			 * 
			 * Prevent the command /pg spawn while in the arena
			 */
				}
		}
	}
}
