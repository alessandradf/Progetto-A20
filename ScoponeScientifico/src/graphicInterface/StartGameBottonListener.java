package graphicInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.GameStartSetup;
import controller.HumanPlayerHandler;
import graphicInterfaceController.GUIController;
import utility.TableObserver;
import utility.TeamObserver;

public class StartGameBottonListener implements ActionListener {

	private JComboBox[] comboBox;
	private JComboBox maxScore;
	private StartFrame frame;
	private JTextField[] textField;

	public StartGameBottonListener(JComboBox[] comboBox, JComboBox maxScore, JTextField[] textField, StartFrame frame) {
		this.comboBox = comboBox;
		this.maxScore = maxScore;
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
		
		
		//setto il punteggio massimo della partita dalla combobox punteggio
		if(maxScore.getSelectedItem().toString().equals("Punteggio")) {
			g = new GameStartSetup(config, GUIController.getDefaultGUIController(), playerNames, 5);
		//	g.getGameController().getGame().setMaxScore(5);
		}
		else {
			g = new GameStartSetup(config, GUIController.getDefaultGUIController(), playerNames, Integer.parseInt(maxScore.getSelectedItem().toString()));
			
		}
		for (HumanPlayerHandler humanPlayerHandler : GUIController.getDefaultGUIController().getPlayers()) {
			GUIController.getDefaultGUIController().getPlayerView().get(humanPlayerHandler).setTitle(humanPlayerHandler.getPlayer().getPlayerName() + " " + humanPlayerHandler.getPlayer().getTeam().getTeamName());
		}
	}

}
