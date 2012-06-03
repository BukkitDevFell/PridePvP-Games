package ca.xshade.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.util.Vector;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class ConfigUtil {
	public static Location getLocationFromVector(Configuration node, String vectorPath, String worldPath) {
		Vector vector = node.getVector(vectorPath);
		String worldName = node.getString(worldPath);
		World world = Bukkit.getWorld(worldName);
		return vector.toLocation(world);
	}
}
