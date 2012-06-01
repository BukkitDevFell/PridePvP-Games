package come.pridemc.games.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

public class Teleportation implements Listener{
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		
		if(event.getCause().equals(TeleportCause.COMMAND) || event.getCause().equals(TeleportCause.PLUGIN)){
			
			for(ItemStack gingots : event.getPlayer().getInventory().getContents()){
				
				if(gingots.getType().equals(Material.GOLD_INGOT)){
					
					PlayerDeath.gold.put(event.getPlayer(), gingots.getAmount());
					
					gingots.setAmount(0);
				}
				
				event.getPlayer().getInventory().clear();
				
				event.getPlayer().getInventory().addItem(new ItemStack(Material.GOLD_INGOT, PlayerDeath.gold.get(event.getPlayer())));
			
			/*
			 * If the location is the spawn or something, remove them from the playerList if they're in-games
			 * 
			 * Prevent the command /pg spawn while in the arena
			 */
			}
		}
	}
}
