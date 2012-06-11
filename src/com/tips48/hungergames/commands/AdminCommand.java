package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgadmin command
 * 
 * @author tips48
 * 
 */
public class AdminCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new AdminCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public AdminCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 2) {
			return false;
		}
		GameSession session = plugin.getGameManager().getGameSession(args[0]);
		if (session == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"The specified session does not exist!"));
			return true;
		}
		Player player = plugin.getServer().getPlayer(args[1]);
		if (player == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"The specified player was not online!"));
			return true;
		}
		session.addAdmin(player);
		return true;
	}
}
