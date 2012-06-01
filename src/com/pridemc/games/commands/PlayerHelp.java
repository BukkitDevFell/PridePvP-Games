package com.pridemc.games.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlayerHelp implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
				ChatColor.YELLOW + "Type /pg + [arg].");
		
		/*
		 * Add player help messages here
		 */
		
		
		
		return true;
	}
}
