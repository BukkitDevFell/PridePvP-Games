package com.pridemc.games.events;

import com.pridemc.games.arena.Arena;
import com.pridemc.games.arena.ArenaManager;
import com.pridemc.games.arena.ArenaPlayer;
import com.pridemc.games.arena.ArenaUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeath implements Listener{
	
	private List<Player> playerlist = new ArrayList<Player>();
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {

		Player player = event.getEntity();
		Arena arena = ArenaManager.getArenaPlayerIsIn(player.getName());
		if (arena != null && arena.getState() == Arena.State.RUNNING_GAME) {
			ArenaPlayer arenaPlayer = arena.getArenaPlayer(player.getName());
			ArenaManager.cleanUpPlayer(player);

			List<Player> arenaPlayersAlive = ArenaUtil.asBukkitPlayerList(arena.getArenaPlayers());
			for (Player playerInArena : arenaPlayersAlive) {
				playerInArena.getWorld().createExplosion(playerInArena.getLocation().add(0, 15, 0), 2); // Explosion above player?

				String msg = ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.AQUA + "%s" + " has died! " + ChatColor.AQUA + "%d" + ChatColor.YELLOW + " players remaining!";
				player.sendMessage(String.format(msg, player.getName(), arenaPlayersAlive.size()));
			}

			ArenaManager.checkEndGameConditions(arena);
		}
	}
}
