package com.tips48.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tips48.hungergames.GameSession;
import com.tips48.hungergames.HungerGames;

/**
 * Handles the /hgjoin command
 * @author tips48
 *
 */
public class JoinCommand implements CommandExecutor {
	private final HungerGames plugin;
	
	/**
	 * Creates a new JoinCommand
	 * @param plugin Plugin instance
	 */
	public JoinCommand(HungerGames plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length != 1) {
			return false;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("This command is player-only!"));
			return true;
		}
		Player player = (Player) sender;
		GameSession session = plugin.getGameManager().getGameSessionOfPlayer(player);
		if (session != null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("You are already in a session!"));
			sender.sendMessage(plugin.getBroadcaster().styleMessage("You can leave with /hgleave " + session.getName()));
			return true;
		}
		session = plugin.getGameManager().getGameSession(args[0]);
		if (session == null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("The specified session does not exist!"));
			return true;
		}
		session.addPlayer(player);
		return true;
	}
	
}
