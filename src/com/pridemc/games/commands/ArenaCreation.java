package com.pridemc.games.commands;

import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.pridemc.games.Core;

public class ArenaCreation implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			
			Player player = (Player) sender;
		
		if(args.length < 2){
			
			player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: /arena create <name>");
			
		}else if(args.length == 2){
			
			String aname = args[1];
			
			if(!Core.arenas.getKeys(false).contains(aname)){
			
			Core.arenas.createSection(aname);
			
			Core.arenas.set(aname + ".max players", 15);
			
			Core.arenas.set(aname + ".playercount to start", 8);
			
			Core.arenas.createSection(aname + ".spawnpoint");
			
			Core.arenas.createSection(aname + ".world");
			
			Core.arenas.set(aname + ".status code", 0);
			
			Core.instance.getEditing().put(player, aname);
			
			player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			ChatColor.YELLOW +	"New arena " + aname + " succesfully created! You are now editing this arena as well. To stop editting, type" + ChatColor.GOLD + " /arena edit");
			
			
			}else{
				
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
