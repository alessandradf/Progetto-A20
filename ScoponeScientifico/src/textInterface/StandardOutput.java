package textInterface;

import utility.OutputInterface;



/**
 * allows to write on the standard output through the OutputInterface
 *
 */
public class StandardOutput implements OutputInterface{

	
	
	@Override
	public void writeOnOutput(String update) {
		// TODO Auto-generated method stub
		System.out.println(update);
		
	}

}
