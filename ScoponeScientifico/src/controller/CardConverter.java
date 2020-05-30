package controller;

import CardTest.CardLabel;
import model.Card;
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
	
	public static CardLabel toCardLabel(Card c) {
		String[] imagePath = new String[2];
		imagePath[0] = "Resources/Cards/" + c.getValue() + "di" + c.getSeed() + ".png";
		return new CardLabel(c.getSeed(), c.getValue(), imagePath);
	}
	
	public static Card toModelCard(CardLabel ic) {
		return new Card(ic.getValue(), ic.getSeed());
	}
}
