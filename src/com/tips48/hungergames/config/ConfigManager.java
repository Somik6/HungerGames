package com.tips48.hungergames.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

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
	 * If mobs should be randomly spawned
	 */
	public static boolean RANDOM_MOBS;
	/**
	 * The chance that a mob will be randomly spawned
	 */
	public static int RANDOM_MOBS_CHANCE;

	/**
	 * If items should be randomly spawned
	 */
	public static boolean RANDOM_ITEMS;
	/**
	 * The chance that an item will be randomly spawned
	 */
	public static int RANDOM_ITEMS_CHANCE;
	/**
	 * A list of items that could be spawned
	 */
	public static List<ItemStack> RANDOM_ITEM_LIST;
	
	
	/**
	 * If potions should be randomly spawned
	 */
	public static boolean RANDOM_POTIONS;
	/**
	 * The chance that a potion will be randomly spawned
	 */
	public static int RANDOM_POTIONS_CHANCE;

	/**
	 * Inits the configuration
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public static void init(HungerGames plugin) {
		File configFile = new File(plugin.getDataFolder(), "config.yml");
		if (!(configFile.exists())) {
			plugin.saveResource("config.yml", false);
		}
		YamlConfiguration config = YamlConfiguration
				.loadConfiguration(configFile);

		MIN_PLAYERS = config.getInt("HungerGames.minPlayers");
		MAX_PLAYERS = config.getInt("HungerGames.maxPlayers");

		
		BROADCAST_PLAYERS_LEFT = config
				.getBoolean("HungerGames.broadcastPlayersLeft");
		BROADCAST_PLAYERS_LEFT_EVERY = config
				.getInt("HungerGames.broadcastPlayersLeftEvery");

		
		ADMINS = config.getStringList("HungerGames.administrators");

		RANDOM_MOBS = config.getBoolean("HungerGames.random.randomMobs");
		RANDOM_MOBS_CHANCE = config
				.getInt("HungerGames.random.randomMobsChance");

		
		RANDOM_ITEMS = config.getBoolean("HungerGames.random.randomItems");
		RANDOM_ITEMS_CHANCE = config
				.getInt("HungerGames.random.randomItemsChance");
		List<String> rawItems = config
				.getStringList("HungerGames.random.randomItemsList");
		RANDOM_ITEM_LIST = parseItems(rawItems);
		
		
		RANDOM_POTIONS = config.getBoolean("HungerGames.random.randomPotions");
		RANDOM_POTIONS_CHANCE = config.getInt("HungerGames.random.randomPotionsChance");
	}

	/**
	 * Parses a raw List<String> into a List<ItemStack>
	 * @param raw List to parse
	 * @return List with ItemStack's
	 */
	private static List<ItemStack> parseItems(List<String> raw) {
		List<ItemStack> result = new ArrayList<ItemStack>(raw.size());
		for (String string : raw) {
			String[] split = string.split(",");
			if (split.length == 0 || split.length == 1) {
				result.add(new ItemStack(Integer.parseInt(string)));
			} else if (split.length == 2) {
				result.add(new ItemStack(Integer.parseInt(split[0]), Integer
						.parseInt(split[1])));
			} else if (split.length == 3) {
				result.add(new ItemStack(Integer.parseInt(split[0]), Integer
						.parseInt(split[1]), (short) 0, Byte
						.parseByte(split[2])));
			}
		}
		return result;
	}

}
