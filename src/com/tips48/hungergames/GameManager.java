package com.tips48.hungergames;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

/**
 * Stores Sessions and manages them
 * 
 * @author tips48
 * 
 */
public class GameManager {
	private final HungerGames plugin;

	private Set<GameSession> sessions;

	/**
	 * Creates a new GameManager
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public GameManager(HungerGames plugin) {
		this.plugin = plugin;
		this.sessions = new HashSet<GameSession>();
	}

	/**
	 * Creates a new session
	 * 
	 * @param name
	 *            Name of the session
	 * @param admins
	 *            Admins in the session
	 * @param players
	 *            Players in the session
	 */
	public boolean createSession(String name, Set<String> admins,
			Set<String> players) {
		if (getGameSession(name) != null) {
			return false;
		}
		GameSession session = new GameSession(plugin, name);
		if (admins != null) {
			session.addAllAdmins(admins);
		}
		if (players != null) {
			session.addAllPlayers(admins);
		}
		sessions.add(session);
		return true;
	}
	
	/**
	 * Deletes a session
	 * @param name Session to delete
	 * @return If successful
	 */
	public boolean deleteSession(String name) {
		GameSession session = getGameSession(name);
		if (session == null) {
			return false;
		}
		session.stop();
		sessions.remove(session);
		return true;
	}

	/**
	 * Gets a game session by it's name
	 * 
	 * @param name
	 *            Name of the session to get
	 * @return Game session with the specified name, null if none
	 */
	public GameSession getGameSession(String name) {
		for (GameSession session : sessions) {
			if (session.getName().equalsIgnoreCase(name)) {
				return session;
			}
		}
		return null;
	}

	/**
	 * Gets the game session a player is currently playing in
	 * 
	 * @param player
	 *            Player to check with
	 * @return Game session of the player, null if none
	 */
	public GameSession getGameSessionOfPlayer(Player player) {
		return getGameSessionOfPlayer(player.getName());
	}

	/**
	 * Gets the game session a player is currently playing in
	 * 
	 * @param player
	 *            Player to check with
	 * @return Game session of the player, null if none
	 */
	public GameSession getGameSessionOfPlayer(String player) {
		for (GameSession session : sessions) {
			if (session.isPlayer(player) || session.isAdmin(player)) {
				return session;
			}
		}
		return null;
	}

	public Set<GameSession> getSessions() {
		return sessions;
	}

}
