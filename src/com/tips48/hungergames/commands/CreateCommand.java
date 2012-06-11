package com.tips48.hungergames.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tips48.hungergames.HungerGames;
import com.tips48.hungergames.config.ConfigManager;

/**
 * Handles the /hgcreate command
 * 
 * @author tips48
 * 
 */
public class CreateCommand implements CommandExecutor {
	private final HungerGames plugin;

	/**
	 * Creates a new CreateCommand
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	public CreateCommand(HungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 1) {
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
		if (plugin.getGameManager().getConstructedSession(sender.getName()) != null) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage("You must finish your constructed session!"));
			return true;
		}
		if (!(plugin.getGameManager().createSession(args[0], sender.getName(), null, null))) {
			sender.sendMessage(plugin.getBroadcaster().styleMessage(
					"Error creating the session!"));
			return true;
		}
		sender.sendMessage(plugin.getBroadcaster().styleMessage(
				"Session created"));
		if (ConfigManager.TELEPORT_ON_JOIN) {
			Player player = (Player) sender;
			player.getInventory().addItem(new ItemStack(Material.DIAMOND_HOE, 1));
			if (ConfigManager.RANDOM_TELEPORT) {
				sender.sendMessage(plugin.getBroadcaster().styleMessage("Right-click on the center of where you want players to spawn with a diamond hoe"));
			} else {
				sender.sendMessage(plugin.getBroadcaster().styleMessage("Right-click on each of the spawn points with a diamond hoe"));
				sender.sendMessage(plugin.getBroadcaster().styleMessage("When your done, type /hgfinish " + args[0]));
			}
		}
		return true;
	}

}
