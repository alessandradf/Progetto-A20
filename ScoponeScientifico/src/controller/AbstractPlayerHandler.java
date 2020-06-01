package controller;

import model.Player;

public abstract class AbstractPlayerHandler {
	
	private GameController controller;	//usato per la comunicazione della carta giocata
	private Player player;	
	/*
	 * private InterfaceCard cardPlayed;
	 */
	
	public AbstractPlayerHandler(Player player, GameController controller) {
		this.player = player;
		this.controller = controller;
	}
	
	public abstract boolean unlockPlayer();

	public Player getPlayer() {
		return player;
	}
	
	public GameController getController() {
		return this.controller;
	}
}
