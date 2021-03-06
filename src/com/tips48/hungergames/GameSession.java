package com.tips48.hungergames;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tips48.hungergames.config.ConfigManager;
import com.tips48.hungergames.utils.EventUtils;

/**
 * Stores information about one session of
 * 
 * @author tips48
 * 
 */
public class GameSession {
	private final HungerGames plugin;

	private final String name;
	
	private final String creator;

	private final Set<String> players;
	private final Set<String> deadPlayers;
	private final Set<String> admins;
	
	private final List<Location> locations;

	private boolean started;
	private boolean constructed;

	private int taskId = -1;

	/**
	 * Creates a new GameSession
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public GameSession(HungerGames plugin, String name, String creator) {
		this.plugin = plugin;
		this.name = name;
		this.creator = creator;
		this.players = new HashSet<String>();
		this.deadPlayers = new HashSet<String>();
		this.admins = new HashSet<String>();
		this.locations = new ArrayList<Location>();
		this.started = false;
		this.constructed = false;
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
	 * Gets the name of the player who created this session
	 * @return Creator
	 */
	public String getCreator() {
		return creator;
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
	 * Gets a list of locations where the player could spawn
	 * @return Locations
	 */
	public List<Location> getSpawnLocations() {
		return locations;
	}
	
	/**
	 * Adds a location to the spawn location pool
	 * @param loc Location to add
	 */
	public void addSpawnLocation(Location loc) {
		locations.add(loc);
		if (ConfigManager.TELEPORT_ON_JOIN && ConfigManager.RANDOM_TELEPORT) {
			setConstructed(true);
		}
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
	 * Gets if the session is ready for joining
	 * @return Constructed
	 */
	public boolean isConstructed() {
		return constructed;
	}
	
	/**
	 * Sets if the session is constructed or not
	 * @param constructed If constructed
	 */
	public void setConstructed(boolean constructed) {
		this.constructed = constructed;
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
		if (ConfigManager.MAX_PLAYERS != -1
				&& players.size() == ConfigManager.MAX_PLAYERS) {
			return;
		}
		if (ConfigManager.ADMINS.contains(player) || player.equalsIgnoreCase(creator)) {
			addAdmin(player);
			return;
		}
		if (plugin.getGameManager().getGameSessionOfPlayer(player) != null) {
			return;
		}
		plugin.getBroadcaster().alertEveryone(this,
				player + " has joined the Hunger Games!");
		this.players.add(player);
		Player p = plugin.getServer().getPlayer(player);
		if (p == null) {
			return;
		}
		if (ConfigManager.TELEPORT_ON_JOIN) {
			if (ConfigManager.RANDOM_TELEPORT) {
				p.teleport(EventUtils.getRandomSpawnLocation(locations.get(0)));
			} else {
				int playerSize = players.size();
				int locSize = locations.size();
				if (playerSize > locSize) {
					int result = playerSize;
					while (result > locSize) {
						result -= locSize;
					}
					p.teleport(locations.get(result - 1));
				} else {
					p.teleport(locations.get(playerSize - 1));
				}
			}
		}
		p.setGameMode(GameMode.SURVIVAL);
		if (ConfigManager.CLEAR_INVENTORY) {
			p.getInventory().clear();
		}
		p.getInventory().addItem(new ItemStack(Material.STICK, 1));
		if (ConfigManager.MAX_PLAYERS != -1) {
		    if (ConfigManager.START_ON_FULL && players.size() == ConfigManager.MAX_PLAYERS) {
		        start();
		    }
		}
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
		checkStatus();
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
		if (plugin.getGameManager().getGameSessionOfPlayer(admin) != null) {
			return;
		}
		if (players.contains(admin)) {
			players.remove(admin);
		}
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
		return players.contains(player) || deadPlayers.contains(player);
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
	 * @return If successful
	 */
	public boolean start() {
		if (started) {
			return false;
		}
		if (ConfigManager.MIN_PLAYERS != -1
				&& players.size() < ConfigManager.MIN_PLAYERS) {
			return false;
		}
		if (players.size() < 2) {
			return false;
		}
		for (String player : getAllPlayers()) {
			Player p = plugin.getServer().getPlayer(player);
			if (p == null) {
				continue;
			}
			p.setExhaustion(0F);
			p.setHealth(20);
			p.setFoodLevel(20);
		}
		started = true;
		plugin.getBroadcaster().alertEveryone(this,
				"The game has been started!");
		if (ConfigManager.BROADCAST_PLAYERS_LEFT) {
			taskId = plugin
					.getServer()
					.getScheduler()
					.scheduleSyncRepeatingTask(
							plugin,
							new PlayersLeftNotifier(plugin, this),
							ConfigManager.BROADCAST_PLAYERS_LEFT_EVERY * 20 * 60,
							ConfigManager.BROADCAST_PLAYERS_LEFT_EVERY * 20 * 60);
		}
		return true;
	}

	/**
	 * Stops the session
	 * 
	 * @return If successful
	 */
	public boolean stop() {
		if (!(started)) {
			return false;
		}
		started = false;
		plugin.getBroadcaster().alertEveryone(this, "The game is over!");
		if (taskId != -1) {
			plugin.getServer().getScheduler().cancelTask(taskId);
			taskId = -1;
		}
		for (String player : deadPlayers) {
			Player p = plugin.getServer().getPlayer(player);
			if (p == null) {
				continue;
			}
			p.setAllowFlight(false);
			for (Player ply : plugin.getServer().getOnlinePlayers()) {
				p.showPlayer(ply);
			}
		}
		for (String player : getAllPlayers()) {
			removePlayer(player);
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
			if (!(admins.contains(p.getName()))) {
				p.hidePlayer(player);
			}
		}
		String name = player.getName();
		players.remove(name);
		deadPlayers.add(name);
		plugin.getServer()
				.getScheduler()
				.scheduleSyncDelayedTask(plugin,
						new DeadNotifier(plugin, this, name), 2);
	}

	/**
	 * Checks if the game should be ended
	 */
	public void checkStatus() {
		if (players.size() < 2) {
			stop();
		}
	}

	/**
	 * Notifies all the players of who just died
	 * 
	 * @author tips48
	 * 
	 */
	private final static class DeadNotifier implements Runnable {
		private final HungerGames plugin;
		private final GameSession session;
		private final String player;

		/**
		 * Creates a new DeadNotifier
		 * 
		 * @param plugin
		 *            Plugin instance
		 * @param session
		 *            Session instance
		 * @param player
		 *            Player to notify
		 */
		public DeadNotifier(HungerGames plugin, GameSession session,
				String player) {
			this.plugin = plugin;
			this.session = session;
			this.player = player;
		}

		@Override
		public void run() {
			plugin.getBroadcaster().alertEveryone(session,
					player + " has died!");
			plugin.getBroadcaster().alertEveryoneOfRemainingPlayers(session);
			session.checkStatus();
		}
	}

	/**
	 * Notifies all the players of who is dead
	 * 
	 * @author tips48
	 * 
	 */
	private final static class PlayersLeftNotifier implements Runnable {
		private final HungerGames plugin;
		private final GameSession session;

		/**
		 * Creates a new PlayerLeftNotifier
		 * 
		 * @param plugin
		 *            Plugin instance
		 * @param session
		 *            Session instance
		 */
		public PlayersLeftNotifier(HungerGames plugin, GameSession session) {
			this.plugin = plugin;
			this.session = session;
		}

		@Override
		public void run() {
			plugin.getBroadcaster().alertEveryoneOfRemainingPlayers(session);
		}
	}

}
