package com.hungergames.hg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.hungergames.hg.GameSession;
import com.hungergames.hg.HungerGames;

/**
 * Listens for when a block is broken
 * 
 * @author tips48
 * 
 */
public class BlockBreakListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new BlockBreakListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public BlockBreakListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles a BlockBreakEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(BlockBreakEvent event) {
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
