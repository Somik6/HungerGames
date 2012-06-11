package com.tips48.hungergames.utils;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

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
	 * @param player
	 *            Player to teleport.
	 * @return The location
	 */
	public static Location getRandomSpawnLocation(Player player) {
		World world = player.getWorld();
		Random rand = new Random();
		int addX = rand.nextInt(player.getName().length() * 2), addZ = rand
				.nextInt(player.getName().length() * 2);

		return new Location(world, addX, world.getHighestBlockAt(addX, addZ)
				.getY(), addZ);
	}

}
