package com.pridemc.games.arena;

import com.pridemc.games.Core;

import java.util.Set;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class ArenaConfig {
	public static Set<String> getArenaNames() {
		return Core.arenas.getKeys(false);
	}

	public static void loadArenas() {
		for (String arenaName : ArenaConfig.getArenaNames()) {
			ArenaManager.addArena(new Arena(arenaName));
		}
	}
}
