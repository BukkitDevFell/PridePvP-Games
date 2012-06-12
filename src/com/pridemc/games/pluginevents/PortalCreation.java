package com.pridemc.games.pluginevents;

import com.pridemc.games.arena.ArenaConfig;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class PortalCreation implements Listener{
	
	@EventHandler
	public void onPortalCreate(SignChangeEvent event){

		if(event.getLine(0).equalsIgnoreCase("[PridePort]")){
			
			if(event.getPlayer().hasPermission("pridegames.admin")){
				
				String aname = event.getLine(1);
				
				if(ArenaConfig.getArenaNames().contains(aname)){
					
					event.getPlayer().sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
					
							ChatColor.GREEN + "Portal to " + aname + " has been created!");
					
				}else{
					
					event.getBlock().breakNaturally();
					
					event.getPlayer().sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
					
							ChatColor.RED + "There is no arena called " + aname);
				}
				
			}else{
				
				event.getBlock().breakNaturally();
				
				event.getPlayer().sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
						
							ChatColor.RED + "You don't have permission to place portal signs!");
				
			}
		}
	}
}
