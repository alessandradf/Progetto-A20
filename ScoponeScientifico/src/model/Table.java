package model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Andrea
 *
 */
public class Table {

	private Set<Card> cardsOnTable;
	
	/*
	 * Initialize the table
	 * The cards Set is initially empty
	 */
	public Table() {
		this.cardsOnTable = new HashSet<Card>();
	}
	
	
	/*
	 * Returns a List containing the cards on table. It also prints them
	 */
	public Set<Card> readCards(){
		for(Card c : cardsOnTable) {
			System.out.println(c.toString());
		}	
		
		return cardsOnTable;
	}
	
	
	/*
	 * Adds a card on the table
	 */
	public void putCard(Card playedCard) {
		this.cardsOnTable.add(playedCard);
	}
	
	
	/*
	 * Removes the specified cards from the table
	 */
	public void removeCards(Card[] cards) {
		for(Card c : cardsOnTable) {
			for(Card c1 : cards) {
				if(c.equals(c1)){
					cardsOnTable.remove(c1);
				}
			}
		}
	}
	
	
	/*
	 * Removes all the cards from the table
	 */
	public void clearTable() {
		this.cardsOnTable = new HashSet<Card>();
	}
	
	
	
}
