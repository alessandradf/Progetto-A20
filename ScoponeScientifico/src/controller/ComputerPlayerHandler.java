package controller;

import java.util.ArrayList;
import java.util.Random;

import exception.CardNotFoundException;
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
		setPlayedCard(pickACard());
		cardPlayed(getPlayedCard());
		return true;
	}

	@Override
	public void cardPlayed(Card c) {
		try {
			super.cardPlayed(c);

			if (getResultFromFetch().size() > 1) {
				getController().endTurn(this, getResultFromFetch().get(0));
				// restituisce sempre la prima scelta, per semplicità
			}
		} catch (CardNotFoundException e) {
			System.out.println("Carta non trovata! Computer cardPlayed");
		}
	}

	@Override
	public boolean lockPlayer() {
		// non fa nulla perchè è un giocatore computer
		return true;
	}

	/*
	 * Ritorna una carta casuale dall'ArrayList del Player del modello al quale �
	 * associato
	 */
	public Card pickACard() {
		int handSize = this.getPlayer().getHand().size();
		int randIndex = this.randomGenerator.nextInt(handSize);
		return this.getPlayer().getHand().get(randIndex);
	}

	@Override
	public void multipleChoice(ArrayList<ArrayList<Card>> choices) {
		try {
		getController().endTurn(this, choices.get(0));
		}
		catch( CardNotFoundException e) {
			System.out.println("Carta non trovata! multipleChoice");
		}
	}

	// La uso per testare, poi la tolgo
	/*
	 * public static void main(String[] args) {
	 * 
	 * Player p = new Player("Debug"); ComputerPlayerHandler ph = new
	 * ComputerPlayerHandler(p, getDefaultGameController());
	 * 
	 * ArrayList<Card> cards = new ArrayList<Card>();
	 * 
	 * cards.add(new Card(3, SeedType.CUORI)); cards.add(new Card(5,
	 * SeedType.FIORI)); cards.add(new Card(5, SeedType.CUORI)); cards.add(new
	 * Card(7, SeedType.PICCHE));
	 * 
	 * p.setHand(cards);
	 * 
	 * System.out.println(p); System.out.println(ph.pickACard()); }
	 */

}
