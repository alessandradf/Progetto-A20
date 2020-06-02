package utility;

import java.util.ArrayList;

import model.Card;

public interface TableObserver {
	
	/*
	 * Metodi per notificare gli observers che una carta è stata messa sul tavolo
	 * e che delle carte sono state tolte
	 */
	
	public void updateOnAddition(Card c);
	
	public void updateOnRemoval(ArrayList<Card> removedCards);

}
