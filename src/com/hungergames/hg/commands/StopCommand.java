package com.hungergames.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.hungergames.hg.HungerGames;

/**
 * Handles the /hgstop command
 * 
 * @author tips48
 * 
 */
public class StopCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new StopCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public StopCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 0) {
			return false;
		}
		if (!(plugin.getGameManager().getGameSession().stop())) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("Failed to stop the game!"));
		}
		return true;
	}
}
