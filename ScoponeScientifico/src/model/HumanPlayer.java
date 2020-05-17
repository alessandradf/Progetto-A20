package model;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer(String name){
		super(name);
	}
	
	/**
	 * @return Card la carta inserita dall'utente 
	 */
	@Override
	public Card playCard() {
		
		// l'utente inserisce seme e numero della carta da tastiera
		Card card = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the seed");
		String seed = sc.next();
		System.out.println("Enter the value");
		int v = sc.nextInt();
		sc.close();
		
		//trovo la carta inserita da tastiera tra quelle in mano al giocatore
		for (Card c : hand) {
			if ((c.getSeed() == SeedType.valueOf(seed)) && (c.getValue() == v )) {
				card = c;
			}
		}
		return card;
	}
	
	

	/**
	 * @return la combinazione di carte scelte dal giocatore nel turno
	 */
	public List<Card> chooseCards() {
		return null;
	}
}
