package com.pridemc.games.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class ArenaTp {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;

		if(args.length < 2){
			
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			
					ChatColor.RED + "Please specify an arena to teleport to!");

		}else{
			
			String aname = args[1];
			
			if(Core.arenas.getVector(aname + ".spawnpoint") == null){
				
				sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
						
					ChatColor.RED + "No arena with the name " + aname + " or this arena has no spawn point");
				
			}else{
				
				player.teleport(Core.arenas.getVector(aname + ".spawnpoint").toLocation(Bukkit.getServer().getWorld(Core.arenas.getString(aname + ".world"))));
				
				sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
						
					ChatColor.YELLOW + "You have been teleported to the spawn of " + aname);
				
			}
		}
		
		return true;
	}
}
