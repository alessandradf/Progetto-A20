package controller;

import model.Team;

/**
 * Interface that manages the end of a game turn, and the end of the game.
 *
 */
public interface InterfaceTurnFinalizer {

	/**
	 * Initialize the interface for a new hand.
	 */
	public void newHand();

	/**
	 * Manages the end of the game. 
	 * Show the {@link Team} winner and initialize the interface for
	 * a possible new game.
	 * 
	 * @param winnerTeam the {@link Team} that won the game.
	 */
	public void gameFinished(Team winnerTeam);
}
