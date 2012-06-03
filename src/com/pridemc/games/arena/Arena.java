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
		WAITING_FOR_PLAYERS(true, false, true, false),
		COUNTING_DOWN(true, false, true, false),
		INITIAL_GRACE_PERIOD(false, true, false, false),
		RUNNING_GAME(false, true, false, true);

		private boolean canJoin, canEditBlocks, canChangeClass, canPvP;

		private State(boolean canJoin, boolean canEditBlocks, boolean canChangeClass, boolean canPvP) {
			this.canJoin = canJoin;
			this.canEditBlocks = canEditBlocks;
			this.canChangeClass = canChangeClass;
			this.canPvP = canPvP;
		}

		public boolean canJoin() {
			return canJoin;
		}

		public boolean canEditBlocks() {
			return canEditBlocks;
		}

		public boolean canChangeClass() {
			return canChangeClass;
		}

		public boolean canPvP() {
			return canPvP;
		}
	}

	private String name;
	private Map<String, ArenaPlayer> arenaPlayerMap = new HashMap<String, ArenaPlayer>();
	private Set<ArenaPlayer> arenaPlayers = new HashSet<ArenaPlayer>();
	private State state = State.WAITING_FOR_PLAYERS;
	private Map<ArenaPlayer, Location> playerSpawnPoints = new HashMap<ArenaPlayer, Location>();
	public long startTime = -1;

	final int DEFAULT_MAX_PLAYERS = 15;
	final int DEFAULT_PLAYERS_TO_START = 8;

	public Arena(String name) {
		this.name = name;

		if(!Core.arenas.getKeys(false).contains(getName())){
			Core.arenas.createSection(getName());
		}
		Core.arenas.set(getName() + ".max players", getMaxNumPlayers());
		Core.arenas.set(getName() + ".playercount to start", getPlayersRequiredToStart());
		if (!Core.arenas.isSet(getName() + ".spawnpoint"))
			Core.arenas.createSection(getName() + ".spawnpoint");
		if (!Core.arenas.isSet(getName() + ".world"))
			Core.arenas.createSection(getName() + ".world");
		setState(State.WAITING_FOR_PLAYERS);
		ArenaConfig.saveArenaConfig();
	}

	public String getName() {
		return name;
	}

	public int getNumPlayers() {
		return getArenaPlayers().size();
	}

	public Set<ArenaPlayer> getArenaPlayers() {
		return arenaPlayers;
	}

	public State getState() {
		return state;
	}

	protected void setState(State state) {
		this.state = state;

		//Persist it? all the other code looks at it. <- Bad reason.
		//TODO Remove this retarded node
		Core.arenas.set(getName() + ".status code", getState().ordinal());
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

	@SuppressWarnings("unchecked")
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
		return Core.arenas.getInt(getName() + ".max players", DEFAULT_MAX_PLAYERS);
	}

	public boolean isFull() {
		return arenaPlayers.size() >= getMaxNumPlayers();
	}

	public List<Player> getBukkitPlayers() {
		return ArenaUtil.asBukkitPlayerList(getArenaPlayers());
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
		arenaPlayers.remove(getArenaPlayer(playerName));
	}

	public int getPlayersRequiredToStart() {
		return Core.arenas.getInt(getName() + ".playercount to start", DEFAULT_PLAYERS_TO_START);
	}
}
