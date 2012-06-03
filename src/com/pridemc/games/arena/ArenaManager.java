package com.pridemc.games.arena;

import ca.xshade.bukkit.util.TaskInjector;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class ArenaManager {
	private static ArenaManager instance = new ArenaManager();
	private Map<String, Arena> arenaMap = new HashMap<String, Arena>();
	private Map<String, String> playerToArenaMap = new HashMap<String, String>();

	public static ArenaManager getInstance() {
		return instance;
	}

	public static Arena getArena(String name) {
		return getInstance().arenaMap.get(name);
	}

	public static void addPlayerToArena(Player player, String arenaName) throws Exception {
		Arena arena = getArena(arenaName);
		addPlayerToArena(player.getName(), arena);
	}

	// Use this method for future proofing
	// Eg: a player joins two arenas at once somehow.
	public static void addPlayerToArena(String playerName, Arena arena) throws Exception {
		// Validation
		if (getInstance().playerToArenaMap.containsKey(playerName))
			throw new Exception(String.format("%s already belongs to %s while trying to join %s.",
					playerName,
					getInstance().playerToArenaMap.get(playerName),
					arena.getName()));
		if (arena.getState() != Arena.State.WAITING_FOR_PLAYERS)
			throw new Exception(); // Don't need an error. Just don't let player warp through the portal.
		if (arena.isFull())
			throw new Exception(String.format("%s is already full", arena.getName()));


		//
		arena.addPlayer(new ArenaPlayer(playerName));
		getInstance().playerToArenaMap.put(playerName, arena.getName());

		// Reaction
		if (arena.isFull()) {
			// Arena is ready
			TaskInjector.schedule(new ArenaGraceTask(arena), 0);
			long delay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES); //TODO: should be configurable
			TaskInjector.schedule(new ArenaStartGameTask(arena), delay);
		}
	}

	public static Arena getArenaPlayerIsIn(String playerName) {
		String arenaName = getInstance().playerToArenaMap.get(playerName);
		return getArena(arenaName);
	}
}
