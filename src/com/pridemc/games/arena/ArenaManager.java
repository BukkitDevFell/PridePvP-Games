package com.pridemc.games.arena;

import org.bukkit.entity.Player;

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

	public static void addPlayerToArena(Player player, String arenaName) {
		Arena arena = getArena(arenaName);
		addPlayerToArena(player.getName(), arena);
	}

	// Use this method for future proofing
	// Eg: a player joins two arenas at once somehow.
	public static void addPlayerToArena(String playerName, Arena arena) {
		arena.addPlayer(new ArenaPlayer(playerName));
	}
}
