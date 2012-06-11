package com.tips48.hungergames;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tips48.hungergames.commands.AdminCommand;
import com.tips48.hungergames.commands.InfoCommand;
import com.tips48.hungergames.commands.ReloadCommand;
import com.tips48.hungergames.commands.StartCommand;
import com.tips48.hungergames.commands.StopCommand;
import com.tips48.hungergames.config.ConfigManager;
import com.tips48.hungergames.dynamic.ItemGiver;
import com.tips48.hungergames.dynamic.MobSpawner;
import com.tips48.hungergames.dynamic.RandomPotions;
import com.tips48.hungergames.listeners.BlockBreakListener;
import com.tips48.hungergames.listeners.BlockPlaceListener;
import com.tips48.hungergames.listeners.PlayerChatListener;
import com.tips48.hungergames.listeners.PlayerDamageListener;
import com.tips48.hungergames.listeners.PlayerJoinListener;
import com.tips48.hungergames.listeners.PlayerKickListener;
import com.tips48.hungergames.listeners.PlayerMoveListener;
import com.tips48.hungergames.listeners.PlayerQuitListener;
import com.tips48.hungergames.listeners.PlayerRespawnListener;
import com.tips48.hungergames.utils.Broadcaster;

/**
 * Main class of the plugin
 * 
 * @author tips48
 */
public class HungerGames extends JavaPlugin {
	// Game manager
	private GameManager gameManager;
	// Listeners
	private PlayerJoinListener joinListener;
	private PlayerMoveListener moveListener;
	private PlayerQuitListener quitListener;
	private PlayerKickListener kickListener;
	private PlayerRespawnListener respawnListener;
	private PlayerChatListener chatListener;
	private PlayerDamageListener damageListener;

	private BlockBreakListener breakListener;
	private BlockPlaceListener placeListener;
	// Commands
	private StartCommand startCommand;
	private StopCommand stopCommand;
	private InfoCommand infoCommand;
	private ReloadCommand reloadCommand;
	private AdminCommand adminCommand;
	// Various utils
	private ItemGiver itemGiver;
	private MobSpawner mobSpawner;
	private Broadcaster broadcaster;
	private RandomPotions randomPotions;

	@Override
	public void onLoad() {
		gameManager = new GameManager(this);
		gameManager.createSession("Hunger Games", null, null);

		itemGiver = new ItemGiver(this);
		mobSpawner = new MobSpawner(this);
		broadcaster = new Broadcaster(this);
		randomPotions = new RandomPotions(this);

		joinListener = new PlayerJoinListener(this);
		moveListener = new PlayerMoveListener(this);
		quitListener = new PlayerQuitListener(this);
		kickListener = new PlayerKickListener(this);
		respawnListener = new PlayerRespawnListener(this);
		chatListener = new PlayerChatListener(this);
		damageListener = new PlayerDamageListener(this);

		breakListener = new BlockBreakListener(this);
		placeListener = new BlockPlaceListener(this);

		startCommand = new StartCommand(this);
		stopCommand = new StopCommand(this);
		infoCommand = new InfoCommand(this);
		reloadCommand = new ReloadCommand(this);
		adminCommand = new AdminCommand(this);
	}

	@Override
	public void onEnable() {
		ConfigManager.init(this);
		registerEvents();
		registerCommands();
	}

	/**
	 * Registers all the events for the plugin
	 */
	private void registerEvents() {
		PluginManager manager = this.getServer().getPluginManager();

		manager.registerEvents(joinListener, this);
		manager.registerEvents(moveListener, this);
		manager.registerEvents(quitListener, this);
		manager.registerEvents(kickListener, this);
		manager.registerEvents(respawnListener, this);
		manager.registerEvents(chatListener, this);
		manager.registerEvents(damageListener, this);

		manager.registerEvents(placeListener, this);
		manager.registerEvents(breakListener, this);
	}

	/**
	 * Registers all the commands for the plugin
	 */
	private void registerCommands() {
		getCommand("hgstart").setExecutor(startCommand);
		getCommand("hgstop").setExecutor(stopCommand);
		getCommand("hginfo").setExecutor(infoCommand);
		getCommand("hgreload").setExecutor(reloadCommand);
		getCommand("hgadmin").setExecutor(adminCommand);
	}

	@Override
	public void onDisable() {
	}

	/**
	 * Gets the GameManager
	 * 
	 * @return GameManager
	 */
	public GameManager getGameManager() {
		return gameManager;
	}

	/**
	 * Gets the ItemGiver
	 * 
	 * @return ItemGiver
	 */
	public ItemGiver getItemGiver() {
		return itemGiver;
	}

	/**
	 * Gets the MobSpawner
	 * 
	 * @return MobSpawner
	 */
	public MobSpawner getMobSpawner() {
		return mobSpawner;
	}

	/**
	 * Gets the Broadcaster
	 * 
	 * @return Broadcaster
	 */
	public Broadcaster getBroadcaster() {
		return broadcaster;
	}

	/**
	 * Gets the RandomPotions
	 * 
	 * @return RandomPotions
	 */
	public RandomPotions getRandomPotions() {
		return randomPotions;
	}

}
