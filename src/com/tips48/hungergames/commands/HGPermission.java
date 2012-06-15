package com.tips48.hungergames.commands;

public enum HGPermission {
	/**
	 * Permission for the /hgadmin command
	 */
	ADMIN("HungerGames.admin"),
	/**
	 * Permission for the /hgcreate command
	 */
	CREATE("HungerGames.create"),
	/**
	 * Permission for the /hgdelete command
	 */
	DELETE("HungerGames.delete"),
	/**
	 * Permission for the /hgreload command
	 */
	RELOAD("HungerGames.reload"),
	/**
	 * Permission for the /hgstart command
	 */
	START("HungerGames.start"),
	/**
	 * Permisson for the /hgstop command
	 */
	STOP("HungerGames.stop"),
	FORCE_JOIN("HungerGames.forcejoin"),
	FORCE_LEAVE("HungerGames.forceleave");
	
	
	private final String permission;
	
	private HGPermission(String permission) {
		this.permission = permission;
	}
	
	@Override
	public String toString() {
		return permission;
	}
}
