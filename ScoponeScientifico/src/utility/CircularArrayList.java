package utility;

import java.util.ArrayList;
import model.Game;

public class CircularArrayList<E> extends ArrayList<E> {
	private static int CURRENT_INDEX = 0;

	public E unlockNext() {
		CURRENT_INDEX++;
		if (CURRENT_INDEX == Game.PLAYER_NUMBER)	//Game.PLAYER_NUMBER = 4;
			setCURRENT_INDEX(0);
		return get(CURRENT_INDEX);
	}
	
	public E unlockNext(int t) {
		return get(t % size());
		//Caro prof. Lombardi, le voglio un mondo di bene
	}
	
	private static void setCURRENT_INDEX(int newIndex) {
		CURRENT_INDEX = newIndex;
	}
}
