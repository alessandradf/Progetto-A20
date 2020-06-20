package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import CardTest.CardLabel;

import java.util.Iterator;

import model.*;
import utility.CardConverter;

public class TestAndrea {
	
	//SERVE A ME PER PASTICCIARE, NON TOCCATE STA CLASSE
	public static void main(String[] args) {
		Card c = new Card(2, SeedType.CUORI);
		CardLabel c1 = CardConverter.toCardLabel(c);
		System.out.println(c1.getSeed() + " " +  c1.getValue());
	}

	/**
	 * Consente di visualizzare un deck di carte passato come Set
	 * 
	 * @param deck
	 */
	public static void seeDeck(Set<Card> deck) {
		ArrayList<String> arrayCarte = new ArrayList<String>();
		Iterator<Card> iterator = deck.iterator();
		while (iterator.hasNext()) {
			arrayCarte.add(iterator.next().toString());
		}
		Collections.sort(arrayCarte);
		for (String s : arrayCarte) {
			System.out.println(s);
		}
	}
}
