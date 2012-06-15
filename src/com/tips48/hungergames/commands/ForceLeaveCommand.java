package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgforceleave command
 * 
 * @author tips48
 * 
 */
public class ForceLeaveCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new ForceLeaveCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public ForceLeaveCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 1) {
			return false;
		}
		if (!(sender.hasPermission(HGPermission.FORCE_LEAVE.toString()))) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"You don't have permission!"));
			return true;
		}
		GameSession session = plugin.getGameManager().getGameSessionOfPlayer(
				args[0]);
		if (session == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"The specified player was not in a session!"));
			return true;
		}
		session.removePlayer(args[0]);
		return true;
	}

}
