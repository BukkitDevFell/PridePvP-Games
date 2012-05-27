package com.pridemc.Games;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RegionSelection implements Listener{
	
	public static Map<Player, Location> max = new HashMap<Player, Location>();

	public static Map<Player, Location> min = new HashMap<Player, Location>();	
	
	ChatColor TEAL = ChatColor.AQUA;
	
	ChatColor GOLD = ChatColor.GOLD;
	
	ChatColor YELLOW = ChatColor.YELLOW;
	
	ChatColor RED = ChatColor.RED;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		
		if(event.getPlayer().hasPermission("pridegames.create")){
			
			if(event.getPlayer().getItemInHand().getType().equals(Material.BLAZE_ROD)){
				
				if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
					
					max.put(event.getPlayer(), event.getClickedBlock().getLocation());
					
					player.sendMessage(GOLD + "[" + TEAL + "PridePvP Games" + GOLD + "] " + YELLOW + "Position 1 selected");
					
					event.setCancelled(true);
					
				} else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
					
					min.put(event.getPlayer(), event.getClickedBlock().getLocation());
					
					event.setCancelled(true);
					
					player.sendMessage(GOLD + "[" + TEAL + "PridePvP Games" + GOLD + "] " + YELLOW + "Position 2 selected");
					
				}
			}
		}
	}
}

