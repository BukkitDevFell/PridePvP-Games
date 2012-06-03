package ca.xshade.bukkit.util;

import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.Timer;

/**
 * A middleman manager of a Timer instance that schedules a Runnable in a InjectIntoBukkitTask.
 * 
 * @see java.util.Timer
 * 
 * @author Zren (Shade / Chris)
 * @version 1.0
 * 
 */
public class TaskInjector {

	/** The instance. */
	private static TaskInjector instance;

	/** The timer. */
	private Timer timer = new Timer();

	/** The plugin. */
	private Plugin plugin;

	/**
	 * Instantiates a new task injector.
	 * 
	 * @param plugin
	 *            the plugin
	 */
	public TaskInjector(Plugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Sets the static version of this class as a new instance with the defined plugin.
	 * 
	 * @param plugin
	 *            the plugin
	 */
	public static void newInstance(Plugin plugin) {
		TaskInjector.instance = new TaskInjector(plugin);
	}

	/**
	 * Gets the timer.
	 * 
	 * @return the timer
	 */
	public static Timer getTimer() {
		return instance.timer;
	}

	/**
	 * Terminates this timer, discarding any currently scheduled tasks. Does not interfere with a currently executing task (if it exists).
	 * Once a timer has been terminated, its execution thread terminates gracefully, and no more tasks may be scheduled on it.
	 * 
	 * @see java.util.Timer#cancel()
	 */
	public static void cancelAll() {
		TaskInjector.getTimer().cancel();
	}

	/**
	 * Schedules the specified task for execution at the specified time. If the time is in the past, the task is scheduled for immediate
	 * execution.
	 * 
	 * @see java.util.Timer#schedule(java.util.TimerTask, java.util.Date)
	 * 
	 * @param runnable
	 *            the runnable
	 * @param time
	 *            the time
	 */
	public static void schedule(Runnable runnable, Date time) {
		TaskInjector.getTimer().schedule(new InjectIntoBukkitTask(instance.plugin, runnable), time);
	}

	/**
	 * Schedules the specified task for repeated fixed-delay execution, beginning at the specified time. Subsequent executions take place at
	 * approximately regular intervals, separated by the specified period.
	 * 
	 * @see java.util.Timer#schedule(java.util.TimerTask, java.util.Date, long)
	 * 
	 * @param runnable
	 *            the runnable
	 * @param firstTime
	 *            the first time
	 * @param period
	 *            the period
	 */
	public static void schedule(Runnable runnable, Date firstTime, long period) {
		TaskInjector.getTimer().schedule(new InjectIntoBukkitTask(instance.plugin, runnable), firstTime, period);
	}

	/**
	 * Schedules the specified task for execution after the specified delay.
	 * 
	 * @see java.util.Timer#schedule(java.util.TimerTask, long)
	 * 
	 * @param runnable
	 *            the runnable
	 * @param delay
	 *            the delay
	 */
	public static void schedule(Runnable runnable, long delay) {
		TaskInjector.getTimer().schedule(new InjectIntoBukkitTask(instance.plugin, runnable), delay);
	}

	/**
	 * Schedules the specified task for repeated fixed-delay execution, beginning after the specified delay. Subsequent executions take
	 * place at approximately regular intervals separated by the specified period.
	 * 
	 * @see java.util.Timer#schedule(java.util.TimerTask, long, long)
	 * 
	 * @param runnable
	 *            the runnable
	 * @param delay
	 *            the delay
	 * @param period
	 *            the period
	 */
	public static void schedule(Runnable runnable, long delay, long period) {
		TaskInjector.getTimer().schedule(new InjectIntoBukkitTask(instance.plugin, runnable), delay, period);
	}

	/**
	 * Schedules the specified task for repeated fixed-rate execution, beginning at the specified time. Subsequent executions take place at
	 * approximately regular intervals, separated by the specified period.
	 * 
	 * @see java.util.Timer#scheduleAtFixedRate(java.util.TimerTask, java.util.Date, long)
	 * 
	 * @param runnable
	 *            the runnable
	 * @param firstTime
	 *            the first time
	 * @param period
	 *            the period
	 */
	public static void scheduleAtFixedRate(Runnable runnable, Date firstTime, long period) {
		TaskInjector.getTimer().scheduleAtFixedRate(new InjectIntoBukkitTask(instance.plugin, runnable), firstTime, period);
	}

	/**
	 * Schedules the specified task for repeated fixed-rate execution, beginning after the specified delay. Subsequent executions take place
	 * at approximately regular intervals, separated by the specified period.
	 * 
	 * @see java.util.Timer#scheduleAtFixedRate(java.util.TimerTask, long, long)
	 * 
	 * @param runnable
	 *            the runnable
	 * @param delay
	 *            the delay
	 * @param period
	 *            the period
	 */
	public static void scheduleAtFixedRate(Runnable runnable, long delay, long period) {
		TaskInjector.getTimer().scheduleAtFixedRate(new InjectIntoBukkitTask(instance.plugin, runnable), delay, period);
	}
}
