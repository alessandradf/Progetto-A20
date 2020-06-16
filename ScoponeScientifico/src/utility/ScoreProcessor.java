package utility;

import java.util.ArrayList;
import java.util.Collections;

import model.Card;
import model.SeedType;

/*
 * Classe con metodi statici per il calcolo dei vari tipi di punti 
 */
public class ScoreProcessor {

	
	// punti posizionali da asso a re per il calcolo della primiera

	private final static int[] PRIMIERA_POINTS = { 16, 12, 13, 14, 15, 18, 21, 10, 10, 10 };

	/*
	 * Ritorna il punteggio concorrente per il punto della PRIMIERA
	 */
	public static int sommaPrimiera(ArrayList<Card> cards) {

		int sommaPrim = 0;

		Collections.sort(cards);

		ArrayList<Card> cardsPerSeed = new ArrayList<Card>();	//insieme delle carte di uno stesso seme

		for (SeedType seed : SeedType.values()) {
			for (int i = 0; i < cards.size(); i++) {
				if (cards.get(i).getSeed() == seed) {
					cardsPerSeed.add(cards.get(i));
				}
			}
			
			//se non ho almeno una carta per seme la primiera non è valida
			if (cardsPerSeed.size() == 0) {
				return 0;
			}
			
			sommaPrim += maxCardValue(cardsPerSeed);
			cardsPerSeed.clear();
		}
		
		return sommaPrim;
	}
	
	/*
	 * Ritorna il massimo valore (secondo i punteggi per la primiera) tra quelli delle carte passate
	 * Per avere senso dovrebbero essere tutte carte di uno stesso seme 
	 */
	private static int maxCardValue(ArrayList<Card> seedCards) {

		int maxValue = 0;

		for (Card c : seedCards) {
			if (PRIMIERA_POINTS[c.getValue() - 1] > maxValue) {
				maxValue = PRIMIERA_POINTS[c.getValue() - 1];
			}
		}
		return maxValue;
	}
	
	
	/*
	 * Ritorna 1 o 2 in base ai punti SETTEBELLO e DENARI ottenuti, 0 se non sono
	 * stati fatti questi tipi di punti
	 */
	public static int denariSettebello(ArrayList<Card> cards) {
		int points = 0;

		int numDenari = 0;

		for (Card c : cards) {
			if (c.getSeed() == SeedType.DENARI) {
				numDenari++;
				if (c.getValue() == 7) {
					points++; // SETTEBELLO
				}
			}
		}

		if (numDenari >= 6) {
			points++;
		}

		return points;
	}

	/*
	 * Ritorna 1 se è stato fatto il punto CARTE, 0 altrimenti
	 */
	public static int carte(ArrayList<Card> cards) {
		if (cards.size() >= 21) {
			return 1;
		} else
			return 0;
	}
	
}
