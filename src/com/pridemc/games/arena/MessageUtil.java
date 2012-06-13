package com.pridemc.games.arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/3/12
 */
public class MessageUtil {
	public static final String pluginPrefix = ChatColor.GOLD + "[" + ChatColor.AQUA + "Pride Games" + ChatColor.GOLD + "] " + ChatColor.YELLOW;

	public static void sendMsg(CommandSender sender, String msg, Object ... args) {
		String formattedMsg = String.format(msg, args);
		sender.sendMessage(pluginPrefix + formattedMsg);
	}

	public static void sendMsgToAll(List<CommandSender> senders, String msg, Object ... args) {
		String formattedMsg = String.format(msg, args);
		for (CommandSender sender : senders) {
			sender.sendMessage(pluginPrefix + formattedMsg);
		}
	}

	public static void sendMsgToServer(String msg, Object ... args) {
		List<CommandSender> senders = new ArrayList<CommandSender>();
		senders.add(Bukkit.getConsoleSender());
		senders.addAll(Arrays.asList(Bukkit.getOnlinePlayers()));
		sendMsgToAll(senders, msg, args);
	}
}
