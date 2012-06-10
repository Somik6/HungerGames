package com.tips48.hungergames.utils;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventUtils {

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
		return fromX == toX && fromZ == toZ;
	}

}
