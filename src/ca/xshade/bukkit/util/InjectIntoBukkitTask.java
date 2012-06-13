package ca.xshade.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.TimerTask;

/**
 * A task that can be scheduled for one-time or repeated execution by a Timer that will insert the defined task into Bukkit's schedular to
 * run on it's next tick.
 * 
 * @author Zren (Shade / Chris)
 * @version 1.0
 */
public class InjectIntoBukkitTask extends TimerTask {

	/** The plugin. */
	private Plugin plugin;

	/** The runnable. */
	private Runnable runnable;

	/**
	 * Instantiates a new inject into bukkit task.
	 * 
	 * @param plugin
	 *            the plugin
	 * @param runnable
	 *            the runnable
	 */
	public InjectIntoBukkitTask(Plugin plugin, Runnable runnable) {
		this.plugin = plugin;
		this.runnable = runnable;
	}

	/**
	 * Inserts task into the Bukkit schedular to run on the next tick.
	 */
	@Override
	public void run() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable);
	}
}
