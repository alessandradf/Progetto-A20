package controller;

import model.Player;

public abstract class AbstractPlayerHandler {
	private Player player;
	/*
	 * private InterfaceCard cardPlayed;
	 */
	
	public AbstractPlayerHandler(Player player) {
		this.player = player;
	}
	
	public abstract boolean unlockPlayer();

	public Player getPlayer() {
		return player;
	}
}
