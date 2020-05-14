package model;
/**
 * 
 * @author Andrea
 *
 */
public class Card {
	
	public static final int CARD_VALUES[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	private SeedType seed;
	private int value;
	
	public Card(int value, SeedType seed) {
		this.value = value;
		this.seed = seed;
	}

	/**
	 * @return the seed
	 */
	public SeedType getSeed() {
		return seed;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue() + getSeed().toString();
	}
	
	@Override
	public boolean equals(Object c) {
		if(((Card)c).getSeed() == this.seed && ((Card)c).getValue() == this.getValue()) {
			return true;
		}
		return false;
	}

}
