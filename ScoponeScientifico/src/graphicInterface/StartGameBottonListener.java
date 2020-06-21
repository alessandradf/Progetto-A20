package graphicInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import controller.GameStartSetup;



public class StartGameBottonListener implements ActionListener{
	
	private JComboBox[] comboBox;
	private StartFrame frame;
	
	public StartGameBottonListener(JComboBox[] comboBox, StartFrame frame) {
		this.comboBox = comboBox;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] config = new String[4];
		for (int i = 0; i < comboBox.length; i++) {
			if (comboBox[i].getSelectedItem().toString().equals("Human Player")) {
				frame.setHumanPlayers(frame.getHumanPlayers() + 1);
				config[i] = "Human";				
			} else {
				frame.setComputerPlayers(frame.getComputerPlayers() + 1);
				config[i] = "Computer";
			}
		}
		frame.setVisible(false);
		GameStartSetup g = GameStartSetup.getDefaultGameSetup(config, frame.getHumanPlayers());
	}

}
