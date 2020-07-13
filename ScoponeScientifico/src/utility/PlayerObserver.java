package utility;

import model.Card;
import model.Player;

/**
 * Implmenetation of the pattern observer.
 * The class that implements this interface is notified when specific changes occurs in the Player class 
 */
public interface PlayerObserver {

	/**
	 * Updates the observer after a Card has been removed from the Player hand (the card has been played) 
	 * 
	 * @param p: the Player observed
	 * @param playedCard: the card that was removed
	 */
	public void updateOnPlayedCard(Player p, Card playedCard);
	
}
