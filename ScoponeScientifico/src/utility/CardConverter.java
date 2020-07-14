package utility;

import java.util.StringTokenizer;

import graphicInterface.CardLabel;
import model.Card;
import model.SeedType;


/**
 * This class provides static methods to convert a {@link Card} from the model
 * to the corresponding {@link CardLabel} from the GUI, and the other way around
 */
public class CardConverter {
	
	private CardConverter() {}

	/**
	 * Converts a {@link Card} into a {@link CardLabel}
	 * 
	 * @param c the {@link Card} to be converted
	 * @return the {@link CardLabel} corresponding to c
	 */
	public static CardLabel toCardLabel(Card c) {
		String imagePath = "Resources/Cards/" + c.getValue() + "di" + c.getSeed() + ".png";
		return new CardLabel(c.getSeed(), c.getValue(), imagePath);
	}
	
	/**
	 * Converts a {@link CardLabel} into a {@link Card}
	 * 
	 * @param ic: the {@link CardLabel}
	 * @return the {@link Card} corresponding to ic
	 */
	public static Card toModelCard(CardLabel ic) {
		return new Card(ic.getValue(), ic.getSeed());
	}
	
	/**
	 * Converts a {@link String} representing a card into a {@link Card} 
	 * 
	 * @param CardString the {@link String} representation of a card
	 * @return the {@link Card}
	 */
	public static Card toModelCard(String CardString) {
		StringTokenizer tokenizer = new StringTokenizer(CardString);
		return new Card(Integer.parseInt(tokenizer.nextToken()), SeedType.valueOf(tokenizer.nextToken())); 
		
	}
}
