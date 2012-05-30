package com.pridemc.games;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdminCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("pga")){
			
			if(args.length < 1){
				
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			ChatColor.YELLOW + "Please type" + ChatColor.GOLD + " /pg help" + ChatColor.YELLOW + " for a list of admin commands");
				
			}else if(args[0].equalsIgnoreCase("reload")){
				
				if(args.length >= 1){
					
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			ChatColor.YELLOW + "Reloading config files...");
			
			Core.instance.reloadConfig();
					
				}
			}
		}
		return true;
	}
}
