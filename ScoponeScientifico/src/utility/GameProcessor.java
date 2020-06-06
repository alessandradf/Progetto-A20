package utility;

import java.util.ArrayList;
import java.util.List;

import model.Card;

public class GameProcessor {

	public static ArrayList<Card> trick(List<Card> onTable, Card playedCard) {
		ArrayList<Card> trick = new ArrayList<Card>();
		for (Card c : onTable) {
			if (c.getValue() == playedCard.getValue()) {
				trick.add(c);
				trick.add(playedCard);
				return trick;
			}
		}
		return null;
	}

}
