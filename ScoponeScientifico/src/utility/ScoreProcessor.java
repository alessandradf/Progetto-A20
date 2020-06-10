package utility;

import java.util.ArrayList;
import java.util.Collections;

import model.Card;
import model.SeedType;

public class ScoreProcessor {

	
	// punti posizionali da asso a re per il calcolo della primiera
	
	private final static int[] PRIMIERA_POINTS = { 16, 12, 13, 14, 15, 18, 21, 10, 10, 10 };


	/*
	 * Ritorna 1 o 2 in base ai punti SETTEBELLO e DENARI ottenuti, 0 se non sono stati fatti questi tipi di punti
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

	/*
	 * Ritorna il punteggio concorrente per il punto della PRIMIERA
	 */
	public static int sommaPrimiera(ArrayList<Card> cards) {

		Collections.sort(cards); // ordino le carte per poter accedere facilmente al punteggio relativo

		int sommaPrimiera = 0;
		SeedType currentSeedChecked = SeedType.values()[0];

		int ultimoConcorrentePrimiera = 0; // ultimo punteggio della carta potenzialmente utile per la primiera

		for (Card c : cards) {

			if (c.getSeed().ordinal() > currentSeedChecked.ordinal()) {
				
				//se manca un seme la primiera non è valida
				if ((c.getSeed().ordinal() - currentSeedChecked.ordinal()) != 1) {
					return 0;
				}
				
				currentSeedChecked = c.getSeed(); // passo al seme successivo per il calcolo della primiera
				sommaPrimiera += ultimoConcorrentePrimiera; // aggiorno il parziale per la primiera
				ultimoConcorrentePrimiera = 0;
			}
			// qua sto scandendo le carte di uno stesso seme
			int prim = PRIMIERA_POINTS[c.getValue() - 1];
			if (prim > ultimoConcorrentePrimiera) {
				ultimoConcorrentePrimiera = prim;
			}

		}

		sommaPrimiera += ultimoConcorrentePrimiera;

		return sommaPrimiera;
	}
	

}
