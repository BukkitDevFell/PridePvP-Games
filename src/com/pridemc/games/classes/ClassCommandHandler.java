package com.pridemc.games.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pridemc.games.Core;

public class ClassCommandHandler implements CommandExecutor {
	
	ClassArcher archer;
	
	ClassScout scout;
	
	ClassSoldier soldier;
	
	public ClassCommandHandler() {
		  
        archer = new ClassArcher();
        
        scout = new ClassScout();
        
        soldier = new ClassSoldier();
        
    }
	
	List<String> classes = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(Core.instance.getPlaying().containsKey(player) && Core.arenas.getInt(Core.instance.getPlaying().get(player) + ".status code") == 0){
		
		if(args.length > 0){
			
			if(args[0].equalsIgnoreCase("Archer")){
				
				return (archer.onCommand(sender, cmd, label, args));
				
			}else if(args[0].equalsIgnoreCase("Scout")){
				
				return (scout.onCommand(sender, cmd, label, args));
				
			}else if(args[0].equalsIgnoreCase("Soldier")){
				
				return (soldier.onCommand(sender, cmd, label, args));
			}
				
		}else{
			
				classes.add("Archer");
				
				classes.add("Scout");
				
				classes.add("Soldier");
				
				if(sender.hasPermission("pg.class.heavy")){
					
					classes.add("Heavy");
					
				}if(sender.hasPermission("pg.class.spy")){
					
					classes.add("Spy");
			}
			
			if(!classes.isEmpty()){
				
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			ChatColor.YELLOW + "The following classes are available for you. Type " + ChatColor.GOLD  + "/class <classname>" + ChatColor.YELLOW + " to select that class");
			
			sender.sendMessage(ChatColor.AQUA + "Classes: " + ChatColor.YELLOW + classes);
			
			classes.clear();
			
			}else{
				
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			ChatColor.YELLOW + ChatColor.RED +"Uh oh! You don't have any classes! Please contact an admin!");
			
				}
			}
		
		}else{
			
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			
					ChatColor.RED + "You can't use this command now!");
		}
		return true;
	}
}
