package graphicInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import controller.GameStartSetup;
import graphicInterfaceController.GUIController;
import utility.TableObserver;
import utility.TeamObserver;



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
		GameStartSetup g = new GameStartSetup(config, frame.getHumanPlayers());
		GUIController.getDefaultGUIController().init(g.getHumanPlayers(), g.getGameController());
		g.getGameController().init();
		ArrayList< TeamObserver> team1Observers = new ArrayList<TeamObserver>();
		for (TeamPanel teamPanel : GUIController.getDefaultGUIController().getTeam1Panels()) {
			team1Observers.add(teamPanel);
		}
		ArrayList<TeamObserver> team2Observers = new ArrayList<TeamObserver>();
		
		for (TeamPanel teamPanel : GUIController.getDefaultGUIController().getTeam2Panels()) {
			team2Observers.add(teamPanel);
		}
		
		
		g.addObservers((TableObserver)GUIController.getDefaultGUIController(),team1Observers, team2Observers);
		
	}

}
