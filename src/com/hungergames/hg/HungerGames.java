package com.hungergames.hg;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.hungergames.hg.commands.InfoCommand;
import com.hungergames.hg.commands.StartCommand;
import com.hungergames.hg.commands.StopCommand;
import com.hungergames.hg.config.HGConfig;
import com.hungergames.hg.dynamic.ItemGiver;
import com.hungergames.hg.dynamic.MobSpawner;
import com.hungergames.hg.dynamic.RandomPotions;
import com.hungergames.hg.listeners.BlockBreakListener;
import com.hungergames.hg.listeners.BlockPlaceListener;
import com.hungergames.hg.listeners.PlayerChatListener;
import com.hungergames.hg.listeners.PlayerJoinListener;
import com.hungergames.hg.listeners.PlayerKickListener;
import com.hungergames.hg.listeners.PlayerMoveListener;
import com.hungergames.hg.listeners.PlayerQuitListener;
import com.hungergames.hg.listeners.PlayerRespawnListener;
import com.hungergames.hg.utils.Broadcaster;

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

	private BlockBreakListener breakListener;
	private BlockPlaceListener placeListener;
	// Commands
	private StartCommand startCommand;
	private StopCommand stopCommand;
	private InfoCommand infoCommand;
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

		breakListener = new BlockBreakListener(this);
		placeListener = new BlockPlaceListener(this);

		startCommand = new StartCommand(this);
		stopCommand = new StopCommand(this);
		infoCommand = new InfoCommand(this);
	}

	@Override
	public void onEnable() {
		HGConfig.init(this);
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

	public RandomPotions getRandomPotions() {
		return randomPotions;
	}

}
