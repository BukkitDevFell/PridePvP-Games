package com.pridemc.games.arena;

import ca.xshade.bukkit.util.TaskInjector;

import java.util.concurrent.TimeUnit;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/3/12
 */
public class ArenaCountdownTask implements Runnable {
	Arena arena;

	public ArenaCountdownTask(Arena arena) {
		this.arena = arena;
	}

	@Override
	public void run() {
		//
		arena.setState(Arena.State.COUNTING_DOWN);

		long delayMillis = ArenaConfig.getCountdownDelay();

		// Msg.
		long minutes = TimeUnit.MILLISECONDS.toMinutes(delayMillis);
		MessageUtil.sendMsgToServer("[Arena - %s] Game starts in %d minute(s).", arena.getName(), minutes);

		//
		TaskInjector.schedule(new ArenaGraceTask(arena), delayMillis);

	}
}
