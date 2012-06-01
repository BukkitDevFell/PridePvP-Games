package com.pridemc.games.shop;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class CreateShop implements Listener{
	
	@EventHandler
	public void onShopCreate(SignChangeEvent event){
		
		if(event.getLine(0).equalsIgnoreCase("[PrideShop]")){
			
			if(event.getPlayer().hasPermission("pridegames.shop")){
				
				event.getPlayer().sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
						
							ChatColor.GREEN + "Shop sign created");
				
			}else{
				
				event.getBlock().breakNaturally();
				
				event.getPlayer().sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
						
							ChatColor.RED + "You don't have permission to place shop signs!");
				
			}
		}
	}
}
