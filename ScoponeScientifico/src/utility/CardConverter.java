package utility;

import java.util.StringTokenizer;

import CardTest.CardLabel;
import model.Card;
import model.SeedType;
import CardTest.CardLabel;
//import graphicInterface.InterfaceCard?


/*
 * TODO: testare se questi metodi funzionano correttamente
 */

/**
 * Fornisce alcuni metodi statici per convertire una carta del dominio in una carta dell'interfaccia e viceversa
 * @author Andrea
 *
 */
public class CardConverter {
	
	private CardConverter() {} // per evitare casino col javadoc

	public static CardLabel toCardLabel(Card c) {
		String[] imagePath = new String[2];
		imagePath[0] = "Resources/Cards/" + c.getValue() + "di" + c.getSeed() + ".png";
		return new CardLabel(c.getSeed(), c.getValue(), imagePath);
	}
	
	public static Card toModelCard(CardLabel ic) {
		return new Card(ic.getValue(), ic.getSeed());
	}
	
	public static Card toModelCard(String CardString) {
		StringTokenizer tokenizer = new StringTokenizer(CardString);
		return new Card(Integer.parseInt(tokenizer.nextToken()), SeedType.valueOf(tokenizer.nextToken())); 
		
	}
}
