package com.tips48.hungergames.dynamic;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tips48.hungergames.HungerGames;

public class ItemGiver {
	@SuppressWarnings("unused")
	private HungerGames plugin;
	private int itemGiveChance;
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
		this.itemGiveChance = ((this.rand.nextInt(50) + 25) * 1234);
	}

	/**
	 * Handles a PlayerMoveEvent
	 * 
	 * @param player
	 *            Player who moved
	 */
	public void handlePlayerMoveEvent(Player player) {
		if (shouldGiveItem(player.getName())) {
			player.getInventory().addItem(getItem());
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
		return ((rand.nextInt(itemGiveChance) * name.length()) == 0);
	}

	/**
	 * Gets the next item that should be given
	 * 
	 * @return Next item
	 */
	private ItemStack getItem() {
		if (rand.nextBoolean()) {
			if (rand.nextBoolean()) {
				return new ItemStack(Material.STONE_SWORD, 1);
			} else {
				return new ItemStack(Material.RAW_BEEF, 1);
			}
		} else {
			if (rand.nextBoolean()) {
				return new ItemStack(Material.COOKED_BEEF, 1);
			} else {
				return new ItemStack(Material.WOOD_SWORD, 1);
			}
		}
	}
}