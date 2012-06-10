package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgstart command
 * 
 * @author tips48
 */
public class StartCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new StartCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public StartCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 0) {
			return false;
		}
		if (!(plugin.getGameManager().getGameSession().start())) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("Failed to start the game!"));
		}
		return true;
	}

}