package controller;

import java.util.ArrayList;

import exception.CardNotFoundException;
import model.Card;
import model.Player;

/**
 * Manages a player. 
 * </p> Is a level of indirection between game controller and player.
 * 
 * @see model.Player
 * @see GameController
 *
 */
public abstract class AbstractPlayerHandler {

	private GameController controller;
	private Player player;
	private Card playedCard;
	private ArrayList<ArrayList<Card>> resultFromFetch;

	/**
	 * Make a generic player handler, based on the player to manage and the
	 * controller of the game.
	 * 
	 * @param player player to manage
	 * @param controller controller of the game
	 */
	public AbstractPlayerHandler(Player player, GameController controller) {
		this.player = player;
		this.controller = controller;
	}

	/**
	 * Freezes the player when it's not his turn.
	 * 
	 */
	public abstract boolean lockPlayer();

	/**
	 * Unlock the player.
	 */
	public abstract boolean unlockPlayer();

	/**
	 * Manages one or more possible choices of player's pickable cards.
	 * 
	 * @param choices Possible card choices.
	 */
	public abstract void multipleChoice(ArrayList<ArrayList<Card>> choices);

	/**
	 * @return Returns the managed player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return the controller of the game.
	 */
	public GameController getController() {
		return this.controller;
	}

	/**
	 * Fetch the result of a player's play from the game controller.
	 * 
	 * @param c the card just played by the player
	 * @throws CardNotFoundException in case the player does not have a card.
	 */
	public void cardPlayed(Card c) throws CardNotFoundException {
		setPlayedCard(c);
		setResultFromFetch(getController().fetchCard(c));

		if (getResultFromFetch().size() == 0) {

			getController().endTurn(this);
		}
		if (getResultFromFetch().size() == 1) {

			getController().endTurn(this, getResultFromFetch().get(0));
		}
	}

	/**
	 * 
	 * @return The player managed by this handler.
	 */
	public Card getPlayedCard() {
		return playedCard;
	}

	/**
	 * Set the card just played by the player.
	 * 
	 * @param playedCard the card just played.
	 */
	public void setPlayedCard(Card playedCard) {
		this.playedCard = playedCard;
	}

	/**
	 * @return the last result from fetch of the player's play.
	 */
	public ArrayList<ArrayList<Card>> getResultFromFetch() {
		return resultFromFetch;
	}

	/**
	 * @param resultFromFetch the result from fetch to set.
	 */
	public void setResultFromFetch(ArrayList<ArrayList<Card>> resultFromFetch) {
		this.resultFromFetch = resultFromFetch;
	}

}
