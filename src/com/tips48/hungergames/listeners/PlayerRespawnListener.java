package com.tips48.hungergames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Listens for when a player has respawned
 * 
 * @author tips48
 */
public class PlayerRespawnListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new PlayerRespawnListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public PlayerRespawnListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles a PlayerRespawnEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerRespawnEvent event) {
		GameSession session = plugin.getGameManager().getGameSession();
		Player player = event.getPlayer();
		if (session.isPlayer(player)) {
			session.onPlayerRespawn(player);
		}
	}
}
