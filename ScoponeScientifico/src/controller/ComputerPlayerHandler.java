package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Card;
import model.Player;
import model.SeedType;

public class ComputerPlayerHandler extends AbstractPlayerHandler {
	
	private Random randomGenerator;

	public ComputerPlayerHandler(Player player, GameController controller) {
		super(player, controller);
		this.randomGenerator = new Random();
	}

	@Override
	public boolean unlockPlayer() {
		Card c = this.pickACard();
		//setPlayedCard(c);
		//this.getController().hasPlayed(this);
		return true;
	}
	
	/*
	 * Ritorna una carta casuale dall'ArrayList del Player del modello al quale è associato
	 */
	public Card pickACard() {
		int handSize = this.getPlayer().getHand().size();
		int randIndex = this.randomGenerator.nextInt(handSize);
		return this.getPlayer().getHand().get(randIndex);
	}

	
	//La uso per testare, poi la tolgo
	/*
	public static void main(String[] args) {
		
		Player p = new Player("Debug");
		ComputerPlayerHandler ph = new ComputerPlayerHandler(p, new GameController());
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(3, SeedType.CUORI));
		cards.add(new Card(5, SeedType.FIORI));
		cards.add(new Card(5, SeedType.CUORI));
		cards.add(new Card(7, SeedType.PICCHE));
		
		p.setHand(cards);
		
		System.out.println(p);
		System.out.println(ph.pickACard());	
	}
	*/
	
}
