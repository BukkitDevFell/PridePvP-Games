package com.pridemc.games.arena;

import ca.xshade.bukkit.util.TaskInjector;
import org.bukkit.Bukkit;

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

		// Msg.
		Bukkit.broadcastMessage(String.format("Arena [%s] Game starts in 3 minute.", arena.getName())); //TODO

		//
		TaskInjector.schedule(new ArenaGraceTask(arena), ArenaConfig.getCountdownDelay());

	}
}
