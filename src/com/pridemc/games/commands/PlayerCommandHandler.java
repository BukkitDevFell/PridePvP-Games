package com.pridemc.games.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlayerCommandHandler implements CommandExecutor{
	
	PlayerHelp help;
	
	PlayerSpawn spawn;
	
	PlayerSpawnSet setspawn;
	
	PlayerShop shop;
	
	PlayerShopSet setshop;
	
	PlayerList list;
	
	PlayerLeave leave;
	
	public PlayerCommandHandler() {
		  
        help = new PlayerHelp();
        
        spawn = new PlayerSpawn();
        
        setspawn = new PlayerSpawnSet();
        
        shop = new PlayerShop();
        
        setshop = new PlayerShopSet();
        
        list = new PlayerList();
        
        leave = new PlayerLeave();
        
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length > 0){
			
			if(args[0].equalsIgnoreCase("help")) {
				 
	            return (help.onCommand(sender, cmd, label, args));
			
			}else if(args[0].equalsIgnoreCase("spawn")){
				
				return (spawn.onCommand(sender, cmd, label, args));
				
			}else if(args[0].equalsIgnoreCase("setspawn")){
				
				return (setspawn.onCommand(sender, cmd, label, args));
				
			}else if(args[0].equalsIgnoreCase("shop")){
				
				return (shop.onCommand(sender, cmd, label, args));
				
			}else if(args[0].equalsIgnoreCase("setshop")){
				
				return (setshop.onCommand(sender, cmd, label, args));
				
			}else if(args[0].equalsIgnoreCase("list")){
				
				return (list.onCommand(sender, cmd, label, args));
				
			}else if(args[0].equalsIgnoreCase("leave")){
				
				return (leave.onCommand(sender, cmd, label, args));
				
			}
			
			
		}else{
			
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
					ChatColor.YELLOW + "Type " + ChatColor.GOLD + "/pg help" + ChatColor.YELLOW + " to view player commands");
			
		}
		
		return true;
	}
}
