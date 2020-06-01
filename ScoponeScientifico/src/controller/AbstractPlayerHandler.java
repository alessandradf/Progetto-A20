package controller;

import model.Card;
import model.Player;

public abstract class AbstractPlayerHandler {
	
	private GameController controller;	//usato per la comunicazione della carta giocata
	private Player player;	
	private Card playedCard; //ultima carta giocata dal giocatore
	
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

	/**
	 * @return playedCard l'ultima carta giocata
	 */
	public Card getPlayedCard() {
		return playedCard;
	}

	public void setPlayedCard(Card playedCard) {
		this.playedCard = playedCard;
	}
}
