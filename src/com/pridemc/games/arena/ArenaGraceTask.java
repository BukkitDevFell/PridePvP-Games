package com.pridemc.games.arena;

import ca.xshade.bukkit.util.TaskInjector;

import java.util.concurrent.TimeUnit;

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

		long delayMillis = ArenaConfig.getGracePeriodDelay();

		// Msg.
		long minutes = TimeUnit.MILLISECONDS.toMinutes(delayMillis);
		MessageUtil.sendMsgToServer("[Arena - %s] Grace Period Started - Game Begins in %s minute(s).", arena.getName(), minutes);

		//
		TaskInjector.schedule(new ArenaStartGameTask(arena), delayMillis);
	}
}
