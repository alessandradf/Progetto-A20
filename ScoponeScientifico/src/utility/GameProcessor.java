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
	 * @return tutte le possibili combinazioni di carte che � possibile prendere  
	 */
	public static ArrayList<ArrayList<Integer>> searchHandle(List<Card> onTable, Card playedCard) {
		int numberOfCard = onTable.size();
		if (numberOfCard == 0)
			return null;
		
		else {
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			for (Card c : onTable) {
				numbers.add(c.getValue());
			}
			int target = playedCard.getValue();
			ArrayList<ArrayList<Integer>> result = new ArrayList<>();
			sum_up_recursive(numbers, target, new ArrayList<Integer>(), result);
			return result;			
		}
	}

	
	static void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial,ArrayList<ArrayList<Integer>> result) {
    	
	       int s = 0;
	       for (int x: partial) s += x;
	       if (s == target) {
	           result.add(partial);
	       }
	       if (s >= target)
	            return;
	       for(int i=0;i<numbers.size();i++) {
	             ArrayList<Integer> remaining = new ArrayList<Integer>();
	             int n = numbers.get(i);
	             for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
	             ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
	             partial_rec.add(n);             
	             sum_up_recursive(remaining,target,partial_rec,result);
	       }
	    }
	
	public static void main(String[] args) {
		ArrayList<Card> onTable = new ArrayList<Card>();
		Card due = new Card(2, SeedType.CUORI);
		Card t = new Card(3, SeedType.CUORI);
		Card c = new Card(5, SeedType.CUORI);
		Card dieci = new Card(10, SeedType.CUORI);
		Card o = new Card(8, SeedType.CUORI);
		Card q = new Card(4, SeedType.CUORI);
		Card u = new Card(1, SeedType.CUORI);
		onTable.add(due); onTable.add(t); onTable.add(dieci); onTable.add(o); onTable.add(q); onTable.add(u);
		ArrayList<ArrayList<Integer>> r = GameProcessor.searchHandle(onTable, c);
		System.out.println(r);
	}
	

}
