package com.tips48.hungergames.dynamic;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.tips48.hungergames.HungerGames;

public class RandomPotions {
	@SuppressWarnings("unused")
	private HungerGames plugin;
	private Random rand;
	private int applyChance;

	public RandomPotions(HungerGames plugin) {
		this.plugin = plugin;
		this.rand = new Random();
		this.applyChance = ((this.rand.nextInt(50) + 25) * 1234);
	}

	public void handlePlayerMoveEvent(Player player) {
		if (applyEffect(player)) {
			player.addPotionEffect(getEffect());
		}
	}

	private boolean applyEffect(Player player) {
		return (rand.nextInt(applyChance * player.getName().length()) == 0);
	}

	private PotionEffect getEffect() {
		if (rand.nextBoolean()) {
			if (rand.nextBoolean()) {
				return new PotionEffect(PotionEffectType.BLINDNESS, 1000, 2);
			} else {
				return new PotionEffect(PotionEffectType.SPEED, 1000, 2);
			}
		} else {
			if (rand.nextBoolean()) {
				return new PotionEffect(PotionEffectType.SLOW, 1000, 2);
			} else {
				return new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,
						1000, 2);
			}
		}
	}
}
