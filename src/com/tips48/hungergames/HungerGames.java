package com.tips48.hungergames;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tips48.hungergames.commands.AdminCommand;
import com.tips48.hungergames.commands.CreateCommand;
import com.tips48.hungergames.commands.DeleteCommand;
import com.tips48.hungergames.commands.FinishCommand;
import com.tips48.hungergames.commands.HelpCommand;
import com.tips48.hungergames.commands.HungerGamesCommand;
import com.tips48.hungergames.commands.InfoCommand;
import com.tips48.hungergames.commands.JoinCommand;
import com.tips48.hungergames.commands.LeaveCommand;
import com.tips48.hungergames.commands.ListCommand;
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
import com.tips48.hungergames.listeners.PlayerInteractListener;
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
	private PlayerMoveListener moveListener;
	private PlayerQuitListener quitListener;
	private PlayerKickListener kickListener;
	private PlayerRespawnListener respawnListener;
	private PlayerChatListener chatListener;
	private PlayerDamageListener damageListener;
	private PlayerInteractListener interactListener;

	private BlockBreakListener breakListener;
	private BlockPlaceListener placeListener;
	// Commands
	private StartCommand startCommand;
	private StopCommand stopCommand;
	private InfoCommand infoCommand;
	private ReloadCommand reloadCommand;
	private AdminCommand adminCommand;
	private ListCommand listCommand;
	private CreateCommand createCommand;
	private FinishCommand finishCommand;
	private DeleteCommand deleteCommand;
	private JoinCommand joinCommand;
	private LeaveCommand leaveCommand;
	private HungerGamesCommand hgCommand;
	private HelpCommand helpCommand;
	// Various utils
	private ItemGiver itemGiver;
	private MobSpawner mobSpawner;
	private Broadcaster broadcaster;
	private RandomPotions randomPotions;

	@Override
	public void onLoad() {
		gameManager = new GameManager(this);

		itemGiver = new ItemGiver(this);
		mobSpawner = new MobSpawner(this);
		broadcaster = new Broadcaster(this);
		randomPotions = new RandomPotions(this);

		moveListener = new PlayerMoveListener(this);
		quitListener = new PlayerQuitListener(this);
		kickListener = new PlayerKickListener(this);
		respawnListener = new PlayerRespawnListener(this);
		chatListener = new PlayerChatListener(this);
		damageListener = new PlayerDamageListener(this);
		interactListener = new PlayerInteractListener(this);

		breakListener = new BlockBreakListener(this);
		placeListener = new BlockPlaceListener(this);

		startCommand = new StartCommand(this);
		stopCommand = new StopCommand(this);
		infoCommand = new InfoCommand(this);
		reloadCommand = new ReloadCommand(this);
		adminCommand = new AdminCommand(this);
		listCommand = new ListCommand(this);
		createCommand = new CreateCommand(this);
		finishCommand = new FinishCommand(this);
		deleteCommand = new DeleteCommand(this);
		joinCommand = new JoinCommand(this);
		leaveCommand = new LeaveCommand(this);
		hgCommand = new HungerGamesCommand(this);
		helpCommand = new HelpCommand(this);
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

		manager.registerEvents(moveListener, this);
		manager.registerEvents(quitListener, this);
		manager.registerEvents(kickListener, this);
		manager.registerEvents(respawnListener, this);
		manager.registerEvents(chatListener, this);
		manager.registerEvents(damageListener, this);
		manager.registerEvents(interactListener, this);

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
		getCommand("hglist").setExecutor(listCommand);
		getCommand("hgcreate").setExecutor(createCommand);
		getCommand("hgfinish").setExecutor(finishCommand);
		getCommand("hgdelete").setExecutor(deleteCommand);
		getCommand("hgjoin").setExecutor(joinCommand);
		getCommand("hgleave").setExecutor(leaveCommand);
		getCommand("hg").setExecutor(hgCommand);
		getCommand("hghelp").setExecutor(helpCommand);
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
