package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgfinish command
 * 
 * @author tips48
 */
public class FinishCommand implements CommandExecutor {
	private final HungerGames plugin;
	
	/**
	 * Creates a new FinishCommand
	 * @param plugin Plugin instance
	 */
	public FinishCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length != 0) {
			return false;
		}
		if (!(sender.hasPermission(HGPermission.CREATE.toString()))) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("You don't have permission!"));
			return true;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("You must be a player!"));
			return true;
		}
		GameSession session = plugin.getGameManager().getConstructedSession(sender.getName());
		if (session == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("You don't have any sessions which aren't finished!"));
			return true;
		}
		session.setConstructed(true);
		sender.sendMessage(plugin.getBroadcaster().styleMessage("Session finished!"));
		return true;
	}
}
