package com.pridemc.games.events;

import com.pridemc.games.arena.Arena;
import com.pridemc.games.arena.ArenaManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.Map;

public class BlockBreak implements Listener{
	
	private Map<Location, Integer> broken = new HashMap<Location, Integer>();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		Block block = event.getBlock();

		Player player = event.getPlayer();
		if (ArenaManager.isInArena(player.getName())) {
			Arena arena = ArenaManager.getArenaPlayerIsIn(player.getName());
			if (arena.getState().canEditBlocks()) { // The arena is allowing editing

				broken.put(block.getLocation(), block.getTypeId());
				
			} else {
				
				if (!event.getPlayer().hasPermission("pridegames.admin")) {
					
					event.setCancelled(true);
					
				}
			}
		}
		
		/*
		 * Make sure the players are in an arena and make sure the arena's match has started, else, deny
		 */
		
	}
}
