package com.tips48.hungergames.dynamic;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tips48.hungergames.HungerGames;
import com.tips48.hungergames.config.ConfigManager;

/**
 * Randomly gives items to players
 * 
 * @author steaks4uce
 * 
 */
public class ItemGiver {
	private HungerGames plugin;
	private Random rand;

	/**
	 * Creates a new ItemGiver
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public ItemGiver(HungerGames plugin) {
		this.plugin = plugin;
		this.rand = new Random();
	}

	/**
	 * Handles a PlayerMoveEvent
	 * 
	 * @param player
	 *            Player who moved
	 */
	public void handlePlayerMoveEvent(Player player) {
		if (!(ConfigManager.RANDOM_ITEMS)) {
			return;
		}
		if (shouldGiveItem(player.getName())) {
			ItemStack givenItem = getItem();
			player.getInventory().addItem(givenItem);
			player.sendMessage(plugin.getBroadcaster().styleMessage(
					"You were given a gift by the game masters!"));
		}
	}

	/**
	 * Checks if an item should be given
	 * 
	 * @param name
	 *            Players name
	 * @return Item should be given
	 */
	private boolean shouldGiveItem(String name) {
		return ((rand.nextInt(ConfigManager.RANDOM_ITEMS_CHANCE)) == 0);
	}

	/**
	 * Gets the next item that should be given
	 * 
	 * @return Next item
	 */
	private ItemStack getItem() {
		if (rand.nextBoolean()) {
			if (rand.nextBoolean()) {
				return ConfigManager.RANDOM_ITEM_LIST.get(0);
			}
			return ConfigManager.RANDOM_ITEM_LIST.get(1);
		} else {
			if (rand.nextBoolean()) {
				return ConfigManager.RANDOM_ITEM_LIST.get(2);
			}
			return ConfigManager.RANDOM_ITEM_LIST.get(3);
		}
	}
}