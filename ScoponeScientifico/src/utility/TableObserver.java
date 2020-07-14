package utility;

import java.util.ArrayList;
import java.util.List;

import model.Card;

/**
 * Implementation of the pattern Observer
 * The class that implmenets this Interface is updated whenever there's changes in the {@link Table}
 */
public interface TableObserver {
	
	/**
	 * Update the Observer after a Card was put on the Table
	 * @param c the {@link Card} added
	 */
	public void updateOnAddition(Card c);
	
	/**
	 * Updates the Observere after the specified List of Cards was removed from the Table
	 * @param toRemove the {@link Card} List 
	 */
	public void updateOnRemoval(List<Card> toRemove);

}
