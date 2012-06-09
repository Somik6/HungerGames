package com.tips48.hungergames;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tips48.hungergames.listeners.PlayerJoinListener;
import com.tips48.hungergames.listeners.PlayerMoveListener;
import com.tips48.hungergames.listeners.PlayerQuitListener;

public class HungerGames extends JavaPlugin {
	private PlayerJoinListener joinListener;
	private PlayerMoveListener moveListener;
	private PlayerQuitListener quitListener;

	@Override
	public void onLoad() {
		joinListener = new PlayerJoinListener(this);
		moveListener = new PlayerMoveListener(this);
		quitListener = new PlayerQuitListener(this);
	}

	@Override
	public void onEnable() {
	  registerEvents();
	}
	
	private void registerEvents() {
		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(joinListener, this);
		manager.registerEvents(moveListener, this);
		manager.registerEvents(quitListener, this);
	}

	@Override
	public void onDisable() {

	}

}
