package model;

import java.util.ArrayList;
import java.util.List;

import utility.TableObserver;

/**
 * Represents the Table where a game of Scopone is played.
 * This class offers methods to put cards and remove cards from the table, alongside
 * the possibility to add observers which will be notified when a card is put or removed.
 */
public class Table {

	private ArrayList<Card> cardsOnTable; // Rappresenta l'insieme delle carte sul tavolo
	private ArrayList<TableObserver> obs; // observer del tavolo

	/*
	 * Inizializza il tavolo con un ArrayList vuoto
	 */
	/**
	 * Creates a new Table with no cards on it
	 */
	public Table() {
		this.cardsOnTable = new ArrayList<Card>();
		this.obs = new ArrayList<TableObserver>();
	}

	/**
	 * Updates the table, adding one card
	 * @param playedCard the card to be put on the table
	 */
	public void putCardOnTable(Card playedCard) {
		this.cardsOnTable.add(playedCard);
		this.updateOnAddition(playedCard);
	}
	
	
	private void updateOnAddition(Card playedCard) {
		for (TableObserver o : obs)
			o.updateOnAddition(playedCard);
	}

	/**
	 * Removes from the table all the cards specified in the collection
	 * @param cards all the Cards to be removed
	 */
	public void removeCardsFromTable(List<Card> cards) {
		if (cards != null) {
			this.cardsOnTable.removeAll(cards);
			this.updateOnRemoval(cards);
		}
	}

	/**
	 * Removes all the Cards from the table
	 */
	public void clearTable() {
		updateOnRemoval(this.getCardsOnTable());
		this.cardsOnTable.clear();
	}

	/**
	 * 
	 * @return the Cards currently on the Table
	 */
	public ArrayList<Card> getCardsOnTable() {
		return this.cardsOnTable;
	}
	/**
	 * Add a single TableObserver to the table
	 * @param o the TableObserver to be added
	 */
	public void addObserver(TableObserver o) {
		this.obs.add(o);
	}

	public void updateOnRemoval(List<Card> toRemove) {
		for (TableObserver o : obs)
			o.updateOnRemoval(toRemove);
	}

}
