package com.tips48.hungergames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

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
		Player player = event.getPlayer();
		GameSession session = plugin.getGameManager().getGameSessionOfPlayer(
				player);
		if (session == null) {
			return;
		}
		session.removePlayer(player);
	}
}
