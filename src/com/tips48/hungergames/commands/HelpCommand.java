package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hghelp command
 * 
 * @author tips48
 * 
 */
public class HelpCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new HelpCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public HelpCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 0) {
			return false;
		}
		sender.sendMessage(plugin.getBroadcaster().styleMessage("Commands: "));
		if (sender.hasPermission("HungerGames.admin")) {
			sender.sendMessage("/hgadmin <session> <player> - Promotes a player to admin");
		}
		if (sender.hasPermission("HungerGames.create")) {
			sender.sendMessage("/hgcreate <name> - Creates a session");
		}
		if (sender.hasPermission("HungerGames.delete")) {
			sender.sendMessage("/hgdelete <name> - Deletes a session");
		}
		sender.sendMessage("/hghelp - Shows this information");
		sender.sendMessage("/hg - Shows general information about the plugin");
		sender.sendMessage("/hginfo <session> - Shows information about a session");
		sender.sendMessage("/hgjoin <session> - Joins a session");
		sender.sendMessage("/hgleave- Leaves a session");
		sender.sendMessage("/hglist - Lists all the currently created sessions");
		if (sender.hasPermission("HungerGames.reload")) {
			sender.sendMessage("/hgreload - Reloads the configuration");
		}
		if (sender.hasPermission("HungerGames.start")) {
			sender.sendMessage("/hgstart <session> - Starts a session");
		}
		if (sender.hasPermission("HungerGames.stop")) {
			sender.sendMessage("/hgstop <session> - Stops a session");
		}
		return true;
	}

}
