package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgleave command
 * 
 * @author tips48
 * 
 */
public class LeaveCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new LeaveCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public LeaveCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 0) {
			return false;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"This command is player-only!"));
			return true;
		}
		Player player = (Player) sender;
		GameSession session = plugin.getGameManager().getGameSessionOfPlayer(
				player);
		if (session == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"You aren't in a session!"));
			return true;
		}
		if (session.isStarted()) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"You can't leave a session until it ends!"));
			return true;
		}
		session.removePlayer(player);
		return true;
	}

}
