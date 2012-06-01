package com.pridemc.games.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ClassSoldier {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
				
				ChatColor.YELLOW + "You have selected the " + ChatColor.AQUA + "SOLDIER" + ChatColor.YELLOW + " class"); 
				
				player.getInventory().clear();
				
				player.getInventory().setArmorContents(null);

				player.getActivePotionEffects().removeAll(player.getActivePotionEffects());
				
						player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
						
						player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));
						
						player.getInventory().addItem(new ItemStack(Material.STONE_SWORD, 1));
		
		return true;
	}
}
