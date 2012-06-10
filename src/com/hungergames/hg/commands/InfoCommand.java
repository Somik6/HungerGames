package com.hungergames.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.hungergames.hg.GameSession;
import com.hungergames.hg.HungerGames;
import com.hungergames.hg.utils.Utils;

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
		if (args.length != 0) {
			return false;
		}
		GameSession session = plugin.getGameManager().getGameSession();
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
		sender.sendMessage(plugin.getBroadcaster().styleMessage(session.getName()));
		sender.sendMessage(plugin.getBroadcaster().styleMessage("Admins: " + admins));
		sender.sendMessage(plugin.getBroadcaster().styleMessage("Players: " + alive + ", " + dead));
		return true;
	}

}
