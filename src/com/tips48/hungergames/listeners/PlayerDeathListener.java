package com.tips48.hungergames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Listens for when a player has kicked
 * 
 * @author tips48
 */
public class PlayerDeathListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new PlayerDeathListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public PlayerDeathListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles a PlayerDeathEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerDeathEvent event) {
		GameSession session = plugin.getGameManager().getGameSession();
		Player player = event.getEntity();
		if (session.isPlayer(player)) {
			session.onPlayerKilled(player);
		}
	}
}
