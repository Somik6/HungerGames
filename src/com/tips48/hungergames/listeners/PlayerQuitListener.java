package com.tips48.hungergames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Listens for when a player has quit
 * 
 * @author tips48
 * @author steaks4uce
 */
public class PlayerQuitListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new PlayerQuitListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public PlayerQuitListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles a PlayerQuitEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerQuitEvent event) {
		GameSession session = plugin.getGameManager().getGameSession();
		Player player = event.getPlayer();
		session.removePlayer(player);
		plugin.getBroadcaster().alertEveryone(player.getName() + " has quit the Hunger Games!");
	}
}
