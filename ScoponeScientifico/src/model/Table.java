package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import utility.GameProcessor;
import utility.TableObserver;

/**
 *
 * @author Stefano
 *
 */
public class Table {

	private ArrayList<Card> cardsOnTable;	//Rappresenta l'insieme delle carte sul tavolo
	private ArrayList<TableObserver> obs;	//observer del tavolo
	
	/*
	 * Inizializza il tavolo con un ArrayList vuoto
	 */
	public Table() {
		this.cardsOnTable = new ArrayList<Card>();
		this.obs = new ArrayList<TableObserver>();
	}
	
	
	/**
	 * Aggiunge la carta passata a quelle presenti nel tavolo
	 */
	public void putCardOnTable(Card playedCard) {
		ArrayList<Card> toRemove = GameProcessor.trick(cardsOnTable, playedCard);		
		if ( toRemove != null) {
			this.removeCardsFromTable(toRemove);
		} else {	
		
		this.cardsOnTable.add(playedCard);
		this.updateOnAddition(playedCard);
		}
		
	}
	
	
	private void updateOnAddition(Card playedCard) {
		for (TableObserver o : obs)
			o.updateOnAddition(playedCard);		
	}


	/*
	 * Rimuove dal tavolo le carte passate come parametro
	 */
	public void removeCardsFromTable(ArrayList<Card> cards) {
		ArrayList<Card> toRemove = new ArrayList<Card>();
		for(Card c : cardsOnTable) {
			for(Card c1 : cards) {
				if(c.equals(c1)){
					toRemove.add(c);
				}
			}
		}
		this.cardsOnTable.removeAll(toRemove);
		this.updateOnRemoval(cards);
	}
	
	
	/*
	 * Rimuove tutte le carte dal tavolo
	 */
	public void clearTable() {
		this.cardsOnTable.clear();
	}


	public ArrayList<Card> getCardsOnTable(){
		return this.cardsOnTable;
	}
	
	
	public void addObserver(TableObserver o) {
		this.obs.add(o); 
	}
	
	public void updateOnRemoval(ArrayList<Card> cards) {
		for (TableObserver o : obs)
			o.updateOnRemoval(cards);
	}	
		
}
