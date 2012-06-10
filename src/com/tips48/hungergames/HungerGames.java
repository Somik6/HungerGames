package com.tips48.hungergames;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tips48.hungergames.commands.InfoCommand;
import com.tips48.hungergames.commands.StartCommand;
import com.tips48.hungergames.commands.StopCommand;
import com.tips48.hungergames.dynamic.ItemGiver;
import com.tips48.hungergames.dynamic.MobSpawner;
import com.tips48.hungergames.listeners.PlayerDeathListener;
import com.tips48.hungergames.listeners.PlayerJoinListener;
import com.tips48.hungergames.listeners.PlayerKickListener;
import com.tips48.hungergames.listeners.PlayerMoveListener;
import com.tips48.hungergames.listeners.PlayerQuitListener;
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
	private PlayerDeathListener deathListener;
	// Commands
	private StartCommand startCommand;
	private StopCommand stopCommand;
	private InfoCommand infoCommand;
	// Various utils
	private ItemGiver itemGiver;
	private MobSpawner mobSpawner;
	private Broadcaster broadcaster;

	@Override
	public void onLoad() {
		gameManager = new GameManager(this);
		gameManager.createSession("Hunger Games", null, null);

		itemGiver = new ItemGiver(this);
		mobSpawner = new MobSpawner(this);
		broadcaster = new Broadcaster(this);

		joinListener = new PlayerJoinListener(this);
		moveListener = new PlayerMoveListener(this);
		quitListener = new PlayerQuitListener(this);
		kickListener = new PlayerKickListener(this);
		deathListener = new PlayerDeathListener(this);

		startCommand = new StartCommand(this);
		stopCommand = new StopCommand(this);
		infoCommand = new InfoCommand(this);
	}

	@Override
	public void onEnable() {
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
		manager.registerEvents(deathListener, this);
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

}
