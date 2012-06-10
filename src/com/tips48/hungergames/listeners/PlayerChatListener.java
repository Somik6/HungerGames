package com.tips48.hungergames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import com.tips48.hungergames.HungerGames;

public class PlayerChatListener implements Listener {
	private HungerGames plugin;

	public PlayerChatListener(HungerGames plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true)
	public void handle(PlayerChatEvent event) {
		if (!(plugin.getGameManager().getGameSession().isStarted())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(
					plugin.getBroadcaster().styleMessage(
							"You cannot chat until the game has started!"));
		}
	}
}
