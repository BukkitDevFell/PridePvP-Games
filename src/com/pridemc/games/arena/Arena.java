package com.pridemc.games.arena;

import com.pridemc.games.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private Set<ArenaPlayer> players = new HashSet<ArenaPlayer>();
	private State state = State.WAITING_FOR_PLAYERS;

	public Arena(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<ArenaPlayer> getPlayers() {
		return players;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	protected boolean addPlayer(ArenaPlayer arenaPlayer) {
		return players.add(arenaPlayer);
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
}
