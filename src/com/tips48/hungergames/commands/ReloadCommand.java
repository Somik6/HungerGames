package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tips48.hungergames.HungerGames;
import com.tips48.hungergames.config.ConfigManager;

/**
 * Handles the /hgreload command
 * 
 * @author tips48
 * 
 */
public class ReloadCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new ReloadCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public ReloadCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 0) {
			return false;
		}
		ConfigManager.init(plugin);
		return true;
	}

}
