package controller;

import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E> {
	private static int CURRENT_INDEX = 0;

	public E getNext() {
		CURRENT_INDEX++;
		if (CURRENT_INDEX == 4)
			setCURRENT_INDEX(0);
		return get(CURRENT_INDEX);
	}
	
	public static void setCURRENT_INDEX(int cURRENT_INDEX) {
		CURRENT_INDEX = cURRENT_INDEX;
	}
}
