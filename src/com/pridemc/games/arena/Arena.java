package com.pridemc.games.arena;

import ca.xshade.bukkit.util.ConfigUtil;
import com.pridemc.games.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.*;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class Arena {


	public enum State {
		WAITING_FOR_PLAYERS,
		INITIAL_GRACE,
		RUNNING_GAME
	}

	private String name;
	private Map<String, ArenaPlayer> arenaPlayerMap = new HashMap<String, ArenaPlayer>();
	private Set<ArenaPlayer> arenaPlayers = new HashSet<ArenaPlayer>();
	private State state = State.WAITING_FOR_PLAYERS;
	private Map<ArenaPlayer, Location> playerSpawnPoints = new HashMap<ArenaPlayer, Location>();

	public Arena(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<ArenaPlayer> getArenaPlayers() {
		return arenaPlayers;
	}

	public State getState() {
		return state;
	}

	protected void setState(State state) {
		this.state = state;
	}

	protected void addPlayer(ArenaPlayer arenaPlayer) {
		arenaPlayers.add(arenaPlayer);
		arenaPlayerMap.put(arenaPlayer.getName(), arenaPlayer);
	}

	public ArenaPlayer getArenaPlayer(String playerName) {
		return arenaPlayerMap.get(playerName);
	}

	public void removePlayer(String playerName) {
		ArenaPlayer arenaPlayer = getArenaPlayer(playerName);
		if (arenaPlayer != null) {
			arenaPlayers.remove(arenaPlayer);
			arenaPlayerMap.remove(playerName);
		}
	}

	public List<Vector> getGameSpawnVectors() {
		List<Vector> vectors = new ArrayList<Vector>();
		List configList = Core.arenas.getList(getName() + ".gamepoints");
		if (configList != null) {
			//TODO: Fuck this unsafe casting
			vectors = (List<Vector>)configList;
		}
		return vectors;
	}

	public World getWorld() {
		String worldName = Core.arenas.getString(getName() + ".world");
		return Bukkit.getWorld(worldName);
	}

	public List<Location> getGameSpawnPoints() {
		World world = getWorld();
		List<Location> gameSpawnPoints = new ArrayList<Location>();
		for (Vector vector : getGameSpawnVectors()) {
			gameSpawnPoints.add(vector.toLocation(world));
		}
		return gameSpawnPoints;
	}

	public int getMaxNumPlayers() {
		return Core.arenas.getInt(getName() + ".max players");
	}

	public boolean isFull() {
		return arenaPlayers.size() == getMaxNumPlayers();
	}

	public List<Player> getBukkitPlayers() {
		List<Player> bukkitPlayers = new ArrayList<Player>();
		for (ArenaPlayer arenaPlayer : getArenaPlayers()) {
			Player player = arenaPlayer.getPlayer();
			if (player != null)
				bukkitPlayers.add(player);
		}
		return bukkitPlayers;
	}


	public void setPlayerSpawnPoints() {
		List<Location> spawnPoints = getGameSpawnPoints();
		List<ArenaPlayer> players = new ArrayList<ArenaPlayer>(getArenaPlayers());
		Collections.shuffle(spawnPoints);

		//TODO: Check that spawnPoints.size() > players.size();

		for (int i = 0; i < players.size(); i++) {
			playerSpawnPoints.put(players.get(i), spawnPoints.get(i));
		}
	}

	public void teleportAllToGameSpawnPoint() {
		for (ArenaPlayer arenaPlayer : getArenaPlayers()) {
			teleportToGameSpawnPoint(arenaPlayer);
		}
	}

	public void teleportToGameSpawnPoint(ArenaPlayer arenaPlayer) {
		arenaPlayer.getPlayer().teleport(playerSpawnPoints.get(arenaPlayer));
	}

	public Location getSpawnPoint() {
		return ConfigUtil.getLocationFromVector(Core.arenas, getName() + ".spawnpoint", getName() + ".world");
	}


	public void setPlayerAsDead(String playerName) {
		ArenaPlayer arenaPlayer = getArenaPlayer(playerName);
		arenaPlayer.setState(ArenaPlayer.State.DEAD);
	}

	public List<ArenaPlayer> getAlivePlayers() {
		List<ArenaPlayer> alivePlayers = new ArrayList<ArenaPlayer>();
		for (ArenaPlayer arenaPlayer : getArenaPlayers()) {
			if (!arenaPlayer.isAlive())
				continue;
			alivePlayers.add(arenaPlayer);
		}
		return alivePlayers;
	}
}
