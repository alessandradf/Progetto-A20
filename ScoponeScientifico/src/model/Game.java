package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Rappresenta un gioco di scopone scientifico, questa classe fa da controller.
 * 
 * @author Andrea
 *
 */
public class Game {

	private static Game defaultGame = null;
	private Table defaultTable;
	private Set<Card> deck;
	
	private Game() {
		createDeck();
		seeDeck();	//metodo di controllo, va eliminato nella versione finale
	}
	
	public static Game getDefaultGame(){
		if(defaultGame == null) {
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
		/*
		for (int i = 0; i < 10; i++) {
			//defaultTable.playRound(); // definire metodo playRound
		}
		*/
	}
	
	private void createDeck() {
		deck = new HashSet<Card>();
		SeedType seeds[] = SeedType.values();
		for(int i = 1; i <= Card.CARD_VALUES.length; i++) {
			for(SeedType type : seeds) {
				Card temp = new Card(i, type);
				deck.add(temp);
			}
		}
	}
	
	private void seeDeck() {
		ArrayList<String> arrayCarte = new ArrayList<String>();
		Iterator<Card> iterator = deck.iterator();
		while(iterator.hasNext()) {
			arrayCarte.add(iterator.next().toString());
		}
		Collections.sort(arrayCarte);
		for(String s : arrayCarte) {
			System.out.println(s);
		}
		
	}
	
	public static void main (String args[]) {
		Game.getDefaultGame();
	}
	
}
