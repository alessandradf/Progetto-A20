package controller;

import java.util.ArrayList;

import utility.TableObserver;


/**
 * Controller of the interface.
 *
 */
public interface InterfaceController extends TableObserver {

	/**
	 * Start the user interface.
	 */
	public void startGame();

	/**
	 * Start the user interface with the specified message.
	 * 
	 * @param message the message to be displayed
	 */
	public void startGame(String message);

	/**
	 * Initializes the interface.
	 * 
	 * Makes the interface visible and ready.
	 * @param playerHandlers handlers of the players
	 * @param gameController controller of the game
	 */
	public void init(ArrayList<HumanPlayerHandler> playerHandlers, GameController gameController);

}
