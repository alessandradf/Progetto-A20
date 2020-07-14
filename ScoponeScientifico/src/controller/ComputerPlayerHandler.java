package controller;

import java.util.ArrayList;
import java.util.Random;

import exception.CardNotFoundException;
import model.Card;
import model.Player;

/**
 * Handler of an artificial player. Extends {@code AbstractPlayerHandler}.
 * 
 * @see AbstractPlayerHandler
 *
 */
public class ComputerPlayerHandler extends AbstractPlayerHandler {

	private Random randomGenerator;

	/**
	 * 
	 * @param player     player to manage
	 * @param controller controller of the Game
	 */
	public ComputerPlayerHandler(Player player, GameController controller) {
		super(player, controller);
		this.randomGenerator = new Random();

	}

	/**
	 * Unlock the player game.
	 */
	@Override
	public boolean unlockPlayer() {
		setPlayedCard(pickACard());
		cardPlayed(getPlayedCard());
		return true;
	}

	/**
	 * Fetches the result of the player's play from the game controller.
	 * If there are more results chooses the first one.
	 */
	@Override
	public void cardPlayed(Card c) {
		try {
			super.cardPlayed(c);

			if (getResultFromFetch().size() > 1) {
				getController().endTurn(this, getResultFromFetch().get(0));

			}
		} catch (CardNotFoundException e) {
			System.out.println("Carta non trovata!");
		}
	}

	/**
	 * It does nothing because it is an artificial player.
	 */
	@Override
	public boolean lockPlayer() {

		return true;
	}

	
	/**
	 * Takes a random {@code Card} from the hand of the managed {@link model.Player}.
	 * @return the {@code Card} taken.
	 */
	public Card pickACard() {
		int handSize = this.getPlayer().getHand().size();
		int randIndex = this.randomGenerator.nextInt(handSize);
		return this.getPlayer().getHand().get(randIndex);
	}

	
	/**
	 * Handles the case where the player has more than one choice.
	 * Returns the first choice.
	 */
	@Override
	public void multipleChoice(ArrayList<ArrayList<Card>> choices) {
		try {
			getController().endTurn(this, choices.get(0));
		} catch (CardNotFoundException e) {
			System.out.println("Carta non trovata!");
		}
	}

}
