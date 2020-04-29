package model;
/**
 * 
 * @author TreCani
 *
 */
public class Card {
	
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
	
	
	
}
