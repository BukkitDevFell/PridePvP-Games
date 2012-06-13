package com.pridemc.games.arena;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import uk.co.oliwali.HawkEye.*;
import uk.co.oliwali.HawkEye.callbacks.RollbackCallback;
import uk.co.oliwali.HawkEye.database.SearchQuery;
import uk.co.oliwali.HawkEye.util.HawkEyeAPI;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;


/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/3/12
 */
public class RevertManager {
	public static void revertArena(Arena arena) {

		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("HawkEye");
		HawkEye hawkEye = (HawkEye)plugin;


		//Setup a SearchParser instance and set values
		SearchParser parser = new SearchParser();
		parser.minLoc = new Vector(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
		parser.maxLoc = new Vector(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
		parser.worlds = new String[]{arena.getWorld().getName()};
		parser.actions = Arrays.asList(new DataType[]{DataType.BLOCK_BREAK, DataType.BLOCK_PLACE});

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(arena.startTime);
		parser.dateFrom = sdf.format(cal.getTime());

		parser.parseLocations();

		//Call search function
		HawkEyeAPI.performSearch(new RollbackCallback(new PlayerSession(Bukkit.getConsoleSender()), Rollback.RollbackType.GLOBAL), parser, SearchQuery.SearchDir.DESC);


		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(arena.startTime);
		String dateFrom = sdf.format(cal.getTime());
		String command = String.format("hawk rollback t:%s",
				dateFrom,
				)
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
		*/
	}
}