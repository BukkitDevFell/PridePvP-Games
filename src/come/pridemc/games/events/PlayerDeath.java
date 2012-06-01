package come.pridemc.games.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.pridemc.games.Core;

public class PlayerDeath implements Listener{
	
	public static Map<Player, Integer> gold = new HashMap<Player, Integer>();
	
	private List<Player> playerlist = new ArrayList<Player>();
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		
		for(ItemStack gingots : event.getDrops()){
			
			if(gingots.getType().equals(Material.GOLD_INGOT)){
				
				gold.put(event.getEntity(), gingots.getAmount());
				
				gingots.setAmount(0);
			}
			
			Core.instance.getPlaying().remove(event.getEntity());
			
			for(Player player : Core.instance.getPlaying().keySet()){
				
				if(Core.instance.getPlaying().get(player) == Core.instance.getPlaying().get(event.getEntity())){
					
					playerlist.add(player);
					
					player.getWorld().createExplosion(player.getLocation().add(0, 15, 0), 2);
					
					player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + 
					
							ChatColor.AQUA + event.getEntity().getDisplayName() + " has died! " + ChatColor.AQUA + playerlist.size() + ChatColor.YELLOW + " players remaining!");
				}
			}
		}
		
		/*
		 * If(game.hasStarted){
		 * 
		 * remove player from the Map
		 * 
		 * Make sure player wasn't last alive
		 */
		
	}
}
