package com.pridemc.Games;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		
		Player player = event.getPlayer();
		
		 List<Integer> blocks = (List<Integer>) Core.config.getIntegerList("Allowed Break Blocks");
		
		if(!blocks.contains(event.getBlock().getTypeId())){
		
			if(!player.hasPermission("rp.*")){
			
			event.setCancelled(true);
			
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		
		Player player = event.getPlayer();
		
		List<Integer> blocks = (List<Integer>) Core.config.getIntegerList("Allowed Place Blocks");
		
	}
}
