package com.pridemc.games.events;

import com.pridemc.games.arena.Arena;
import com.pridemc.games.arena.ArenaManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/3/12
 */
public class PvP implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity defender = event.getEntity();
		if (defender instanceof Player) {
			Player defenderPlayer = (Player)defender;
			if (ArenaManager.isInArena(defenderPlayer.getName())) {
				Arena arena = ArenaManager.getArenaPlayerIsIn(defenderPlayer.getName());
				if (!arena.getState().canPvP()) {
					event.setCancelled(true);
				}
			}
		}
	}
}
