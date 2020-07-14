package utility;

import model.Card;
import model.Player;

/**
 * Implementation of the pattern observer.
 * The class that implements this interface is notified when specific changes occurs in the {@link Player} 
 */
public interface PlayerObserver {

	/**
	 * Updates the observer after a Card has been removed from the Player hand (the card has been played) 
	 * 
	 * @param p the {@link Player} observed
	 * @param playedCard the card that was removed
	 * 
	 * @see {@link Player}
	 */
	public void updateOnPlayedCard(Player p, Card playedCard);
	
}
