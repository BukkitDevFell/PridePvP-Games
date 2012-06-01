package com.pridemc.games.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class PlayerSpawn implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(Core.config.getVector("Spawn location") == null){
			
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.RED +
					
					"No spawn has been set! Contact an admin about this issue");
			
		}else{
			
			player.teleport(Core.config.getVector("Spawn location").toLocation(Bukkit.getServer().getWorld(Core.config.getString("Spawn world"))));
			
		}
		
		return true;
	}
}
