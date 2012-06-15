package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;
import com.tips48.hungergames.utils.Utils;

/**
 * Handles the /hginfo command
 * 
 * @author tips48
 */
public class InfoCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new InfoCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public InfoCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 1) {
			return false;
		}
		GameSession session = plugin.getGameManager().getGameSession(args[0]);
		if (session == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"The specified session does not exist!"));
			return true;
		}
		String admins = Utils.makeReadable(session.getAdmins());
		if (admins.isEmpty()) {
			admins = "None";
		}
		String alive = Utils.makeReadable(session.getPlayers());
		if (alive.isEmpty()) {
			alive = "None";
		}
		String dead = Utils.makeReadable(session.getDeadPlayers());
		if (dead.isEmpty()) {
			dead = "None";
		}
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				session.getName()));
		sender.sendMessage(plugin.getBroadcaster().styleMessage("Constructed: " + session.isConstructed()));
		sender.sendMessage(plugin.getBroadcaster().styleMessage("Creator: " + session.getCreator()));
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				"Admins: " + admins));
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				"Players alive: " + alive + ", Dead: " + dead));
		return true;
	}
}
