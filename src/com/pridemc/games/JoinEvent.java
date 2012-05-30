package com.pridemc.games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JoinEvent implements Listener{
	
	public HashMap<String, List<Player>> players = new HashMap<String, List<Player>>();
	
	List<Player> playerList = new ArrayList<Player>();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		
		if(event.getPlayer().getLocation().add(0, -2, 0).getBlock().getType().equals(Material.SIGN_POST)){
			
			Sign sign = (Sign) event.getPlayer().getLocation().add(0, -2, 0).getBlock().getState();
			
			if(sign.getLine(0).equalsIgnoreCase("PridePort"));
			
			String aname = sign.getLine(1);
			
			if(Core.arenas.getKeys(false).contains(aname)){
				
				if(!Core.arenas.getBoolean(aname + ".InProgress") && players.get(aname).size() <= Core.arenas.getInt(aname + ".Max players")){
				
				@SuppressWarnings("unchecked")
				List<Vector> spawnpoints = (List<Vector>) Core.arenas.getList(aname + ".spawnpoints");
				
				Vector loc = spawnpoints.get(new Random().nextInt(Core.arenas.getList(aname + ".spawnpoints").size()));
				
				event.getPlayer().teleport(loc.toLocation(Bukkit.getServer().getWorld(Core.arenas.getString(aname + ".world"))));
				
				if(players.containsKey(aname)){
					
					players.get(aname).add(event.getPlayer());
					
				}else{
					
				playerList.add(event.getPlayer());
				
				players.put(aname, playerList);
				
				playerList.remove(event.getPlayer());
				
				}
				
				}else if(Core.arenas.getBoolean(aname + ".InProgress")){
					
					event.getPlayer().sendMessage(ChatColor.RED + "This event is already in progress!");
					
				}
			}
		}
	}
}
