package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Handlse the /hgforcejoin command
 * 
 * @author tips48
 * 
 */
public class ForceJoinCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new ForceJoinCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public ForceJoinCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 2) {
			return false;
		}
		if (!(sender.hasPermission(HGPermission.FORCE_JOIN.toString()))) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"You don't have permission!"));
			return true;
		}
		GameSession session = plugin.getGameManager().getGameSession(args[0]);
		if (session == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"The specified session did not exist!"));
			return true;
		}
		Player player = plugin.getServer().getPlayer(args[1]);
		if (player == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"The specified player did not exist!"));
			return true;
		}
		session.addPlayer(player);
		return true;
	}
}
