package com.pridemc.games.arena;

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

	protected boolean addPlayer(ArenaPlayer arenaPlayer) {
		return arenaPlayers.add(arenaPlayer);
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
			gameSpawnPoints.add(new Location(world, vector.getX(), vector.getY(), vector.getZ()));
		}
		return gameSpawnPoints;
	}

	public int getMaxNumPlayers() {
		return Core.arenas.getInt(getName() + ".max players");
	}

	public boolean isFull() {
		return arenaPlayers.size() == getMaxNumPlayers();
	}

	public void start() {
		// Change perm

		// Teleport
		setState(State.INITIAL_GRACE);
		// Msg.

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
}
