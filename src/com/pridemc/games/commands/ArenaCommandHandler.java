package com.pridemc.games.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ArenaCommandHandler implements CommandExecutor {
	
	ArenaCreation create;
	
	ArenaRemoval remove;
	
	ArenaHelp help;
	
	ArenaConfigOptions set;
	
	ArenaTp tp;
	
	ArenaEdit edit;
	
	  public ArenaCommandHandler() {
		  
	        create = new ArenaCreation();
	        
	        remove = new ArenaRemoval();
	        
	        help = new ArenaHelp();
	        
	        set = new ArenaConfigOptions();
	        
	        tp = new ArenaTp();
	        
	        edit = new ArenaEdit();
	        
	    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
	if(sender.hasPermission("pridegames.admin")){
		
	if(args.length > 0){
		
		 if(args[0].equalsIgnoreCase("create")) {
			 
	            return (create.onCommand(sender, cmd, label, args));
	            
	     }else if(args[0].equalsIgnoreCase("remove")){
	    	 
	    	 return (remove.onCommand(sender, cmd, label, args));
	    	 
	     }else if(args[0].equalsIgnoreCase("help")){
	    	 
	    	 return (help.onCommand(sender, cmd, label, args));
	    	 
	     }else if(args[0].equalsIgnoreCase("set")){
	    	 
	    	 return (set.onCommand(sender, cmd, label, args));
	    	 
	     }else if(args[0].equalsIgnoreCase("tp")){
	    	 
	    	 return (tp.onCommand(sender, cmd, label, args));
	    	 
	     }else if(args[0].equalsIgnoreCase("edit")){
	    	 
	    	 return (edit.onCommand(sender, cmd, label, args));
	    	 
	     }
		 
	     }else{
	    	 
	    	 sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
			ChatColor.YELLOW + "Type " + ChatColor.GOLD + "/arena help" + ChatColor.YELLOW + " to view the commands that deal with arenas");
	    	 
	     }
	}
		
		return true;
	}
}
