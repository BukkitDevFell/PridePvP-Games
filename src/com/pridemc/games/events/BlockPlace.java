package com.pridemc.games.events;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.pridemc.games.Core;

public class BlockPlace implements Listener {
	
	private Map<Location, Integer> placed = new HashMap<Location, Integer>();
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		
		Block block = event.getBlock();
		
		if(Core.instance.getPlaying().containsKey(event.getPlayer())){
			
			if(Core.arenas.getInt(Core.instance.getPlaying().get(event.getPlayer()) + ".status code") >= 2){
				
				placed.put(block.getLocation(), 0);
				
			}else{
				
				if(!event.getPlayer().hasPermission("pridegames.admin")){
					
					event.setCancelled(true);
					
				}
			}
		}
		
		
		/*
		 * Make sure the players are in an arena and make sure the arena's match has started, else, deny
		 */
		
	}
}
