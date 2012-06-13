package com.pridemc.games.commands;

import com.pridemc.games.arena.ArenaManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerLeave {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;

		if (ArenaManager.isInArena(player.getName())) {

			ArenaManager.cleanUpPlayer(player);
			
			player.teleport(ArenaManager.getGlobalSpawnPoint());
			
		}else{
			
			player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			
					ChatColor.RED + "You are not currently in an arena, and therefore cannot leave one.");
			
			}
		
		return true;
	}
}
