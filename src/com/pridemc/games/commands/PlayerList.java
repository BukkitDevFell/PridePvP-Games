package com.pridemc.games.commands;

import com.pridemc.games.arena.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerList {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		


		if (sender instanceof Player && ArenaManager.isInArena(sender.getName())) {
			Arena arena = ArenaManager.getArenaPlayerIsIn(sender.getName());
			String msg = "Players in %s: %s";
			MessageUtil.sendMsg(sender, msg, arena.getName(), ArenaUtil.getPlayerDisplayNames(arena));
		} else {
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.YELLOW + "Arenas:");

			for(String arenaName : ArenaConfig.getArenaNames()){
				Arena arena = ArenaManager.getArena(arenaName);

				if (arena.getState().canJoin()) {
					String msg = ChatColor.AQUA + "%s" + ChatColor.YELLOW + " : " + ChatColor.GREEN + "OPEN";
					sender.sendMessage(String.format(msg, arena.getName()));
				} else {
					String msg = ChatColor.AQUA + "%s" + ChatColor.YELLOW + " : " + ChatColor.RED + "IN PROGRESS" + ChatColor.YELLOW + " : " + ChatColor.GOLD + "%d" + ChatColor.YELLOW + "/" + ChatColor.GOLD + "%d";
					sender.sendMessage(String.format(msg, arena.getName(), arena.getNumPlayers(), arena.getMaxNumPlayers()));
				}
			}
		}
		
		return true;
	}
}
