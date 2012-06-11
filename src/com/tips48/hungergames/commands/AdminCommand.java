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
		if (args.length != 1) {
			return false;
		}
		Player player = plugin.getServer().getPlayer(args[0]);
		if (player == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("The specified player was not online!"));
			return true;
		}
		GameSession session = plugin.getGameManager().getGameSession();
		session.addAdmin(player);
		return true;
	}
}
