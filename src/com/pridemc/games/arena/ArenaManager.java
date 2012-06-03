package com.pridemc.games.arena;

import ca.xshade.bukkit.util.ConfigUtil;
import ca.xshade.bukkit.util.TaskInjector;
import com.pridemc.games.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

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
		addPlayerToArena(player, arena);
	}

	// Use this method for future proofing
	// Eg: a player joins two arenas at once somehow.
	public static void addPlayerToArena(Player player, Arena arena) throws Exception {
		// Validation
		if (getInstance().playerToArenaMap.containsKey(player.getName()))
			throw new Exception(String.format("%s already belongs to %s while trying to join %s.",
					player.getName(),
					getInstance().playerToArenaMap.get(player.getName()),
					arena.getName()));
		if (!arena.getState().canJoin())
			throw new Exception(String.format("%s is already in progress", arena.getName())); // Don't need an error. Just don't let player warp through the portal.
		if (arena.isFull())
			throw new Exception(String.format("%s is already full", arena.getName()));


		//
		_addPlayerToArena(player, arena);


		// Reaction
		String msg = "Joined Arena [%s] [%d / %d]";
		player.sendMessage(String.format(msg, arena.getName(), arena.getArenaPlayers().size(), arena.getMaxNumPlayers()));

		if (arena.getState() == Arena.State.WAITING_FOR_PLAYERS && arena.getArenaPlayers().size() >= arena.getPlayersRequiredToStart()) {
			// Arena is ready
			TaskInjector.schedule(new ArenaCountdownTask(arena), 0);
		}
	}

	private static void _addPlayerToArena(Player player, Arena arena) {
		arena.addPlayer(new ArenaPlayer(player));
		getInstance().playerToArenaMap.put(player.getName(), arena.getName());
		//Core.instance.getPlaying().put(player, arena.getName()); //TODO Legacy
	}

	public static void removePlayerFromArena(Player player) {
		Arena arena = ArenaManager.getArenaPlayerIsIn(player.getName());
		if (arena != null && !arena.getState().canJoin()) { // If in a game state where the players can't join, then remove the player
			ArenaManager.cleanUpPlayer(player);

			List<Player> arenaPlayersAlive = ArenaUtil.asBukkitPlayerList(arena.getArenaPlayers());
			for (Player playerInArena : arenaPlayersAlive) {
				playerInArena.getWorld().createExplosion(playerInArena.getLocation().add(0, 15, 0), 2); // Explosion above player?

				String msg = ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.AQUA + "%s" + " has died! " + ChatColor.AQUA + "%d" + ChatColor.YELLOW + " players remaining!";
				player.sendMessage(String.format(msg, player.getName(), arenaPlayersAlive.size()));
			}

			if (ArenaManager.checkEndGameConditions(arena)) {
				ArenaManager.endGame(arena);
			}
		}
	}

	public static void _removePlayerFromArena(Player player) {
		Arena arena = ArenaManager.getArenaPlayerIsIn(player.getName());
		arena.setPlayerAsDead(player.getName());
		getInstance().playerToArenaMap.remove(player.getName());
	}

	public static Arena getArenaPlayerIsIn(String playerName) {
		String arenaName = getInstance().playerToArenaMap.get(playerName);
		return getArena(arenaName);
	}

	//TODO: Move somewhere more specific.
	public static Location getGlobalSpawnPoint() {
		return ConfigUtil.getLocationFromVector(Core.config, "Spawn location", "Spawn world");
	}

	public static boolean checkEndGameConditions(Arena arena) {
		if (arena.getState() == Arena.State.RUNNING_GAME) {
			Set<ArenaPlayer> alivePlayers = arena.getArenaPlayers();
			if (alivePlayers.size() <= 1) {
				return true;
			}
		}
		return false;
	}

	public static void endGame(Arena arena) {
		List<ArenaPlayer> alivePlayers = new ArrayList<ArenaPlayer>(arena.getArenaPlayers()); //TODO ?

		if (alivePlayers.size() > 0) {
			Player winningPlayer = alivePlayers.get(0).getPlayer();

			// Msg
			Bukkit.broadcastMessage(String.format("%s won %s!", winningPlayer.getName(), arena.getName()));

			// Cleanup remaining players.
			for (ArenaPlayer arenaPlayer : alivePlayers) {
				ArenaManager.cleanUpPlayer(arenaPlayer.getPlayer());
			}
		}

		// Cleanup Arena
		resetArena(arena.getName());
	}

	public static void addArena(Arena arena) {
		getInstance().arenaMap.put(arena.getName(), arena);
	}

	public static void cleanUpPlayer(Player player) {
		_removePlayerFromArena(player);
		player.getInventory().clear();
	}

	public static void resetArena(String arenaName) {
		cleanupArena(getArena(arenaName));
		addArena(new Arena(arenaName));
	}

	public static boolean isInArena(String playerName) {
		return getInstance().playerToArenaMap.containsKey(playerName);
	}

	public static void cleanupArena(Arena arena) {
		for (Player player : ArenaUtil.asBukkitPlayerList(arena.getArenaPlayers())) {
			cleanUpPlayer(player);
			player.teleport(getGlobalSpawnPoint());
		}
	}

	public static void cleanupAllArenas() {
		for (Arena arena : getArenas()) {
			cleanupArena(arena);
		}
	}

	public static Collection<Arena> getArenas() {
		return getInstance().arenaMap.values();
	}
}
