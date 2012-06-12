package com.pridemc.games.arena;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/3/12
 */
public class ArenaUtil {
	public static List<String> getPlayerDisplayNames(Arena arena) {
		List<String> playerDisplayNames = new ArrayList<String>();
		for(Player playerInSameArena : arena.getBukkitPlayers()){
			playerDisplayNames.add(playerInSameArena.getDisplayName());
		}
		return playerDisplayNames;
	}

	public static List<Player> asBukkitPlayerList(Iterable<ArenaPlayer> arenaPlayers) {
		List<Player> bukkitPlayers = new ArrayList<Player>();
		for (ArenaPlayer arenaPlayer : arenaPlayers) {
			Player player = arenaPlayer.getPlayer();
			if (player != null)
				bukkitPlayers.add(player);
		}
		return bukkitPlayers;
	}
}
