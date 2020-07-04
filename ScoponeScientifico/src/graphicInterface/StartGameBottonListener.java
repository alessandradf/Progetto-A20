package graphicInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.GameStartSetup;
import graphicInterfaceController.GUIController;
import utility.TableObserver;
import utility.TeamObserver;

public class StartGameBottonListener implements ActionListener {

	private JComboBox[] comboBox;
	private StartFrame frame;
	private JTextField[] textField;

	public StartGameBottonListener(JComboBox[] comboBox, JTextField[] textField, StartFrame frame) {
		this.comboBox = comboBox;
		this.textField = textField;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] config = new String[4];
		ArrayList<String> playerNames = new ArrayList<String>();
		for (int i = 0; i < comboBox.length; i++) {
			if (comboBox[i].getSelectedItem().toString().equals("Human Player")) {
				frame.setHumanPlayers(frame.getHumanPlayers() + 1);
				config[i] = "Human";
				playerNames.add(textField[i].getText());
			} else {
				frame.setComputerPlayers(frame.getComputerPlayers() + 1);
				config[i] = "Computer";
				playerNames.add(null);
			}
		}
		frame.setVisible(false);
		GameStartSetup g;		
		g = new GameStartSetup(config, frame.getHumanPlayers(), GUIController.getDefaultGUIController(), playerNames);		
	}

}
