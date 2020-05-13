package model;

import java.util.Set;

/**
 * Rappresenta un gioco di scopone scientifico, questa classe fa da controller.
 * 
 * @author Andrea
 *
 */
public class Game {

	private Game defaultGame = null;
	private Table defaultTable;
	private Set<Card> deck;
	
	private Game() {
		//new deck of cards
	}

	public Game getDefaultGame() {
		if (defaultGame == null) {
			defaultGame = new Game();
		}
		return defaultGame;
	}

	/**
	 * Metodo che crea tutto il resto del gioco, partendo dal tavolo per ora il
	 * metodo restituisce void, se serve cambiarlo non farsi dei problemi. Questa
	 * classe andrebbe istanziata una sola volta, secondo il pattern singleton
	 */
	public void playGame() {
		defaultTable = new Table();
		for (int i = 0; i < 10; i++) {
			defaultTable.playRound(); // definire metodo playRound
		}

	}

	public static void main(String[] args) {
		// TODO
		System.out.println("It seems that there is nothing here...");
	}

}
