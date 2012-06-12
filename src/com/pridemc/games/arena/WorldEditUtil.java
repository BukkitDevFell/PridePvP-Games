package com.pridemc.games.arena;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/12/12
 */
public class WorldEditUtil {
	public static LocalSession getSession(Player player) {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldEdit");
		WorldEditPlugin worldEditPlugin = (WorldEditPlugin)plugin;

		return worldEditPlugin.getSession(player);
	}

	public static Vector getSelectionMinimum(Player player) throws IncompleteRegionException {
		LocalSession session = getSession(player);
		return toBukkitVector(session.getRegionSelector(session.getSelectionWorld()).getRegion().getMinimumPoint());
	}

	public static Vector getSelectionMaximum(Player player) throws IncompleteRegionException {
		LocalSession session = getSession(player);
		return toBukkitVector(session.getRegionSelector(session.getSelectionWorld()).getRegion().getMaximumPoint());
	}

	public static Vector toBukkitVector(com.sk89q.worldedit.Vector vector) {
		return new Vector(vector.getX(), vector.getY(), vector.getZ());
	}
}
