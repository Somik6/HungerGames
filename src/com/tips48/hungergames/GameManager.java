package com.tips48.hungergames;

import java.util.HashSet;
import java.util.Set;

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
	public void createSession(String name, Set<String> admins,
			Set<String> players) {
		GameSession session = new GameSession(plugin, name);
		if (admins != null) {
			session.addAllAdmins(admins);
		}
		if (players != null) {
			session.addAllPlayers(admins);
		}
		sessions.add(session);
	}

	/**
	 * Gets the default game session
	 * 
	 * @return Default game session
	 */
	public GameSession getGameSession() {
		return sessions.iterator().next(); // Temporary until support for
											// multiple games are added
	}

}
