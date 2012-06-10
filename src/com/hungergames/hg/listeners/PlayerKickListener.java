package com.hungergames.hg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import com.hungergames.hg.GameSession;
import com.hungergames.hg.HungerGames;

/**
 * Listens for when a player has kicked
 * 
 * @author tips48
 */
public class PlayerKickListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new PlayerKickListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public PlayerKickListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles a PlayerKickEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerKickEvent event) {
		GameSession session = plugin.getGameManager().getGameSession();
		Player player = event.getPlayer();
		session.removePlayer(player);
	}
}
