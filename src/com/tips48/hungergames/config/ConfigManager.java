package com.tips48.hungergames.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import com.tips48.hungergames.HungerGames;

public class ConfigManager {
	/**
	 * Minimum amount of players before the game is started
	 */
	public static int MIN_PLAYERS;
	/**
	 * Maximum amount of players before the game is started
	 */
	public static int MAX_PLAYERS;
	
	/**
	 * If how many players left should be broadcasted
	 */
	public static boolean BROADCAST_PLAYERS_LEFT;
	/**
	 * How often how many players left should be broadcasted
	 */
	public static int BROADCAST_PLAYERS_LEFT_EVERY;
	
	/**
	 * List of users that are always administrators
	 */
	public static List<String> ADMINS;
	
	/**
	 * Inits the configuration
	 * @param plugin Plugin instance
	 */
	public static void init(HungerGames plugin) {
		File configFile = new File(plugin.getDataFolder(), "config.yml");
		if (!(configFile.exists())) {
			File parent = configFile.getParentFile();
			if (!(parent.exists())) {
				parent.mkdir();
			}
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		
		config.options().copyDefaults(true);
		
		MIN_PLAYERS = config.getInt("HungerGames.minPlayers");
		MAX_PLAYERS = config.getInt("HungerGames.maxPlayers");
		
		BROADCAST_PLAYERS_LEFT = config.getBoolean("HungerGames.broadcastPlayersLeft");
		BROADCAST_PLAYERS_LEFT_EVERY = config.getInt("HungerGames.broadcastPlayersLeftEvery");	
		
		ADMINS = config.getStringList("HungerGames.administrators");
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
