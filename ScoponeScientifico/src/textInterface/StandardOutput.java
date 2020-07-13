package textInterface;

import utility.OutputInterface;

/**
 * Allows to write on the {@link StandardOutput} through the
 * {@link OutputInterface} It's used from the TUIController to set the History.
 * 
 * @see TUIController
 */
public class StandardOutput implements OutputInterface {

	/**
	 * Allows to write a String on the {@link StandardOutput}
	 * 
	 * @param update string that has to be written
	 */
	@Override
	public void writeOnOutput(String update) {
		// TODO Auto-generated method stub
		System.out.println(update);

	}

}
