package com.tips48.hungergames.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.tips48.hungergames.GameManager;
import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

public class Broadcaster {
	private Server theServer;
	private GameSession gameSession;
	private GameManager gameManager;
	private HungerGames plugin;

	public Broadcaster(HungerGames plugin) {
		this.plugin = plugin;
		this.theServer = Bukkit.getServer();
		this.gameManager = this.plugin.getGameManager();
		this.gameSession = this.gameManager.getGameSession();
	}

	public void alertEveryoneOfRemainingPlayers() {
		String topLine = "Only "
				+ plugin.getGameManager().getGameSession().getPlayers().size()
				+ " players remain!";
		String bottomLine = "Including: ";
		boolean skip = true;

		for (String player : plugin.getGameManager().getGameSession()
				.getPlayers()) {
			bottomLine += (skip ? "" : " & " + player);
			skip = false;
		}

		for (Player player : theServer.getOnlinePlayers()) {
			player.sendMessage(topLine);
			player.sendMessage(bottomLine);
		}
	}

	public void alertPlayers(String theMessage) {
		for (Player player : theServer.getOnlinePlayers()) {
			if (gameSession.isPlayer(player)) {
				player.sendMessage(styleMessage(theMessage));
			}
		}
	}

	public void alertAdmins(String theMessage) {
		for (Player player : theServer.getOnlinePlayers()) {
			if (gameSession.isAdmin(player)) {
				player.sendMessage(styleMessage(theMessage));
			}
		}
	}

	public void alertEveryone(String theMessage) {
		for (Player player : theServer.getOnlinePlayers()) {
			player.sendMessage(styleMessage(theMessage));
		}
	}

	public String styleMessage(String theMessage) {
		return ("[" + ChatColor.GOLD + "HG" + ChatColor.WHITE + "] " + theMessage);
	}
}