package com.pridemc.games.arena;

import com.pridemc.games.Core;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class ArenaConfig {
	public static final long DEFAULT_GRACE_PERIOD = TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);
	public static final long DEFAULT_COUNTDOWN = TimeUnit.SECONDS.convert(3, TimeUnit.MINUTES);
	public static final String NODE_GRACE_PERIOD = "grace period";
	public static final String NODE_COUNTDOWN = "countdown";


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
		if (!Core.arenas.isSet(NODE_COUNTDOWN))
			Core.arenas.set(NODE_COUNTDOWN, DEFAULT_COUNTDOWN);
		return TimeUnit.MILLISECONDS.convert(Core.arenas.getLong(NODE_COUNTDOWN, DEFAULT_GRACE_PERIOD), TimeUnit.SECONDS);
	}

	public static long getGracePeriodDelay() {
		if (!Core.arenas.isSet(NODE_GRACE_PERIOD))
			Core.arenas.set(NODE_GRACE_PERIOD, DEFAULT_GRACE_PERIOD);
		return TimeUnit.MILLISECONDS.convert(Core.arenas.getLong(NODE_GRACE_PERIOD, DEFAULT_GRACE_PERIOD), TimeUnit.SECONDS);
	}
}
