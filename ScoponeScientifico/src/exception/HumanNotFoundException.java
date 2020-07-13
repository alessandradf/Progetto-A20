package exception;

import controller.GameStartSetup;


/**
 * Signals that no human players have been entered.
 * 
 * <p> This exception will be thrown by the {@link GameStartSetup} when the no
 * humans are submitted in the game setup.
 *
 */
public class HumanNotFoundException extends IllegalArgumentException {

	/**
	 * Constructs a HumanNotFoundException with null as its error detail message.
	 */
	public HumanNotFoundException() {
		super();
		
	}

	/**
	 * Constructs a HumanNotFoundException with the specified detail message.
	 */
	public HumanNotFoundException(String message) {
		super(message);
		
	}

}
