package com.pridemc.Games;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {

	ChatColor TEAL = ChatColor.AQUA;
	
	ChatColor GOLD = ChatColor.GOLD;
	
	ChatColor YELLOW = ChatColor.YELLOW;
	
	ChatColor RED = ChatColor.RED;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player player = (Player) sender;
		
		if(label.equalsIgnoreCase("arena")){
			
			if(args[0].equalsIgnoreCase("create")){
			
			if(args.length > 1 && args.length < 3){
			
			String name = args[1];
			
			if(RegionSelection.max.containsKey(player)){
				
				if(RegionSelection.min.containsKey(player)){
					
					player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + YELLOW + "Arena " + name + " succesfully created!");
					
					Core.arenas.createSection(name);
					
					Core.arenas.set(name + ".max", RegionSelection.max.get(player).getX() + "," + RegionSelection.max.get(player).getY()
							+ "," + RegionSelection.max.get(player).getZ());
					
					Core.arenas.set(name + ".min", RegionSelection.min.get(player).getX() + "," + RegionSelection.min.get(player).getY()
							+ "," + RegionSelection.min.get(player).getZ());
					
					Core.arenas.set("Breakable Blocks", Core.config.getList("Global Breakable Blocks"));
					
					Core.arenas.set("Placeable Blocks", Core.config.getList("Global Placeable Blocks"));
					
				}else{
						
					player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + RED + "You haven't properly selected a region");
						
					}
				
				}else{
					
					player.sendMessage(GOLD + "[" + TEAL + "Pride Games" + GOLD + "] " + RED + "You haven't properly selected a region");
					
					}
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
