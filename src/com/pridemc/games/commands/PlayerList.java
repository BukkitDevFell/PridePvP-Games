package com.pridemc.games.commands;

import com.pridemc.games.Core;
import com.pridemc.games.arena.Arena;
import com.pridemc.games.arena.ArenaManager;
import com.pridemc.games.arena.ArenaUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerList {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if (ArenaManager.isInArena(player.getName())) {
			Arena arena = ArenaManager.getArenaPlayerIsIn(player.getName());
			String msg = ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.YELLOW + "Players in %s: %s";
			sender.sendMessage(String.format(msg, arena.getName(), ArenaUtil.getPlayerDisplayNames(arena)));
		} else {
			
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.YELLOW + "Arenas:");
			
			for(String arenaName : Core.arenas.getKeys(false)){
				Arena arena = ArenaManager.getArena(arenaName);
				List<String> playerDisplayNames = ArenaUtil.getPlayerDisplayNames(arena);
						
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
