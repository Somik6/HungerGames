package com.tips48.hungergames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;
import com.tips48.hungergames.utils.EventUtils;

/**
 * Listens for when a player has moved
 * 
 * @author tips48
 */
public class PlayerMoveListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new PlayerMoveListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public PlayerMoveListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerMoveEvent event) {
		GameSession session = plugin.getGameManager().getGameSession();
		Player player = event.getPlayer();
		if ((!(session.isStarted())) && session.isPlayer(player)
				&& EventUtils.isCoarse(event)) {
			event.setCancelled(true);
		}
	}
}
