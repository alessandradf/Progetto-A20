package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exception.MultipleChoiceException;
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
	 * Aggiorna il tavolo a seguito di una carta giocata
	 */
	public void putCardOnTable(Card playedCard) {
		this.cardsOnTable.add(playedCard);
		this.updateOnAddition(playedCard);
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
		updateOnRemoval(this.getCardsOnTable());
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
