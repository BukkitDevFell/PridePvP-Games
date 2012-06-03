package com.pridemc.games.arena;

import com.pridemc.games.Core;

import java.io.File;
import java.io.IOException;
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

	public static void saveArenaConfig() {
		try {
			Core.arenas.save(new File(Core.instance.getDataFolder(), "arenas.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static long getCountdownDelay() {
		return 0;//TimeUnit.MILLISECONDS.convert(3, TimeUnit.MINUTES);
	}

	public static long getGracePeriodDelay() {
		return 0;//TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES);
	}
}
