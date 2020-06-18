package graphicInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;



public class StartGameBottonListener implements ActionListener{
	
	private JComboBox[] comboBox;
	private StartFrame frame;
	
	public StartGameBottonListener(JComboBox[] comboBox, StartFrame frame) {
		this.comboBox = comboBox;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (JComboBox jComboBox : comboBox) {
			if(jComboBox.getSelectedItem().toString().equals("Human Player")) {
				frame.setHumanPlayers(frame.getHumanPlayers()+1);
			}
			else frame.setComputerPlayers(frame.getComputerPlayers()+1);
		}
	}

}
