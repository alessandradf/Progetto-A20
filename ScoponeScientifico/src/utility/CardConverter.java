package utility;

import java.util.StringTokenizer;

import graphicInterface.CardLabel;
import model.Card;
import model.SeedType;


/**
 * This class provides static methods to convert a Card from the model object 
 * to the corresponding GUI object, and the other way around
 */
public class CardConverter {
	
	private CardConverter() {}

	/**
	 * Converts a Card from the model into a CardLabel
	 * 
	 * @param c: the card to be converted
	 * @return the CardLabel corresponding to c
	 */
	public static CardLabel toCardLabel(Card c) {
		String[] imagePath = new String[2];
		imagePath[0] = "Resources/Cards/" + c.getValue() + "di" + c.getSeed() + ".png";
		return new CardLabel(c.getSeed(), c.getValue(), imagePath);
	}
	
	/**
	 * Converts a CardLabel into a Card
	 * 
	 * @param ic: the CardLabel
	 * @return the Card corresponding to ic
	 */
	public static Card toModelCard(CardLabel ic) {
		return new Card(ic.getValue(), ic.getSeed());
	}
	
	/**
	 * Converts a String representing a Card into a Card 
	 * 
	 * @param CardString: the String representation of a card
	 * @return the Card
	 */
	public static Card toModelCard(String CardString) {
		StringTokenizer tokenizer = new StringTokenizer(CardString);
		return new Card(Integer.parseInt(tokenizer.nextToken()), SeedType.valueOf(tokenizer.nextToken())); 
		
	}
}
