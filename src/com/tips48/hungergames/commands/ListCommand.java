package com.tips48.hungergames.commands;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hglist command
 * 
 * @author tips48
 * 
 */
public class ListCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new ListCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public ListCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(args.length == 0)) {
			return false;
		}
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				"Red = started;Green = not started"));
		sender.sendMessage(plugin.getBroadcaster().styleMessage("Sesssions:"));
		Set<GameSession> sessions = plugin.getGameManager().getSessions();
		if (sessions.isEmpty()) {
			sender.sendMessage("None!");
			return true;
		}
		for (GameSession session : sessions) {
			ChatColor color = session.isStarted() ? ChatColor.RED
					: ChatColor.GREEN;
			sender.sendMessage(color + session.getName());
		}
		return true;
	}

}
