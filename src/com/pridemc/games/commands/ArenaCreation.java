package com.pridemc.games.commands;

import com.pridemc.games.Core;
import com.pridemc.games.arena.Arena;
import com.pridemc.games.arena.ArenaConfig;
import com.pridemc.games.arena.ArenaManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class ArenaCreation implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			
			Player player = (Player) sender;
		
		if(args.length < 2){
			
			player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: /arena create <name>");
			
		}else if(args.length == 2){
			
			String aname = args[1];
			
			if (!ArenaConfig.getArenaNames().contains(aname)) {

				ArenaManager.addArena(new Arena(aname));
			
				Core.instance.getEditing().put(player, aname);

				player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " +
				ChatColor.YELLOW +	"New arena " + aname + " succesfully created! You are now editing this arena as well. To stop editting, type" + ChatColor.GOLD + " /arena edit");

			} else {
				
				sender.sendMessage(ChatColor.RED + "There is already an arena called " + aname + ". If you'd like to remove this arena type /arena remove " + aname + ".");
				
			}
		}
		
		try {

			Core.arenas.save(new File(Core.instance.getDataFolder(), "arenas.yml"));

		} catch (IOException e) {

			e.printStackTrace();
			
			}
		}
		return true;
	}
}
