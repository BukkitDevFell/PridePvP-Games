package com.pridemc.games.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class ClassScout {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
				
				ChatColor.YELLOW + "You have selected the " + ChatColor.AQUA + "SCOUT" + ChatColor.YELLOW + " class"); 
				
				player.getInventory().clear();
				
				player.getInventory().setArmorContents(null);
				
				player.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE, 1));
				
				player.getInventory().addItem(new ItemStack(272, 1));
				
				player.getInventory().addItem(new Potion(PotionType.SPEED).toItemStack(1));
							
		
		return true;
	}
}
