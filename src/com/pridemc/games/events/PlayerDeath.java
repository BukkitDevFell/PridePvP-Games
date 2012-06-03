package com.pridemc.games.events;

import com.pridemc.games.Core;
import com.pridemc.games.arena.Arena;
import com.pridemc.games.arena.ArenaManager;
import com.pridemc.games.arena.ArenaPlayer;
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
	public void onPlayerDeath(PlayerDeathEvent event){
		
		Core.instance.getPlaying().remove(event.getEntity());

		for(Player player : Core.instance.getPlaying().keySet()){

			if(Core.instance.getPlaying().get(player) == Core.instance.getPlaying().get(event.getEntity())){

				playerlist.add(player);

				player.getWorld().createExplosion(player.getLocation().add(0, 15, 0), 2);

				player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " +

						ChatColor.AQUA + event.getEntity().getDisplayName() + " has died! " + ChatColor.AQUA + playerlist.size() + ChatColor.YELLOW + " players remaining!");
			}
		}
		
		/*
		 * If(game.hasStarted){
		 * 
		 * remove player from the Map
		 * 
		 * Make sure player wasn't last alive
		 */

		Player player = event.getEntity();
		Arena arena = ArenaManager.getArenaPlayerIsIn(player.getName());

		if (arena != null && arena.getState() == Arena.State.RUNNING_GAME) {
			ArenaPlayer arenaPlayer = arena.getArenaPlayer(player.getName());
			if (arenaPlayer.getState() == ArenaPlayer.State.ALIVE) {
				ArenaManager.cleanUpPlayer(player);
				ArenaManager.checkEndGameConditions(arena);
			}
		}
	}
}
