package com.pridemc.games.arena;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class ArenaPlayer {

	String name;

	public ArenaPlayer(Player player) {
		this.name = player.getName();
	}

	public ArenaPlayer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(getName());
	}

	public Arena getArena() {
		return ArenaManager.getArenaPlayerIsIn(getName());
	}

	@Override
	public boolean equals(Object obj) {
		return getName().equals(obj);
	}
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}
