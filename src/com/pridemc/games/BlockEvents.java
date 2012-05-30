package com.pridemc.games;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvents implements Listener{
	
	@EventHandler
	public void onBlockPlace(final BlockPlaceEvent event){
		
		if(!event.getPlayer().hasPermission("pg.edit.blocks")){
		
		if(!Core.config.getList("GPlaceable blocks").contains(event.getBlock().getTypeId())){
				
			event.setCancelled(true);
				
	}else{
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.instance, new Runnable() {

			   public void run() {
				   
			Location loc = event.getBlock().getLocation();
			
			loc.getBlock().setTypeId(0);
				   
		    	}
			   
			}, 180L);
		}
	}	
}
	
	@EventHandler
	public void onBlockBreak(final BlockBreakEvent event){
		
		if(!event.getPlayer().hasPermission("pg.edit.blocks")){
		
		if(!Core.config.getList("GBreakable blocks").contains(event.getBlock().getTypeId())){
			
			event.setCancelled(true);
			
		}else{
			
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.instance, new Runnable() {

			public void run() {
					   
				Location loc = event.getBlock().getLocation();
				
				loc.getBlock().setType(event.getBlock().getType());
					   
			    	}
			
				}, 60L * Core.config.getInt("Default Approx Time"));
			}
		}
	}
}
