package com.pridemc.games.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ClassArcher {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
		
				ChatColor.YELLOW + "You have selected the " + ChatColor.AQUA + "ARCHER" + ChatColor.YELLOW + " class"); 
				
				player.getInventory().clear();
				
				player.getInventory().setArmorContents(null);
				
				player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
				
				ItemStack is = new ItemStack(Material.BOW, 1);
				
				is.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
				
				player.getInventory().addItem(is);
				
				player.getInventory().setArmorContents(null);
				
				player.getActivePotionEffects().clear();
				
				for(ItemStack bow : player.getInventory().getContents()){
					
					if(bow != null){
					
					if(bow.getType().equals(Material.BOW)){
						
						bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
						}
					}
				}
				
				player.getInventory().addItem(new ItemStack(322, 1));
		
		return true;
	}
}
