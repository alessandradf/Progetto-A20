package exception;

import controller.HumanPlayerHandler;

/**
 * Signals that an attempt to take a {@code Card} has failed.
 * <p> This exception will be thrown by the {@link HumanPlayerHandler} when the
 * player has no {@code Card} in his hand.
 *
 */
public class CardNotFoundException extends Exception {

	/**
	 * Constructs a CardNotFoundException with null as its error detail message.
	 */
	public CardNotFoundException() {

	}

	/**
	 * Constructs a CardNotFoundException with the specified detail message.
	 */
	public CardNotFoundException(String message) {
		super(message);

	}

}
