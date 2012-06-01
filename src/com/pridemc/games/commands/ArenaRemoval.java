package com.pridemc.games.commands;

import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class ArenaRemoval implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			
			Player player = (Player) sender;
		
		if(args.length < 2){
			
			player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: /arena remove <name>");
			
		}else if(args.length == 2){
			
			String aname = args[1];
			
			if(Core.arenas.getKeys(false).contains(aname)){
			
			Core.arenas.set(aname, null);
			
			player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			ChatColor.YELLOW +	"Arena " + aname + " has been succesfully removed.");
			
			
			}else{
				
			sender.sendMessage(ChatColor.RED + "There is no arena called " + aname + ". Type /arena list to view a list of current arenas.");
				
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
