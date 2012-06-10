package com.tips48.hungergames.dynamic;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.tips48.hungergames.HungerGames;

/**
 * Randomly applies potion effects to users
 * 
 * @author Nicholas Krecklow
 * 
 */
public class RandomPotions {
	@SuppressWarnings("unused")
	private HungerGames plugin;
	private Random rand;
	private int applyChance;

	/**
	 * Creates a new RandomPotions
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public RandomPotions(HungerGames plugin) {
		this.plugin = plugin;
		this.rand = new Random();
		this.applyChance = ((this.rand.nextInt(50) + 25) * 1234);
	}

	/**
	 * Handles a PlayerMoveEvent
	 * 
	 * @param player
	 *            Player who moved
	 */
	public void handlePlayerMoveEvent(Player player) {
		if (applyEffect(player)) {
			player.getActivePotionEffects().clear();
			player.addPotionEffect(getEffect());
		}
	}

	/**
	 * Checks if a potion effect should be applied.
	 * 
	 * @param name
	 *            Player
	 * @return Item should be given
	 */
	private boolean applyEffect(Player player) {
		return (rand.nextInt(applyChance * player.getName().length()) == 0);
	}

	/**
	 * Gets the next effect to be applied
	 * 
	 * @return Next effect
	 */
	private PotionEffect getEffect() {
		int timeLength = (rand.nextInt(10) + 1) * 1000;
		int amplifier = rand.nextInt(3) + 1;

		if (rand.nextBoolean()) {
			if (rand.nextBoolean()) {
				return new PotionEffect(PotionEffectType.BLINDNESS, timeLength,
						amplifier);
			} else {
				return new PotionEffect(PotionEffectType.SPEED, timeLength,
						amplifier);
			}
		} else {
			if (rand.nextBoolean()) {
				return new PotionEffect(PotionEffectType.SLOW, timeLength,
						amplifier);
			} else {
				return new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,
						timeLength, amplifier);
			}
		}
	}
}
