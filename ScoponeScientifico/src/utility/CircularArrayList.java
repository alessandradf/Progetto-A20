package utility;

import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E> {

	public E unlockNext(int t) {
		return get(t % size());
	}
}
