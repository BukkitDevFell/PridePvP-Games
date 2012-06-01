package com.pridemc.games.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class PlayerSpawnSet {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		Core.config.set("Spawn location", player.getLocation().toVector());
		
		Core.config.set("Spawn world", player.getWorld().getName());
		
		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
				ChatColor.YELLOW + "The PrideGames spawn has been set to your location");
		
		Core.instance.saveConfig();
		
		return true;
	}
}
