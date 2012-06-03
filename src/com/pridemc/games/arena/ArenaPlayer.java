package com.pridemc.games.arena;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class ArenaPlayer {
	public enum State {
		ALIVE,
		DEAD
	}

	String name;
	State state = State.ALIVE;

	public ArenaPlayer(Player player) {
		this.name = player.getName();
	}

	public ArenaPlayer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(getName());
	}

	public Arena getArena() {
		return ArenaManager.getArenaPlayerIsIn(getName());
	}
}
