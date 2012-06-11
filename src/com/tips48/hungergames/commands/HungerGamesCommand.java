package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hg command
 * 
 * @author tips48
 * 
 */
public class HungerGamesCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new HungerGamesCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public HungerGamesCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(args.length == 0)) {
			return false;
		}
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				"HungerGames plugin v" + plugin.getDescription().getVersion()
						+ " by tips48 and steaks4uce!"));
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				"Type /hghelp for help"));
		return true;
	}

}
