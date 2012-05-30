package com.pridemc.games;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ArenaManagement implements CommandExecutor{

	public static Map<Player, String> editing = new HashMap<Player, String>();
	
	ArrayList<Vector> spawnpoints = new ArrayList<Vector>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
			if(label.equalsIgnoreCase("arena")){
				
				if(args.length < 1){
					
					sender.sendMessage(ChatColor.RED + "Type /pga help for admin commands");
				
					//Create arena
			}else if(args[0].equalsIgnoreCase("create")){
				
				if(args.length < 2){
					
					sender.sendMessage(ChatColor.RED + "Please specify a name for this arena!");
					
				}else if(args.length == 2){
					
					String aname = args[1];
					
					if(!Core.arenas.contains(aname)){
					
					Core.arenas.createSection(aname);
					
					Core.arenas.createSection(aname + ".players");
					
					Core.arenas.createSection(aname + ".spawnpoints");
					
					//Core.arenas.set("Approx Time", Core.config.getInt("Default Approx Time"));
					
					//Core.arenas.set(aname + ".Placeable blocks", Core.config.getList("GPlaceable blocks"));
					
					//Core.arenas.set(aname + ".Breakable blocks", Core.config.getList("GBreakable blocks"));
					
					Core.arenas.set(aname + ".Max players", 5);
					
					sender.sendMessage(ChatColor.GREEN + "Arena " + aname + " has been successfully created");
					
					}else{
						
						sender.sendMessage(ChatColor.RED + aname + " has already been created!");
						
					}
				}				
			
				//Editing the arena
			}else if(args[0].equalsIgnoreCase("edit")){
				
				if(args.length < 2){
					
					sender.sendMessage(ChatColor.RED + "Please specify the name of the arena you want to edit");
					
				}else if(args.length == 2){
				
					String aname = args[1];
					
					if(Core.arenas.getKeys(false).contains(aname)){
						
						if(!editing.containsKey(player)){
						
						sender.sendMessage(ChatColor.AQUA + "You are now editing the arena " + aname);
						
						editing.put(player, aname);
						
						}else{
							
							editing.remove(player);
							
							sender.sendMessage(ChatColor.AQUA + "Finished editing " + aname);
							
						}
						
					}else{
						
						sender.sendMessage(ChatColor.RED + "There is no arena called " + aname);
					}
				}
			
				//Placing spawns
			}else if(args[0].equalsIgnoreCase("sp")){
				
				if(args.length < 2){
					
					if(editing.containsKey(sender)){
						
						spawnpoints.add(player.getLocation().toVector());
						
						Core.arenas.set(editing.get(player) + ".spawnpoints", spawnpoints);
						
						Core.arenas.set(editing.get(player) + ".world", player.getWorld().getName());
						
						player.sendMessage(ChatColor.GREEN + "Spawn point succesfully set");
					
					}else{
						
						sender.sendMessage(ChatColor.RED + "You are not editting an arena");
						
					}
					
				}
				
			}else if(args[0].equalsIgnoreCase("delete")){
				
				if(args.length == 2){
				
					String aname = args[1];
					
					if(Core.arenas.getKeys(false).contains(aname)){
						
						Core.arenas.set(aname, null);
						
						sender.sendMessage(ChatColor.GREEN + aname + " has been succesfully deleted");
						
					}else{
						
						sender.sendMessage(ChatColor.RED + "There is no arena called " + aname);
						
					}
				
				}else{ 
					
				sender.sendMessage(ChatColor.RED + "Please specify the name of the arena you want to delete");
				
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
