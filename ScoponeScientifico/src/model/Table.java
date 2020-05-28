package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Stefano
 *
 */
public class Table {

	private ArrayList<Card> cardsOnTable;	//Rappresenta l'insieme delle carte sul tavolo
	
	/*
	 * Inizializza il tavolo con un ArrayList vuoto
	 */
	public Table() {
		this.cardsOnTable = new ArrayList<Card>();
	}
	
	
	/**
	 * Aggiunge la carta passata a quelle presenti nel tavolo
	 */
	public void putCardOnTable(Card playedCard) {
		this.cardsOnTable.add(playedCard);
	}
	
	
	/*
	 * Rimuove dal tavolo le carte passate come parametro
	 */
	public void removeCardsFromTable(Card[] cards) {
		for(Card c : cardsOnTable) {
			for(Card c1 : cards) {
				if(c.equals(c1)){
					cardsOnTable.remove(c1);
				}
			}
		}
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
	
	
}
