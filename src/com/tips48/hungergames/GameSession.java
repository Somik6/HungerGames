package com.tips48.hungergames;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.tips48.hungergames.config.HGConfig;

/**
 * Stores information about one session of
 * 
 * @author tips48
 * 
 */
public class GameSession {
	private final HungerGames plugin;

	private final String name;

	private final Set<String> players;
	private final Set<String> deadPlayers;
	private final Set<String> admins;

	private boolean started;

	private int taskId = -1;

	/**
	 * Creates a new GameSession
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public GameSession(HungerGames plugin, String name) {
		this.plugin = plugin;
		this.name = name;
		this.players = new HashSet<String>();
		this.deadPlayers = new HashSet<String>();
		this.admins = new HashSet<String>();
		this.started = false;
	}

	/**
	 * Gets the name of this session
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets all the admins in this session
	 * 
	 * @return Players in this session
	 */
	public Set<String> getAdmins() {
		return admins;
	}

	/**
	 * Gets all the players still alive in this session
	 * 
	 * @return Players in this session
	 */
	public Set<String> getPlayers() {
		return players;
	}

	/**
	 * Gets all the players who died in this session
	 * 
	 * @return Players in this session
	 */
	public Set<String> getDeadPlayers() {
		return deadPlayers;
	}

	/**
	 * Gets all the players in this session, dead or alive
	 * 
	 * @return Players in this session
	 */
	public Set<String> getAllPlayers() {
		Set<String> result = new HashSet<String>();
		for (String player : deadPlayers) {
			result.add(player);
		}
		for (String player : players) {
			result.add(player);
		}
		return result;
	}

	/**
	 * Adds a player to the session
	 * 
	 * @param player
	 *            Player to add
	 */
	public void addPlayer(Player player) {
		addPlayer(player.getName());
	}

	/**
	 * Adds a player to the session
	 * 
	 * @param player
	 *            Player to add
	 */
	public void addPlayer(String player) {
		if (HGConfig.MAX_PLAYERS != -1
				&& players.size() == HGConfig.MAX_PLAYERS) {
			return;
		}
		plugin.getBroadcaster().alertEveryone(
				player + " has joined the Hunger Games!");
		this.players.add(player);
	}

	/**
	 * Removes a player to the session
	 * 
	 * @param player
	 *            Player to remove
	 */
	public void removePlayer(Player player) {
		removePlayer(player.getName());
	}

	/**
	 * Removes a player to the session
	 * 
	 * @param player
	 *            Player to remove
	 */
	public void removePlayer(String player) {
		this.players.remove(player);
	}

	/**
	 * Adds a collection of players
	 * 
	 * @param players
	 *            Players to add
	 */
	public void addAllPlayers(Collection<String> players) {
		for (String player : players) {
			addPlayer(player);
		}
	}

	/**
	 * Removes a collection of players
	 * 
	 * @param players
	 *            Players to remove
	 */
	public void removeAllPlayers(Collection<String> players) {
		for (String player : players) {
			removePlayer(player);
		}
	}

	/**
	 * Adds an admin to the game
	 * 
	 * @param admin
	 *            Player to add
	 */
	public void addAdmin(Player admin) {
		addAdmin(admin.getName());
	}

	/**
	 * Adds an admin to the game
	 * 
	 * @param admin
	 *            Player to add
	 */
	public void addAdmin(String admin) {
		this.admins.add(admin);
	}

	/**
	 * Removes an admin to the game
	 * 
	 * @param admin
	 *            Player to remove
	 */
	public void removeAdmin(Player admin) {
		removeAdmin(admin.getName());
	}

	/**
	 * Removes an admin to the game
	 * 
	 * @param admin
	 *            Player to remove
	 */
	public void removeAdmin(String admin) {
		this.admins.remove(admin);
	}

