package com.pridemc.games.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class PlayerList {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		List<String> playerlist = new ArrayList<String>();
		
		if(Core.instance.getPlaying().containsKey(player)){
			
			for(Player players : Core.instance.getPlaying().keySet()){
				
				if(Core.instance.getPlaying().get(players).equals(Core.instance.getPlaying().get(player))){
			
					playerlist.add(players.getDisplayName());
				}
			}
			
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
					ChatColor.YELLOW + "Players in " + Core.instance.getPlaying().get(player) + ": " + playerlist);
			
			
		playerlist.clear();
			
		}else{
			
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.YELLOW + "Arenas:");
			
			for(String anames : Core.arenas.getKeys(false)){
				
				for(Player players : Core.instance.getPlaying().keySet()){
					
					if(Core.instance.getPlaying().get(players).equals(anames)){
				
						playerlist.add(players.getDisplayName());
						
					}
				}
						
				if(Core.arenas.getInt(anames + ".status code") == 0){
					
					sender.sendMessage(ChatColor.AQUA + anames + ChatColor.YELLOW + " : " + ChatColor.GREEN + "OPEN");
					
						playerlist.clear();
					
				}else{
					
					sender.sendMessage(ChatColor.AQUA + anames + ChatColor.YELLOW + " : " + ChatColor.RED + "IN PROGRESS" + ChatColor.YELLOW + " : " 
							
							+ ChatColor.GOLD + playerlist.size() + ChatColor.YELLOW + "/" + ChatColor.GOLD + Core.arenas.getInt(anames + ".max players"));
					
					playerlist.clear();
					
				}
			}
		}
		
		return true;
	}
}
