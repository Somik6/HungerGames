package com.tips48.hungergames.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Listens for when damage is dealt
 * 
 * @author Nicholas Krecklow
 * 
 */
public class PlayerDamageListener implements Listener {
	private HungerGames plugin;

	/**
	 * Creates a new PlayerDamageListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public PlayerDamageListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles a EntityDamageEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			return;
		}
		Player player = (Player) entity;
		GameSession session = plugin.getGameManager().getGameSessionOfPlayer(
				player);
		if (session == null) {
			return;
		}
		if (!(session.isStarted()) && !(session.isAdmin(player))) {
			event.setCancelled(true);
		}
	}
}