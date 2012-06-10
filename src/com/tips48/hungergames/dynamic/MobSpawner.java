package com.tips48.hungergames.dynamic;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.tips48.hungergames.HungerGames;

public class MobSpawner {
	private int mobSpawnChance;
	private Random rand;
	private HungerGames plugin;

	public MobSpawner(HungerGames plugin) {
		this.rand = new Random();
		this.mobSpawnChance = ((rand.nextInt(50) + 25) * 1234);
		this.plugin = plugin;
	}

	public void handlePlayerMoveEvent(Player player) {
		if (shouldSpawnMob(player)
				&& plugin.getGameManager().getGameSession().isPlayer(player)) {
			player.getWorld().spawnCreature(getLocation(player),
					getEntity(player));
		}
	}

	private boolean shouldSpawnMob(Player player) {
		return (rand.nextInt(mobSpawnChance * player.getName().length()) == 0);
	}

	private EntityType getEntity(Player player) {
		if (rand.nextBoolean()) {
			if (rand.nextBoolean()) {
				return EntityType.CREEPER;
			} else {
				return EntityType.SKELETON;
			}
		} else {
			if (rand.nextBoolean()) {
				return EntityType.ZOMBIE;
			} else {
				return EntityType.CAVE_SPIDER;
			}
		}
	}

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