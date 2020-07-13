package controller;

import java.util.ArrayList;

import exception.CardNotFoundException;
import model.Card;
import model.Player;

/**
 * Manages the user's player. Extends {@code AbstractPlayerHandler}. Is a level
 * of direction between game controller and player.
 *
 */
public class HumanPlayerHandler extends AbstractPlayerHandler {

	private HumanPlayerInterfaceController interfaceController = null;

	/**
	 * Make a user's player handler, based on the player to manage and the
	 * controller of the game.
	 * 
	 * @param player     player to manage
	 * @param controller controller of the game
	 */
	public HumanPlayerHandler(Player player, GameController controller) {
		super(player, controller);
	}

	/**
	 * Make a user's player handler, based on the player to manage and the
	 * controller of the game.
	 * 
	 * @param player     player player to manage
	 * @param controller controller controller of the game
	 * @param i          user's interface controller
	 */
	public HumanPlayerHandler(Player player, GameController controller, HumanPlayerInterfaceController i) {
		this(player, controller);
		this.interfaceController = i;
	}

	/**
	 * Fetches the result of a player's play from the game controller.</br>
	 * If there are more results, this method passes control to the interface to
	 * allow the player to choose one of the results.
	 */
	public void cardPlayed(Card c) throws CardNotFoundException {
		boolean isValid = false;
		if (super.getPlayer().getHand().contains(c)) {

			isValid = true;
		}
		if (!isValid) {
			throw new CardNotFoundException();
		}
		super.cardPlayed(c);
		if (getResultFromFetch().size() > 1) {
			// passa il controllo all'interfaccia, per scegliere le carte da prendere
			multipleChoice(getResultFromFetch());
		}
	}

	/**
	 * Unlock the player interface.
	 */
	@Override
	public boolean unlockPlayer() {
		interfaceController.unlock(this);
		return true;
	}

	/**
	 * Lock the player interface. Prevents the player from playing when it is not
	 * his turn.
	 */
	@Override
	public boolean lockPlayer() {
		interfaceController.lock(this);
		return true;
	}

	/**
	 * Passes control to the interface when multiple choices are possible.
	 * 
	 * @param choice possible choice
	 */
	@Override
	public void multipleChoice(ArrayList<ArrayList<Card>> choices) {
		interfaceController.multipleChoice(this, choices);

	}

	/**
	 * Set the controller interface to use.
	 * 
	 * @param i controller interface
	 */
	public void setInterfaceController(HumanPlayerInterfaceController i) {
		this.interfaceController = i;
	}

}
