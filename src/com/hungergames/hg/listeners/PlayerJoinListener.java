package com.hungergames.hg.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.hungergames.hg.GameSession;
import com.hungergames.hg.HungerGames;
import com.hungergames.hg.config.HGConfig;

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

	/**
	 * Handles a PlayerJoinEvent
	 * 
	 * @param event
	 *            Event
	 */
	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerJoinEvent event) {
		GameSession session = plugin.getGameManager().getGameSession();
		Player player = event.getPlayer();
		event.setJoinMessage("");
		if (HGConfig.ADMINS.contains(player.getName())) {
			session.addAdmin(player);
		} else {
			session.addPlayer(player);
			player.teleport(new Location(player.getWorld(), 0, player
					.getWorld().getHighestBlockYAt(0, 0), 0)); // Temporary
			player.getInventory().clear();
			player.getInventory().addItem(new ItemStack(Material.STICK, 1));
			player.getInventory().addItem(new ItemStack(Material.ROTTEN_FLESH, 2));
		}
	}

}
