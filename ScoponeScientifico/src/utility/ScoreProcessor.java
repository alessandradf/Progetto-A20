package utility;

import java.util.ArrayList;
import java.util.Collections;

import model.Card;
import model.SeedType;
import model.Team;

/*
 * Classe per il calcolo dei vari tipi di punti
 * Va istanziata con i due team
 * Dall'esterno è visibile solo il metodo giveScore()
 */
public class ScoreProcessor {
	
	// punti posizionali da asso a re per il calcolo della primiera
	private final static int[] PRIMIERA_POINTS = { 16, 12, 13, 14, 15, 18, 21, 10, 10, 10 };
	
	
	private Team team1, team2;	//I due team della partita
	
	
	public ScoreProcessor(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
	}
	
	/*
	 * Calcola e assegna i punteggi dei due team usando i metodi interni a questa classe 
	 */
	public void giveScore() {
		
		int score1 = 0;
		int score2 = 0;
		
		ArrayList<Card> cards1 = team1.getCards();
		ArrayList<Card> cards2 = team2.getCards();
		
		score1 += this.carte(cards1);
		score1 += this.denariSettebello(cards1);
		score1 += team1.getScope().size();
		
		score2 += this.carte(cards2);
		score2 += this.denariSettebello(cards2);
		score2 += team2.getScope().size();
		
		//Punti per la primiera
		int prim1 = this.sommaPrimiera(cards1);
		int prim2 = this.sommaPrimiera(cards2);
		
		if (prim1 != 0 || prim2 != 0) {
			if (prim1 > prim2)
				score1++;
			else
				score2++;
		}
		
		team1.addScore(score1);
		team2.addScore(score2);
	}

	/*
	 * Ritorna il punteggio concorrente per il punto della PRIMIERA
	 */
	private int sommaPrimiera(ArrayList<Card> cards) {

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
	private int maxCardValue(ArrayList<Card> seedCards) {

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
	private int denariSettebello(ArrayList<Card> cards) {
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
	private int carte(ArrayList<Card> cards) {
		if (cards.size() >= 21) {
			return 1;
		} else
			return 0;
	}
	
}

