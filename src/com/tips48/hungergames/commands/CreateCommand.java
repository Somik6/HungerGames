package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgcreate command
 * 
 * @author tips48
 * 
 */
public class CreateCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new CreateCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public CreateCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(args.length != 1)) {
			return false;
		}
		if (!(sender.hasPermission(HGPermission.CREATE.toString()))) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("You don't have permission!"));
			return true;
		}
		if (!(plugin.getGameManager().createSession(args[0], null, null))) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"Error creating the session!"));
			return true;
		}
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				"Session created"));
		return true;
	}

}
