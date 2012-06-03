package com.pridemc.games.arena;

import ca.xshade.bukkit.util.TaskInjector;
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
		if (ArenaManager.checkEndGameConditions(arena)) {
			ArenaManager.endGame(arena);
			return;
		}

		arena.startTime = System.currentTimeMillis();

		// Teleport Players
		arena.setPlayerSpawnPoints();
		arena.teleportAllToGameSpawnPoint();

		//
		arena.setState(Arena.State.INITIAL_GRACE_PERIOD);

		// Msg.
		Bukkit.broadcastMessage(String.format("Arena [%s] Grace Period - Game Begins in 1 minute", arena.getName()));

		//
		TaskInjector.schedule(new ArenaStartGameTask(arena), ArenaConfig.getGracePeriodDelay());
	}
}
