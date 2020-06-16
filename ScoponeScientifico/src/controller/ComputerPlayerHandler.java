package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Card;
import model.Player;
import model.SeedType;

public class ComputerPlayerHandler extends AbstractPlayerHandler {
	
	private Random randomGenerator;
	private ComputerMultipleChoiceHandler computerMultipleChoiceHandler;

	public ComputerPlayerHandler(Player player, GameController controller) {
		super(player, controller);
		this.randomGenerator = new Random();
		computerMultipleChoiceHandler = new MultipleChoiceHandler(controller, this);
		
	}

	@Override
	public boolean unlockPlayer() {
		Card c = this.pickACard();
		setPlayedCard(c);
		this.getController().hasPlayed(this);
		return true;
	}
	
	
	@Override
	public boolean lockPlayer() {
		return false;
	}
	
	
	/*
	 * Ritorna una carta casuale dall'ArrayList del Player del modello al quale � associato
	 */
	public Card pickACard() {
		int handSize = this.getPlayer().getHand().size();
		int randIndex = this.randomGenerator.nextInt(handSize);
		return this.getPlayer().getHand().get(randIndex);
	}

	@Override
	public void multipleChoice(ArrayList<ArrayList<Card>> choices) {
		computerMultipleChoiceHandler.computerMultipleChoice(choices);	
	}

	

	
	//La uso per testare, poi la tolgo
	/*
	public static void main(String[] args) {
		
		Player p = new Player("Debug");
		ComputerPlayerHandler ph = new ComputerPlayerHandler(p, getDefaultGameController());
		
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
