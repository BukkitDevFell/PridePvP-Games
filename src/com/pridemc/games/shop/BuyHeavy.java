package com.pridemc.games.shop;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuyHeavy implements Listener{
	
	@EventHandler
	public void HeavyClassBuy(PlayerInteractEvent event){
		
		if(event.getClickedBlock().getState() instanceof Sign){
			
			Sign sign = (Sign) event.getClickedBlock().getState();
			
			if(sign.getLine(0).equalsIgnoreCase("[PrideShop]")){
				
				if(sign.getLine(1).equalsIgnoreCase("Class")){
					
					if(sign.getLine(2).equalsIgnoreCase("Heavy")){
						
						if(sign.getLine(3).equalsIgnoreCase("5")){
							
							
						}
					}
				}
			}
		}
	}
}
