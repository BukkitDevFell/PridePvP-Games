package come.pridemc.games.events;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {
	
	private Map<Location, Integer> placed = new HashMap<Location, Integer>();
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		
		Block block = event.getBlock();
		
		placed.put(block.getLocation(), 0);
		
		/*
		 * Make sure the players are in an arena and make sure the arena's match has started, else, deny
		 */
		
	}
}
