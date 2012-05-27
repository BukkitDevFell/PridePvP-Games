package com.pridemc.Games;

import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener{
	
	ChatColor TEAL = ChatColor.AQUA;
	
	ChatColor GOLD = ChatColor.GOLD;
	
	ChatColor YELLOW = ChatColor.YELLOW;
	
	ChatColor RED = ChatColor.RED;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		
	Player player = event.getPlayer();
		
	if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
	
	if(event.getClickedBlock().getType().equals(Material.SIGN_POST)){
		
		System.out.println("1");
		
		Sign sign = (Sign) event.getClickedBlock().getState();

		if(sign.getLine(0).equalsIgnoreCase("[Pride Games]")){
		
		String arena = sign.getLine(1);
		
	if(Core.arenas.contains(arena)){	
		
		@SuppressWarnings("unchecked")
		List<Location> spawnpoint = (List<Location>) Core.arenas.getList(arena + ".spawnpoints");
		
	if(spawnpoint != null){
		
		Location position = spawnpoint.get(new Random().nextInt(Core.arenas.getList(arena + "spawnpoints").size()));
		
			event.getPlayer().teleport(position);
			
				}else{
					
				player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + RED + "There are no spawn points defined in this arena");
					
				}
	
			}else{
				
			player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + RED + "There is no arena called " + arena);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onSignPlace(SignChangeEvent event){
		
		if(event.getLine(0).equalsIgnoreCase("[Pride Games]")){
			
			String arena = event.getLine(1);
			
			if(Core.arenas.contains(arena) && arena.length() > 0){
			
			event.setLine(0, "[Pride Games]");
			
			event.getPlayer().sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + YELLOW + "Arena sign successfully created!");
			
			}else{
				
			event.getBlock().breakNaturally();
			
			event.getPlayer().sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + RED + "There is no arena called " + arena);
				
			}
		}
	}
}
