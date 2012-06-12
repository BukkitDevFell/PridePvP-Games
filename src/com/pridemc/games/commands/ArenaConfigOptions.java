package com.pridemc.games.commands;

import com.pridemc.games.Core;
import com.pridemc.games.arena.MessageUtil;
import com.pridemc.games.arena.WorldEditUtil;
import com.sk89q.worldedit.IncompleteRegionException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArenaConfigOptions implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			String arenaName = Core.instance.getEditing().get(sender);

			if (args.length < 2) {

				sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " +

						ChatColor.RED + "You have to specifiy something to set! (Spawnpoint [" + ChatColor.YELLOW + "sp" + ChatColor.RED + "], " +

						"Game point[" + ChatColor.YELLOW + "gp" + ChatColor.RED + "], " +

						"Max players[" + ChatColor.YELLOW + "mp" + ChatColor.RED + "], " +

						"Playercount before start[" + ChatColor.YELLOW + "pbs" + ChatColor.RED + "]");

			} else {

				if (Core.instance.getEditing().containsKey(sender)) {

					//Setting spawn points
					if (args[1].equalsIgnoreCase("sp")) {

						Core.arenas.set(arenaName + ".spawnpoint", player.getLocation().toVector());

						Core.arenas.set(arenaName + ".world", player.getWorld().getName());

						sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " +

								ChatColor.YELLOW + "Spawn point for " + arenaName + " set at your location");

						//Setting game points
					} else if (args[1].equalsIgnoreCase("gp")) {

						Integer x = player.getLocation().toVector().getBlockX();

						Integer y = player.getLocation().toVector().getBlockY();

						Integer z = player.getLocation().toVector().getBlockZ();

						if (Core.arenas.getList(arenaName + ".gamepoints") == null) {

							ArrayList<Vector> gamepoints = new ArrayList<Vector>();

							gamepoints.add(new Vector(x, y, z));

							Core.arenas.set(arenaName + ".gamepoints", gamepoints);

						} else {

							@SuppressWarnings("unchecked")
							ArrayList<Vector> gamepoints = (ArrayList<Vector>) Core.arenas.getList(arenaName + ".gamepoints");

							gamepoints.add(new Vector(x, y, z));

							Core.arenas.set(arenaName + ".gamepoints", gamepoints);

						}

						sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " +

								ChatColor.YELLOW + "Game point for " + arenaName + " set at your location");

						//Setting the max players
					} else if (args[1].equalsIgnoreCase("mp")) {

						if (args.length < 3) {

							sender.sendMessage(ChatColor.RED + "Missing arguments. Correct usage: /arena set mp #");

						} else {

							try {
								Integer number = Integer.valueOf(args[2]);

								Core.arenas.set(arenaName + ".max players", number);

								sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " +

										ChatColor.YELLOW + "Max player amount for " + arenaName + " set to " + number);

							} catch (NumberFormatException ex) {

								sender.sendMessage(ChatColor.RED + "The last argument must be a number!");

							}
						}

						//Setting the players to start
					} else if (args[1].equalsIgnoreCase("pbs")) {

						if (args.length < 3) {

							sender.sendMessage(ChatColor.RED + "Missing arguments. Correct usage: /arena set mp #");

						} else {

							try {
								Integer number = Integer.valueOf(args[2]);

								Core.arenas.set(arenaName + ".playercount to start", number);

								sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " +
										ChatColor.YELLOW + "Playercount to start for " + arenaName + " set to " + Integer.valueOf(number));

							} catch (NumberFormatException ex) {

								sender.sendMessage(ChatColor.RED + "The last argument must be a number!");

							}
						}
					}else if (args[1].equalsIgnoreCase("region")) {
						try {
							Vector minVector = WorldEditUtil.getSelectionMinimum(player);
							Vector maxVector = WorldEditUtil.getSelectionMaximum(player);
							Core.arenas.set(arenaName + ".region.min", minVector);
							Core.arenas.set(arenaName + ".region.max", maxVector);
							String msg = ChatColor.YELLOW + "Set region for %s as (%s) -> (%s).";
							MessageUtil.sendMsg(sender, msg, arenaName, minVector, maxVector);
						} catch (IncompleteRegionException e) {
							MessageUtil.sendMsg(sender, ChatColor.RED + "You have not selected a region (WorldEdit).");
						}
					}
				} else {
					String msg = ChatColor.RED + "You are not editing an arena!" +
							" Type " + ChatColor.YELLOW + "/arena edit <name> " + ChatColor.RED + "to begin editing an arena";
					MessageUtil.sendMsg(sender, msg);
				}
			}

			try {

				Core.arenas.save(new File(Core.instance.getDataFolder(), "arenas.yml"));

			} catch (IOException e) {

				e.printStackTrace();

			}
		}

		return true;
	}
}
