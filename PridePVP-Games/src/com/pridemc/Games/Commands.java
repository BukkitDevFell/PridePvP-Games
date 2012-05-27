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
		
		if(label.equalsIgnoreCase("pridegames") || label.equalsIgnoreCase("pg")){
			
			if(args.length == 0){
				
				player.sendMessage(GOLD + "[" + TEAL + "PridePvP Games" + GOLD + "] " + YELLOW + "Please type" 
				+ GOLD + " /pg help" + YELLOW + " for a list of commands");
				
			}else if(args[0].equalsIgnoreCase("reload") && args.length <= 1){
				
				player.sendMessage(GOLD + "[" + TEAL + "PridePvP Games" + GOLD + "] " + YELLOW + "Reloading config files...");
				
				Core.instance.reloadConfig();
				
			}else if(args[0].equalsIgnoreCase("create")){
				
				if(args.length > 1 && args.length < 3){
				
				String name = args[1];
				
				if(RegionSelection.max.containsKey(player)){
					
					if(RegionSelection.min.containsKey(player)){
						
						player.sendMessage(GOLD + "[" + TEAL + "PridePvP Games" + GOLD + "] " + YELLOW + "Arena " + name + " succesfully created!");
						
						Core.arenas.createSection(name);
						
						Core.arenas.set(name + ".max", RegionSelection.max.get(player).toString());
						
						Core.arenas.set(name + ".min", RegionSelection.min.get(player).toString());
						
					}else{
							
						player.sendMessage(GOLD + "[" + TEAL + "PridePvP Games" + GOLD + "] " + RED + "You haven't properly selected a region");
							
						}
					
					}else{
						
						player.sendMessage(GOLD + "[" + TEAL + "PridePvP Games" + GOLD + "] " + RED + "You haven't properly selected a region");
						
					}
				}
			
			}else if(args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("del")){
				
				String name = args[1];
				
				if(Core.arenas.contains(name)){
					
					Core.arenas.set(name, null);
					
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
