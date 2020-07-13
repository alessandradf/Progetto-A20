package textInterface;

import utility.OutputInterface;



/**
 * Allows to write on the standard output through the OutputInterface.
 * It's used from the TUIController to set the History.
 */
public class StandardOutput implements OutputInterface{

	
	
	@Override
	public void writeOnOutput(String update) {
		// TODO Auto-generated method stub
		System.out.println(update);
		
	}

}
