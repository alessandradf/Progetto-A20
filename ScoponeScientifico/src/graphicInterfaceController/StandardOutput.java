package graphicInterfaceController;

import utility.OutputInterface;

public class StandardOutput implements OutputInterface{

	@Override
	public void writeOnOutput(String update) {
		// TODO Auto-generated method stub
		System.out.println(update);
		
	}

}
