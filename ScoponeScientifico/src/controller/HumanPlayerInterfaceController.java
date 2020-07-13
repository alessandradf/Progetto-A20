package controller;

import java.util.ArrayList;

import model.Card;

/**
 * Is a level of indirection between the human interface and and the control
 * layer.
 * 
 * </p>
 * Provides methods for handling the multiple choice case, and for unlocking and
 * locking the interface.
 *
 */
public interface HumanPlayerInterfaceController {

	/**
	 * Handles the case where a player has multiple choices. Via the interface the
	 * player will be asked to choose one.
	 * @param humanPlayerHandler handler of the player
	 * @param choices all possible choices
	 */
	public void multipleChoice(HumanPlayerHandler humanPlayerHandler, ArrayList<ArrayList<Card>> choices);

	/**
	 * Locks the player's interface.
	 * In this way player can't play when it's not his turn.
	 * 
	 * @param humanPlayerHandler
	 */
	public void lock(HumanPlayerHandler humanPlayerHandler);

	/**
	 * Unlock the player's interface.
	 * It is called when it is the player's turn
	 * @param humanPlayerHandler handler of the player
	 */
	public void unlock(HumanPlayerHandler humanPlayerHandler);
}
