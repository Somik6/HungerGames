package com.hungergames.hg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.hungergames.hg.GameSession;
import com.hungergames.hg.HungerGames;

/**
 * Listens for when a block is placed
 * 
 * @author steaks4uce
 * 
 */
public class BlockPlaceListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new BlockPlaceListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public BlockPlaceListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles a BlockPlacekEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		GameSession session = plugin.getGameManager().getGameSession();
		if (!(session.isStarted()) && !(session.isAdmin(player))) {
			event.setCancelled(true);
		}
		if (session.getDeadPlayers().contains(player)) {
			event.setCancelled(true);
		}
	}
}
