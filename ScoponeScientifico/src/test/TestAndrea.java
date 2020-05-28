package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import java.util.Iterator;

import model.*;

public class TestAndrea {
	
	//SERVE A ME PER PASTICCIARE, NON TOCCATE STA CLASSE
	public static void main(String[] args) {
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
