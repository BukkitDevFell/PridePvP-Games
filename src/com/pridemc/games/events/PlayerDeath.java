package com.pridemc.games.events;

import com.pridemc.games.arena.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeath implements Listener{
	
	private List<Player> playerlist = new ArrayList<Player>();
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {

		Player player = event.getEntity();
		ArenaManager.removePlayerFromArena(player);
	}
}
