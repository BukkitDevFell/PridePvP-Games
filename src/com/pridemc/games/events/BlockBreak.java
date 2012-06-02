package com.pridemc.games.events;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.pridemc.games.Core;

public class BlockBreak implements Listener{
	
	private Map<Location, Integer> broken = new HashMap<Location, Integer>();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		
		Block block = event.getBlock();
		
		if(Core.instance.getPlaying().containsKey(event.getPlayer())){
			
			if(Core.arenas.getInt(Core.instance.getPlaying().get(event.getPlayer()) + ".status code") >= 2){
				
				broken.put(block.getLocation(), block.getTypeId());
				
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
