package utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Card;
import model.SeedType;
import model.Table;

public class GameProcessor {

	/**
	 * @param onTable carte sul tavolo esclusa playedCard
	 * @param playedCard 
	 * @return lista di carte che è possibile prendere con playedCard, null altrimenti 
	 */
	public static List<Card> searchHandle(List<Card> onTable, Card playedCard) {
		int numberOfCard = onTable.size();
		if (numberOfCard == 0)
			return null;
		else if (numberOfCard == 1)
			return searchDouble(onTable, playedCard);
		else
			return searchCombination(onTable, playedCard, numberOfCard);
	}

	/**
	 * @param onTable 
	 * @param playedCard
	 * @param n numero di carte sul tavolo 
	 * @return lista di carte che è possibile prendere con playedCard, null altrimenti 
	 */
	private static List<Card> searchCombination(List<Card> onTable, Card playedCard, int n) {
		List<Card> result = new ArrayList<Card>();
		ArrayList<Card> copy = new ArrayList<Card>(onTable);
		result = searchDouble(onTable, playedCard);

		if (result != null)
			return result;

		else {
			int[] values = new int[n];
			for (int i = 0; i < n; i++) {
				values[i] = onTable.get(i).getValue();
			}
			int sum;
			for (int j = 0; j < n; j++) {
				for (int k = j + 2; k <= n; k++) {
					sum = Arrays.stream(values, j, k).sum();
					if (sum == playedCard.getValue()) {
						result = copy.subList(j, k);						
						return result;
					}
				}
			}
		}
		return null;
	}

	/**trova le coppie di carte 
	 * @param onTable
	 * @param playedCard
	 * @return una carta sul tavolo uguale a playedCard
	 */
	private static List<Card> searchDouble(List<Card> onTable, Card playedCard) {
		List<Card> trick = new ArrayList<Card>();
		for (Card c : onTable) {
			if ((c.getValue() == playedCard.getValue()) && (c.getSeed() != playedCard.getSeed())) {
				trick.add(c);				
				return trick;
			}
		}
		return null;
	}

}
