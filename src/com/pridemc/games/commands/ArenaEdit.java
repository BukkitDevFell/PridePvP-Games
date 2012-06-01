package com.pridemc.games.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class ArenaEdit {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(args.length == 1){
		
		if(Core.instance.getEditing().containsKey(player)){
			
	    	 sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			ChatColor.YELLOW + "Finished editing arena " + Core.instance.getEditing().get(player));
			
			Core.instance.getEditing().remove(player);
			
			}else{
				
		    sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
		    	ChatColor.RED + "You are not currently editing an arena. If you wish to do so, type " + ChatColor.YELLOW + "/arena edit <name>");
				
			}
		
		}else if(args.length == 2){
			
			String aname = args[1];
			
			if(Core.instance.getEditing().containsKey(player)){
				
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
				    ChatColor.RED + "You are already editing an arena! To quit editing this arena, type " + ChatColor.YELLOW + "/arena edit");
				
 			}else{
 				
 				Core.instance.getEditing().put(player, aname);
 				
 				 sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
 						ChatColor.YELLOW + "You are now editing the arena " + Core.instance.getEditing().get(player));
 				
 			}
		}
		
		return true;
	}
}
