package com.tips48.hungergames.utils;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.player.PlayerMoveEvent;

import com.tips48.hungergames.config.ConfigManager;

/**
 * Utility methods for events
 * 
 * @author tips48
 */
public class EventUtils {
	private EventUtils() {

	}

	/**
	 * Checks if a PlayerMoveEvent was called because the player moved their X
	 * or their Z coordinate
	 * 
	 * @param event
	 *            Event to check
	 * @return If the event was coarse
	 */
	public static boolean isCoarse(PlayerMoveEvent event) {
		Location from = event.getFrom();
		Location to = event.getTo();
		double fromX = from.getX();
		double fromZ = from.getZ();
		double toX = to.getX();
		double toZ = to.getZ();
		return fromX != toX || fromZ != toZ;
	}

	/**
	 * Returns a location to randomly spawn the player at.
	 * 
	 * @param location
	 *            Player to teleport.
	 * @return The location
	 */
	public static Location getRandomSpawnLocation(Location location) {
		World world = location.getWorld();
		Random rand = new Random();
		int x = rand.nextInt(ConfigManager.RANDOM_TELEPORT_RADIUS);
	    int z = rand.nextInt(ConfigManager.RANDOM_TELEPORT_RADIUS);
		
		if (rand.nextBoolean()) {
			if (rand.nextBoolean()) {
				Location result = new Location(world, location.getX() + x, 0, location.getZ() + z);
				result.setY(world.getHighestBlockYAt(result));
				return result;
			} else {
				Location result = new Location(world, location.getX() - x, 0, location.getZ() + z);
				result.setY(world.getHighestBlockYAt(result));
				return result;
			}
		} else {
			if (rand.nextBoolean()) {
				Location result = new Location(world, location.getX() + x, 0, location.getZ() - z);
				result.setY(world.getHighestBlockYAt(result));
				return result;
			} else {
				Location result = new Location(world, location.getX() - x, 0, location.getZ() - z);
				result.setY(world.getHighestBlockYAt(result));
				return result;
			}
		}
	}

}
