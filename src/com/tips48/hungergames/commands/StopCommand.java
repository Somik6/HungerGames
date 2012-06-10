package com.tips48.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgstop command
 * 
 * @author tips48
 * 
 */
public class StopCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new StopCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public StopCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 0) {
			return false;
		}
		if (!(plugin.getGameManager().getGameSession().stop())) {
			sender.sendMessage(ChatColor.RED + "Error stopping the session!");
		}
		return true;
	}
}