	/**
	 * Adds a collection of admins
	 * 
	 * @param players
	 *            Players to add
	 */
	public void addAllAdmins(Collection<String> players) {
		for (String player : players) {
			addAdmin(player);
		}
	}

	/**
	 * Removes a collection of admins
	 * 
	 * @param players
	 *            Players to remove
	 */
	public void removeAllAdmins(Collection<String> players) {
		for (String player : players) {
			removeAdmin(player);
		}
	}

	/**
	 * Checks if a player is an admin in the session
	 * 
	 * @param player
	 *            Player to check
	 * @return If the player is in this session
	 */
	public boolean isAdmin(Player player) {
		return isAdmin(player.getName());
	}

	/**
	 * Checks if a player is an adminin the session
	 * 
	 * @param player
	 *            Player to check
	 * @return If the player is in this session
	 */
	public boolean isAdmin(String player) {
		return admins.contains(player);
	}

	/**
	 * Checks if a collection of players are admins in the session
	 * 
	 * @param players
	 *            Players to check
	 * @return If the players are in the session
	 */
	public boolean areAdmins(Collection<String> players) {
		for (String player : players) {
			if (!(isAdmin(player))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if a player is in the session
	 * 
	 * @param player
	 *            Player to check
	 * @return If the player is in this session
	 */
	public boolean isPlayer(Player player) {
		return isPlayer(player.getName());
	}

	/**
	 * Checks if a player is in the session
	 * 
	 * @param player
	 *            Player to check
	 * @return If the player is in this session
	 */
	public boolean isPlayer(String player) {
		return players.contains(player);
	}

	/**
	 * Checks if a collection of players are in the session
	 * 
	 * @param players
	 *            Players to check
	 * @return If the players are in the session
	 */
	public boolean arePlayers(Collection<String> players) {
		for (String player : players) {
			if (!(isPlayer(player))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets if the session has been started
	 * 
	 * @return If the session has been started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * Starts the session
	 * 
	 * @return Successful
	 */
	public boolean start() {
		if (started) {
			return false;
		}
		if (HGConfig.MIN_PLAYERS != -1 && players.size() < HGConfig.MIN_PLAYERS) {
			return false;
		}
		started = true;
		plugin.getBroadcaster().alertEveryone(
				ChatColor.GREEN + "The game has been started!");
		if (HGConfig.BROADCAST_PLAYERS_LEFT) {
			taskId = plugin.getServer().getScheduler()
					.scheduleSyncRepeatingTask(
							plugin,
							new Runnable() {
								public void run() {
									plugin.getBroadcaster()
											.alertEveryoneOfRemainingPlayers();
								}
							}, HGConfig.BROADCAST_PLAYERS_LEFT_EVERY * 20 * 60,
							HGConfig.BROADCAST_PLAYERS_LEFT_EVERY * 20 * 60);
		}
		return true;
	}

	/**
	 * Stops the session
	 * 
	 * @return Successful
	 */
	public boolean stop() {
		if (!(started)) {
			return false;
		}
		started = false;
		plugin.getBroadcaster().alertEveryone(
				ChatColor.RED + "The game is over!");
		if (taskId != -1) {
			plugin.getServer().getScheduler().cancelTask(taskId);
			taskId = -1;
		}
		return true;
	}

	/**
	 * Called when a player respawns
	 * 
	 * @param player
	 *            Player that respawned
	 */
	public void onPlayerRespawn(Player player) {
		player.setAllowFlight(true);
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			p.hidePlayer(player);
		}
		final String name = player.getName();
		players.remove(name);
		deadPlayers.add(name);
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						plugin.getBroadcaster().alertEveryone(
								name + " has died!");
						plugin.getBroadcaster()
								.alertEveryoneOfRemainingPlayers();
						checkStatus();
					}
				}, 2);
	}

	/**
	 * Checks if the game should be ended
	 */
	public void checkStatus() {
		if (players.size() < 2) {
			stop();
		}
	}

}
