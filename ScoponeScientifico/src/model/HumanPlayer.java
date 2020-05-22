package model;

public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name);
	}

	/**
	 * @param card carta scelta dall'utente
	 * @return true se il player possiede la carta e la rimuove dalla propria mano, false altrimenti
	 */
	public boolean playCard(Card card) {
		for (Card c : getHand()) {
			if (c.equals(card)) {
				getHand().remove(c);
				return true;
			}
		}
		return false;
	}

}
