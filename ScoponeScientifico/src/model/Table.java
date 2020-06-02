package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import utility.TableObserver;

/**
 *
 * @author Stefano
 *
 */
public class Table {

	private ArrayList<Card> cardsOnTable;	//Rappresenta l'insieme delle carte sul tavolo
	private TableObserver obs;	//observer del tavolo
	
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
		this.obs.updateOnAddition(playedCard);
	}
	
	
	/*
	 * Rimuove dal tavolo le carte passate come parametro
	 */
	public void removeCardsFromTable(ArrayList<Card> cards) {
		for(Card c : cardsOnTable) {
			for(Card c1 : cards) {
				if(c.equals(c1)){
					cardsOnTable.remove(c1);
				}
			}
		}
		this.obs.updateOnRemoval(cards);
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
		this.obs = o; 
	}
	
	
}
