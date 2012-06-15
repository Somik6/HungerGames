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
		if (sender.hasPermission(HGPermission.ADMIN.toString())) {
			sender.sendMessage("/hgadmin <session> <player> - Promotes a player to admin");
		}
		if (sender.hasPermission(HGPermission.CREATE.toString())) {
			sender.sendMessage("/hgcreate <name> - Creates a session");
			sender.sendMessage("/hgfinish - Finishes a created session");
		}
		if (sender.hasPermission(HGPermission.DELETE.toString())) {
			sender.sendMessage("/hgdelete <name> - Deletes a session");
		}
		sender.sendMessage("/hghelp - Shows this information");
		sender.sendMessage("/hg - Shows general information about the plugin");
		sender.sendMessage("/hginfo <session> - Shows information about a session");
		sender.sendMessage("/hgjoin <session> - Joins a session");
		sender.sendMessage("/hgleave- Leaves a session");
		sender.sendMessage("/hglist - Lists all the currently created sessions");
		if (sender.hasPermission(HGPermission.RELOAD.toString())) {
			sender.sendMessage("/hgreload - Reloads the configuration");
		}
		if (sender.hasPermission(HGPermission.START.toString())) {
			sender.sendMessage("/hgstart <session> - Starts a session");
		}
		if (sender.hasPermission(HGPermission.STOP.toString())) {
			sender.sendMessage("/hgstop <session> - Stops a session");
		}
		if (sender.hasPermission(HGPermission.FORCE_JOIN.toString())) {
			sender.sendMessage("/hgforcejoin <session> <player> - Forces a player to join a session");
		}
		if (sender.hasPermission(HGPermission.FORCE_LEAVE.toString())) {
			sender.sendMessage("/hgforcequit <player> - Forces a player to leave the session they are currently in");
		}
		return true;
	}

}
