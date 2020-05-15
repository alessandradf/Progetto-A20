package test;

import java.util.ArrayList;

import model.Card;
import model.HumanPlayer;
import model.Player;
import model.SeedType;

/**
 * NON TOCCATEMELA SERVE A ME PER FARE DEI PASTICCI
 * @author TreCani
 *
 */
public class TestAndrea {
	
	public static void main(String args[]) {
		ArrayList<Card> carte = new ArrayList<Card>();
		for(int i = 0; i < 10; i++) {
			carte.add(new Card(i, SeedType.CUORI));
		}
		
		Player p = new HumanPlayer();
		p.setHand(carte);
		Card selectedCard = p.playCard();
		System.out.println(selectedCard.toString());
	}
}
