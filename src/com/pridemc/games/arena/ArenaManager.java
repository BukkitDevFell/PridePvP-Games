package com.pridemc.games.arena;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class ArenaManager {
    private static ArenaManager instance = new ArenaManager();
    private Map<String, Arena> arenaMap = new HashMap<String, Arena>();

    public static ArenaManager getInstance() {
        return instance;
    }

    public static Arena getArena(String name) {
        return getInstance().arenaMap.get(name);
    }

    public static Arena put(Arena arena) {
        return getInstance().arenaMap.put(arena.getName(), arena);
    }
}
