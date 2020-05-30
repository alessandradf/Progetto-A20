package controller;

import java.util.ArrayList;
import model.Game;

public class CircularArrayList<E> extends ArrayList<E> {
	private static int CURRENT_INDEX = 0;

	public E getNext() {
		CURRENT_INDEX++;
		if (CURRENT_INDEX == Game.PLAYER_NUMBER)	//Game.PLAYER_NUMBER = 4;
			setCURRENT_INDEX(0);
		return get(CURRENT_INDEX);
	}
	
	private static void setCURRENT_INDEX(int newIndex) {
		CURRENT_INDEX = newIndex;
	}
}
