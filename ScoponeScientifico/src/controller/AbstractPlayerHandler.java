package controller;

import java.util.ArrayList;

import exception.CardNotFoundException;
import model.Card;
import model.Player;

public abstract class AbstractPlayerHandler {
	
	private GameController controller;	//usato per la comunicazione della carta giocata
	private Player player;	
	private Card playedCard; //ultima carta giocata dal giocatore
	private ArrayList<ArrayList<Card>> resultFromFetch;
	
	public AbstractPlayerHandler(Player player, GameController controller) {
		this.player = player;
		this.controller = controller;
	}
	
	public abstract boolean lockPlayer();	// Blocca il panel relativo al giocatore
	
	public abstract boolean unlockPlayer();	// Sblocca il panel relativo al giocatore
	
	public abstract void multipleChoice(ArrayList<ArrayList<Card>> choices); //Permette di gestire la presa multipla
	

	public Player getPlayer() {
		return player;
	}
	
	public GameController getController() {
		return this.controller;
	}

	/**
	 * @return playedCard l'ultima carta giocata
	 */
	public void cardPlayed(Card c) throws CardNotFoundException{
		setPlayedCard(c);
		setResultFromFetch(getController().fetchCard(c));

		if (getResultFromFetch().size() == 0) {
			//finisci il turno, cioè metti la carta sul tavolo
			getController().endTurn(this);
		}
		if(getResultFromFetch().size() ==  1) {
			//la presa è obbligata
			getController().endTurn(this, getResultFromFetch().get(0));
		}
	}
	
	public Card getPlayedCard() {
		return playedCard;
	}

	public void setPlayedCard(Card playedCard) {
		this.playedCard = playedCard;
	}

	/**
	 * @return the resultFromFetch
	 */
	public ArrayList<ArrayList<Card>> getResultFromFetch() {
		return resultFromFetch;
	}

	/**
	 * @param resultFromFetch the resultFromFetch to set
	 */
	public void setResultFromFetch(ArrayList<ArrayList<Card>> resultFromFetch) {
		this.resultFromFetch = resultFromFetch;
	}

	
	
}
