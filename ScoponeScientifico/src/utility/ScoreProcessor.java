package utility;

import java.util.ArrayList;
import java.util.Collections;

import model.Card;
import model.SeedType;
import model.Team;

/**
 * This class provides methods to determine the scores of two Teams depending
 * on the Cards they have. It also assign the calculated scores.
 * The only public method is giveScore().
 */
public class ScoreProcessor {
	
	/**
	 * Array of positional points (sorted from "ace" to "king") used to calculate the PRIMIERA points
	 */
	private final static int[] PRIMIERA_POINTS = { 16, 12, 13, 14, 15, 18, 21, 10, 10, 10 };
	
	/**
	 * The two competing teams
	 */
	private Team team1, team2;
	
	/**
	 * Initialize the ScoreProcessor with the teams
	 * @param team1
	 * @param team2
	 */
	public ScoreProcessor(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
	}
	
	/**
	 * Calculates and assign the scores to the teams
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
		
		int prim1 = this.sommaPrimiera(cards1);
		int prim2 = this.sommaPrimiera(cards2);
		
		// if primiera scores of both teams are 0 the point is not assigned
		if (prim1 != 0 || prim2 != 0) {
			if (prim1 > prim2)
				score1++;
			else
				score2++;
		}
		
		team1.addScore(score1);
		team2.addScore(score2);
	}

	/**
	 * Returns the score competeing for PRIMIERA point. It's calculated on the specified cards and the PRIMIERA_POINTS array
	 * 
	 * @param cards the {@link ArrayList} of {@link Card}
	 * @return the score competing for PRIMIERA point
	 */
	private int sommaPrimiera(ArrayList<Card> cards) {

		int sommaPrim = 0;

		Collections.sort(cards);	//sorted by values so they can be compared with the PRIMIERA_POITNS array

		ArrayList<Card> cardsPerSeed = new ArrayList<Card>();	//ArrayList of cards of the same seed

		for (SeedType seed : SeedType.values()) {
			for (int i = 0; i < cards.size(); i++) {
				if (cards.get(i).getSeed() == seed) {
					cardsPerSeed.add(cards.get(i));
				}
			}
			
			//if there's not at least one card for each seed then the score is not valid
			if (cardsPerSeed.size() == 0) {
				return 0;
			}
			
			sommaPrim += maxCardValue(cardsPerSeed);
			cardsPerSeed.clear();
		}
		
		return sommaPrim;
	}
	
	/**
	 * Returns the max value of the specified cards, according to the PRIMIERA values. The cards must have the same seed
	 * 
	 * @param seedCards ArrayList of cards of the same seed
	 * @return maxValue the max PRIMIERA value from the specified cards
	 * 
	 * @see {@link SeedType}, {@link Card}
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
	
	/**
	 * Determines if SETTEBELLO or DENARI points have been obtained
	 * 
	 * @param cards the ArrayList of cards
	 * @return an integer value based on the points obtained. 1 or 2 if only one or both points were obtained, 0 otherwise
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
			points++;	// DENARI
		}

		return points;
	}

	/**
	 * Determines if CARTE point was obtained (if the specified cards are more than 21).
	 * 
	 * @param cards ArrayList of cards used to determine the point
	 * @return 1 if the point was obtained, 0 otherwise
	 */
	private int carte(ArrayList<Card> cards) {
		if (cards.size() >= 21) {
			return 1;
		} else
			return 0;
	}
	
}

