package com.pridemc.Games;

import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	ChatColor TEAL = ChatColor.AQUA;
	
	ChatColor GOLD = ChatColor.GOLD;
	
	ChatColor YELLOW = ChatColor.YELLOW;
	
	ChatColor RED = ChatColor.RED;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player player = (Player) sender;

		//Core command
		
		if(label.equalsIgnoreCase("pridegames") || label.equalsIgnoreCase("pg")){
			
			if(args.length == 0){
				
				player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + YELLOW + "Please type" 
				+ GOLD + " /pg help" + YELLOW + " for a list of commands");
			
				//Reloading files
				
			}else if(args[0].equalsIgnoreCase("reload") && args.length <= 1){
				
				player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + YELLOW + "Reloading config files...");
				
				Core.instance.reloadConfig();
				
				
				//Creating arenas
			
			
				//Deleting arenas
			}else if(args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("del")){
				
				String name = args[1];
				
				if(Core.arenas.contains(name)){
					
					Core.arenas.set(name, null);
					
					player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + YELLOW + "Arena " + name + " has been succesfully removed");
					
				}else{
					
					player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + RED + "There seems to be no arena called " + name);	
					
				}
			
				//Listing arenas
			}else if(args[0].equalsIgnoreCase("list")){
				
				//List<String> names = (List<String>) Core.arenas.getConfigurationSection("");
				
				player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + YELLOW + "Arenas: " + Core.arenas.getConfigurationSection("").getKeys(false));
				
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
