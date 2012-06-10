package com.tips48.hungergames.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Listens for when a player has joined
 * 
 * @author tips48
 */
public class PlayerJoinListener implements Listener {
	private final HungerGames plugin;

	/**
	 * Creates a new PlayerJoinListener
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public PlayerJoinListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerJoinEvent event) {
		GameSession session = plugin.getGameManager().getGameSession();
		Player player = event.getPlayer();
		session.addPlayer(player);
		player.teleport(new Location(player.getWorld(), 0, player.getWorld().getHighestBlockYAt(0, 0), 0)); // TODO replace with arena locations
	}

}
