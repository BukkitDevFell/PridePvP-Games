package come.pridemc.games.events;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener{
	
	private Map<Location, Integer> broken = new HashMap<Location, Integer>();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		
		Block block = event.getBlock();
		
		broken.put(block.getLocation(), block.getTypeId());
		
		/*
		 * Make sure the players are in an arena and make sure the arena's match has started, else, deny
		 */
		
	}
}
