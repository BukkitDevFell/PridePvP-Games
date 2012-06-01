package com.pridemc.games.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class PlayerShopSet {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		Core.config.set("Shop location", player.getLocation().toVector());
		
		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
				ChatColor.YELLOW + "The PrideGames shop has been set to your location");
		
		Core.instance.saveConfig();
		
		return true;
	}
}
