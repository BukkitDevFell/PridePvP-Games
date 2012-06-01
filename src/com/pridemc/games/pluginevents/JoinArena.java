package com.pridemc.games.pluginevents;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.pridemc.games.Core;

public class JoinArena implements Listener{
	
	private Map<String, Set<Player>> players = new HashMap<String, Set<Player>>();
	
	Set<Player> playerset = new HashSet<Player>();
	
	@EventHandler
	public void onPortalStep(PlayerMoveEvent event){
		
		Player newplayer = event.getPlayer();
		
		for(Integer i = 0; i < 3; i++){
			
			if(newplayer.getLocation().add(0, -i, 0).getBlock().getType().equals(Material.SIGN_POST)){
				
				Sign sign = (Sign) newplayer.getLocation().add(0, -i, 0).getBlock().getState();
				
				if(sign.getLine(0).equalsIgnoreCase("[PridePort]")){
				
					String aname = sign.getLine(1);
				
						if(Core.arenas.getKeys(false).contains(aname) && Core.arenas.getInt(aname + ".status code") == 0){
							
							newplayer.teleport(Core.arenas.getVector(aname + ".spawnpoint").toLocation(Bukkit.getServer().getWorld(Core.arenas.getString(aname + ".world"))));
							
							Core.instance.getPlaying().put(newplayer, aname);
							
							if(players.get(aname) == null){
								
								playerset.add(newplayer);
								
								players.put(aname, playerset);
								
							}else{
								
								for(Player tributes : players.get(aname)){
									
									tributes.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
									
									ChatColor.YELLOW + newplayer.getDisplayName() + " has joined the game! (" + players.get(aname).size() + "/" + Core.arenas.getInt(aname + ".max players") + ")");
									
								}if(players.get(aname).size() == (Core.arenas.getInt(aname + ".playercount to start"))){
									
									
									
								}
							}
							
						
						}else if(Core.arenas.getInt(aname + ".status code") > 0){
							
							newplayer.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
							
									ChatColor.RED + "This arena is currently in progress! To see other arenas and their status, type " + ChatColor.YELLOW + "/pg list");
							
					}
				}
			}
		}
	}
}
