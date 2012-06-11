package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgdelete command
 * 
 * @author tips48
 * 
 */
public class DeleteCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new DeleteCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public DeleteCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(args.length != 1)) {
			return false;
		}
		if (!(sender.hasPermission(HGPermission.DELETE.toString()))) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("You don't have permission!"));
			return true;
		}
		if (!(plugin.getGameManager().deleteSession(args[0]))) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"Error deleting the session!"));
			return true;
		}
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				"Session deleted"));
		return true;
	}

}