package com.tips48.hungergames.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Handles broadcasting information to all the players
 * 
 * @author steaks4uce
 * 
 */
public class Broadcaster {
	private HungerGames plugin;

	/**
	 * Creates a new Broadcaster
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public Broadcaster(HungerGames plugin) {
		this.plugin = plugin;
	}

	/**
	 * Alerts all the players of a session of the remaining players
	 */
	public void alertEveryoneOfRemainingPlayers() {
		GameSession session = plugin.getGameManager().getGameSession();
		String topLine = "Only " + session.getPlayers().size()
				+ " players remain!";

		String bottomLine = "They are: "
				+ Utils.makeReadable(session.getPlayers());
		for (String player : session.getAllPlayers()) {
			Player p = Bukkit.getPlayer(player);
			if (p == null) {
				continue;
			}
			p.sendMessage(styleMessage(topLine));
			p.sendMessage(styleMessage(bottomLine));
		}
		for (String player : session.getAdmins()) {
			Player p = Bukkit.getPlayer(player);
			if (p == null) {
				continue;
			}
			p.sendMessage(styleMessage(topLine));
			p.sendMessage(styleMessage(bottomLine));
		}
	}

	/**
	 * Alerts all the players of a message
	 * 
	 * @param message
	 *            Message to be sent
	 */
	public void alertPlayers(String message) {
		GameSession session = plugin.getGameManager().getGameSession();
		for (String player : session.getPlayers()) {
			Player p = plugin.getServer().getPlayer(player);
			if (p == null) {
				continue;
			}
			p.sendMessage(styleMessage(message));
		}
	}

	/**
	 * Alerts all the admins of a message
	 * 
	 * @param message
	 *            Message to be sent
	 */
	public void alertAdmins(String message) {
		GameSession session = plugin.getGameManager().getGameSession();
		for (String player : session.getAdmins()) {
			Player p = plugin.getServer().getPlayer(player);
			if (p == null) {
				continue;
			}
			p.sendMessage(styleMessage(message));
		}
	}

	/**
	 * Alerts all the players and administrators of a message
	 * 
	 * @param message
	 *            Message to be sent
	 */
	public void alertEveryone(String message) {
		GameSession session = plugin.getGameManager().getGameSession();
		for (String player : session.getAllPlayers()) {
			Player p = plugin.getServer().getPlayer(player);
			if (p == null) {
				continue;
			}
			p.sendMessage(styleMessage(message));
		}
		for (String player : session.getAdmins()) {
			Player p = plugin.getServer().getPlayer(player);
			if (p == null) {
				continue;
			}
			p.sendMessage(styleMessage(message));
		}
	}

	/**
	 * Styles a message
	 * 
	 * @param message
	 *            Message to style
	 * @return Styled message
	 */
	public String styleMessage(String message) {
		return ("[" + ChatColor.RED + "HG" + ChatColor.WHITE + "] " + message);
	}
}