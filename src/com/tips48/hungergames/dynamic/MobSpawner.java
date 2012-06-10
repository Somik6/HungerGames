package com.tips48.hungergames.dynamic;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.tips48.hungergames.HungerGames;

/**
 * Randomly spawns mobs near a player
 * 
 * @author tips48
 * 
 */
public class MobSpawner {
	private int mobSpawnChance;
	private Random rand;
	private HungerGames plugin;

	/**
	 * Creates a new MobSpawner
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public MobSpawner(HungerGames plugin) {
		this.rand = new Random();
		this.mobSpawnChance = ((rand.nextInt(50) + 25) * 1234);
		this.plugin = plugin;
	}

	/**
	 * Handles a PlayerMoveEvent
	 * 
	 * @param player
	 *            Player who moved
	 */
	public void handlePlayerMoveEvent(Player player) {
		if (shouldSpawnMob(player)
				&& plugin.getGameManager().getGameSession().isPlayer(player)) {
			player.getWorld().spawnCreature(getLocation(player), getEntity());
		}
	}

	/**
	 * Checks if a mob should be spawned
	 * 
	 * @param player
	 *            Player to check
	 * @return Mob should be spawned
	 */
	private boolean shouldSpawnMob(Player player) {
		return (rand.nextInt(mobSpawnChance * player.getName().length()) == 0);
	}

	/**
	 * Gets the next entity that should be spawned
	 * 
	 * @return Entity
	 */
	private EntityType getEntity() {
		if (rand.nextBoolean()) {
			if (rand.nextBoolean()) {
				return EntityType.CREEPER;
			}
			return EntityType.SKELETON;
		} else {
			if (rand.nextBoolean()) {
				return EntityType.ZOMBIE;
			}
			return EntityType.CAVE_SPIDER;
		}
	}

	/**
	 * Gets the next location where an entity should be spawned
	 * 
	 * @param player
	 *            Player to get the location from
	 * @return Location
	 */
	private Location getLocation(Player player) {
		int addX = rand.nextInt(4), addZ = rand.nextInt(4);
		Location playerLoc = player.getLocation();

		if (rand.nextBoolean()) {
			addX = -addX;
		}

		if (rand.nextBoolean()) {
			addZ = -addZ;
		}

		return (playerLoc.add(addX, 0, addZ));
	}
}