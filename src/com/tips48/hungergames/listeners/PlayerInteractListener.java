package com.tips48.hungergames.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Listens for when a player interacts with his surroundings
 * 
 * @author tips48
 * 
 */
public class PlayerInteractListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new PlayerInteractListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public PlayerInteractListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles a PlayerInteractEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerInteractEvent event) {
		Material mat = event.getMaterial();
		Action action = event.getAction();
		Player player = event.getPlayer();
		Block b = event.getClickedBlock();
		GameSession session = plugin.getGameManager().getGameSessionOfPlayer(player);
		if (session != null) {
			if (session.getDeadPlayers().contains(player.getName())) {
				event.setCancelled(true);
				return;
			}
		}
		if (!(event.hasBlock())) {
			return;
		}
		if (!(event.hasItem())) {
			return;
		}
		if (!(action == Action.RIGHT_CLICK_BLOCK)
				&& !(action == Action.RIGHT_CLICK_AIR)) {
			return;
		}
		if (!(mat == Material.DIAMOND_HOE)) {
			return;
		}
		session = plugin.getGameManager().getConstructedSession(
				player);
		if (session == null) {
			return;
		}
		Location bLocation = b.getLocation();
		bLocation.setY(bLocation.getY() + 1);
		bLocation.setX(bLocation.getX() + 0.5);
		bLocation.setZ(bLocation.getZ() + 0.5);
		session.addSpawnLocation(bLocation);
		player.sendMessage(plugin.getBroadcaster().styleMessage(
				"Location added!"));
	}
}
