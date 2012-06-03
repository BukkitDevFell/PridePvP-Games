package com.pridemc.games.arena;

import org.bukkit.Bukkit;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class ArenaGraceTask implements Runnable {
	Arena arena;

	public ArenaGraceTask(Arena arena) {
		this.arena = arena;
	}

	@Override
	public void run() {
		// Change perm

		// Teleport
		arena.setPlayerSpawnPoints();

		//
		arena.setState(Arena.State.INITIAL_GRACE);

		// Msg.
		Bukkit.broadcastMessage(String.format("Arena [%s] Game starts in 1 minute.", arena.getName()));
	}
}
