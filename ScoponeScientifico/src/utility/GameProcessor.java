package utility;

import java.util.ArrayList;
import java.util.List;

import model.Card;

/**
 * This class uses a recursive algorithm to find all combinations of cards,
 * whose sum of values is equal to the value of a given card.
 * 
 * @see Card
 */
public class GameProcessor {

	/**
	 * Search for all possible tricks with a given card.
	 * 
	 * @param onTable    cards on Table
	 * @param playedCard the card played by the player
	 * @return all possible card combinations that can be taken or a empty list if
	 *         no hold is possible
	 */
	public static ArrayList<ArrayList<Card>> searchHandle(List<Card> onTable, Card playedCard) {
		ArrayList<ArrayList<Card>> resultCards = new ArrayList<ArrayList<Card>>();

		int numberOfCard = onTable.size();
		if (numberOfCard == 0)
			return resultCards;

		ArrayList<Card> doubleCard = searchDouble(onTable, playedCard);

		if (doubleCard != null) {
			resultCards.add(doubleCard);
			return resultCards;
		}

		else {
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			for (Card c : onTable) {
				numbers.add(c.getValue());
			}
			int target = playedCard.getValue();
			ArrayList<ArrayList<Integer>> resultNumbers = new ArrayList<>();
			sum_up(numbers, target, resultNumbers);

			if (resultNumbers.size() == 0)
				return resultCards;

			for (int i = 0; i < resultNumbers.size(); i++) {
				ArrayList<Card> comb = new ArrayList<Card>();
				for (int j = 0; j < resultNumbers.get(i).size(); j++) {
					for (Card c : onTable) {
						if (c.getValue() == resultNumbers.get(i).get(j)) {
							comb.add(c);
						}
					}
				}
				resultCards.add(comb);
			}
			return resultCards;
		}

	}

	/**
	 * Facade function of {@code sum_up_recursive}.
	 * 
	 * @param numbers numbers to analyze
	 * @param target  result of the sum
	 * @param result  all combinations of numbers found
	 */
	static void sum_up(ArrayList<Integer> numbers, int target, ArrayList<ArrayList<Integer>> result) {
		sum_up_recursive(numbers, target, new ArrayList<Integer>(), result);
	}

	/**
	 * Finds all combinations of numbers whose sum is equal to target.
	 * 
	 * @param numbers numbers to analyze
	 * @param target  result of the sum
	 * @param partial subset of numbers analyzed
	 * @param result  all combinations of numbers found
	 */
	static void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial,
			ArrayList<ArrayList<Integer>> result) {

		int s = 0;
		for (int x : partial)
			s += x;
		if (s == target) {
			result.add(partial);
		}
		if (s >= target)
			return;
		for (int i = 0; i < numbers.size(); i++) {
			ArrayList<Integer> remaining = new ArrayList<Integer>();
			int n = numbers.get(i);
			for (int j = i + 1; j < numbers.size(); j++)
				remaining.add(numbers.get(j));
			ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
			partial_rec.add(n);
			sum_up_recursive(remaining, target, partial_rec, result);
		}
	}

	/**
	 * Find the pairs of cards i.e. a card with the same value as a card on the
	 * table.
	 * 
	 * @param onTable    cards on Table
	 * @param playedCard the card played by the player
	 * @return the taken card or {@code null} if no pair has been found
	 */
	private static ArrayList<Card> searchDouble(List<Card> onTable, Card playedCard) {
		ArrayList<Card> trick = new ArrayList<Card>();
		for (Card c : onTable) {
			if ((c.getValue() == playedCard.getValue()) && (c.getSeed() != playedCard.getSeed())) {
				trick.add(c);
				return trick;
			}
		}
		return null;
	}

}
