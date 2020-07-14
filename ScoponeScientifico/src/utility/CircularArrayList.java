package utility;

import java.util.ArrayList;

/**
 * This class provides a particular {@link ArrayList} which behaves like a circular queue
 */
public class CircularArrayList<E> extends ArrayList<E> {

	/**
	 * Returns the element in this list corresponding to the specified index.
	 * If the index exceeds the size of the list then the counter starts back at the beginning.
	 * 
	 * @param t the index of the element
	 * @return the element E at the specified position
	 */
	public E unlockNext(int t) {
		return get(t % size());
	}
}
