package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utility.GameProcessor;
import utility.TableObserver;

/**
 *
 * @author Stefano
 *
 */
public class Table {

	private ArrayList<Card> cardsOnTable; // Rappresenta l'insieme delle carte sul tavolo
	private ArrayList<TableObserver> obs; // observer del tavolo

	/*
	 * Inizializza il tavolo con un ArrayList vuoto
	 */
	public Table() {
		this.cardsOnTable = new ArrayList<Card>();
		this.obs = new ArrayList<TableObserver>();
	}

	/**
	 * @param playedCard
	 * @return la prima combinazione di carte che è possibile prendere, null
	 *         altrimenti
	 */
	public List<Card> putCardOnTable(Card playedCard) {
		List<Card> result = GameProcessor.searchHandle(cardsOnTable, playedCard);
		
		
		if (result != null) {
			this.removeCardsFromTable(result);
		} else {
			this.cardsOnTable.add(playedCard);
			this.updateOnAddition(playedCard);
		}
		return result;
	}

	private void updateOnAddition(Card playedCard) {
		for (TableObserver o : obs)
			o.updateOnAddition(playedCard);
	}

	/*
	 * Rimuove dal tavolo le carte passate come parametro
	 */
	public void removeCardsFromTable(List<Card> cards) {
		if (cards != null) {
			this.cardsOnTable.removeAll(cards);
			this.updateOnRemoval(cards);
		}
	}

	/*
	 * Rimuove tutte le carte dal tavolo
	 */
	public void clearTable() {
		this.cardsOnTable.clear();
	}

	public ArrayList<Card> getCardsOnTable() {
		return this.cardsOnTable;
	}

	public void addObserver(TableObserver o) {
		this.obs.add(o);
	}

	public void updateOnRemoval(List<Card> toRemove) {
		for (TableObserver o : obs)
			o.updateOnRemoval(toRemove);
	}

}
