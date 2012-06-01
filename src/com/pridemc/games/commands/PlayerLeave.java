package com.pridemc.games.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class PlayerLeave {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(Core.instance.getPlaying().containsKey(player)){
			
			Core.instance.getPlaying().remove(player);
			
			player.teleport(Core.config.getVector("Spawn location").toLocation(Bukkit.getServer().getWorld(Core.config.getString("Spawn world"))));
			
		}else{
			
			player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			
					ChatColor.RED + "You are not currently in an arena, and therefore cannot leave one.");
			
			}
		
		return true;
	}
}
