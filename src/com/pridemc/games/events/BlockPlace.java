package com.pridemc.games.events;

import com.pridemc.games.arena.Arena;
import com.pridemc.games.arena.ArenaManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.Map;

public class BlockPlace implements Listener {
	
	private Map<Location, Integer> placed = new HashMap<Location, Integer>();
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		
		Block block = event.getBlock();

		Player player = event.getPlayer();

		if (player.hasPermission("pridegames.admin"))
			return;

		if (ArenaManager.isInArena(player.getName())) {
			Arena arena = ArenaManager.getArenaPlayerIsIn(player.getName());
			if (arena.getState().canEditBlocks()) { // The arena is allowing editing
				
				placed.put(block.getLocation(), 0); //TODO
				return;
				
			}
		}

		event.setCancelled(true);
	}
}
