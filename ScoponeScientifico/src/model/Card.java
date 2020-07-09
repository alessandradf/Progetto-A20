package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represent one card in the game
 */
public class Card implements Comparable<Card> {
	
	/**
	 * the possibile values of the card
	 */
	public static final int CARD_VALUES[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	private SeedType seed;
	private int value;
	/**
	 * Creates a card given seed and value
	 * @param value of the card
	 * @param seed of the card
	 */
	public Card(int value, SeedType seed) {
		this.value = value;
		this.seed = seed;
	}

	/**
	 * @return the seed of the card
	 */
	public SeedType getSeed() {
		return seed;
	}

	/**
	 * @return the value of the card
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue() +" "+getSeed().toString();
	}
	

	@Override
	/**
	 * Indicates whether this Card is equal or not to Object c.
	 * Two Cards are considered equals if seeds and values are the same for both cards.
	 */
	public boolean equals(Object c) {
		if(((Card)c).getSeed() == this.seed && ((Card)c).getValue() == this.getValue()) {
			return true;
		}
		return false;
	}

	@Override
	/**
	 * Compares this Card to the given Card. Returns a negative integer, zero or a positive integer
	 * if this card is less, equals or greater than the specified card.
	 */
	public int compareTo(Card o) {
		if (this.seed.ordinal() != o.getSeed().ordinal()) {
			return this.seed.ordinal() - o.getSeed().ordinal();
		}
		else {
			return this.value - o.getValue();
		}
	}


}
