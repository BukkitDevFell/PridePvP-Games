package com.pridemc.games.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class PlayerShop {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(Core.config.getVector("Shop location") == null){
			
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.RED +
					
					"No shop has been set! Contact an admin about this issue");
			
		}else{
			
			player.teleport(Core.config.getVector("Shop location").toLocation(Bukkit.getServer().getWorld(Core.config.getString("Spawn world"))));
			
		}
		
		return true;
	}
}
