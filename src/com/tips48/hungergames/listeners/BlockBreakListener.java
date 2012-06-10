package com.tips48.hungergames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

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
		if (session.getDeadPlayers().contains(player)) {
			event.setCancelled(true);
		}
	}
}
