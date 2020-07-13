package utility;

import model.Card;

/**
 * Implementation of the pattern Observer
 * A class that implements this interface is notified whenever changes occurs in the Team class
 */
public interface TeamObserver {
	
	/**
	 * Updates the Observer when the Team score is modified
	 * @param score: the updated total score
	 * @param lastHandScore: the score of the last hand
	 */
	public void updateScore(int score, int lastHandScore);
	
	/**
	 * Updates the Observer when the Team makes a "scopa" point
	 * @param scopaCard: the Card related to the "scopa"
	 */
	public void scopa(Card scopaCard);
}
