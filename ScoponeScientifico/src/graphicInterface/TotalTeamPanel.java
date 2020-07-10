package graphicInterface;

import javax.swing.JPanel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

public class TotalTeamPanel extends JPanel /* implements TeamObserver*/{
	private TeamPanel team1;
	private TeamPanel team2;
	//private ArrayList<CardLabel> cardsTeam1List;
	//private ArrayList<CardLabel> cardsTeam2List;
	
	
	
	/**
	 * Create the panel.
	 */
	public TotalTeamPanel(TeamPanel team1, TeamPanel team2) {
		setLayout(new GridLayout(2, 1, 0, 0));
	
		this.team1 = team1;
		add(team1);
		
		this.team2 = team2;
		add(team2);
		
		
		
		setMinimumSize(new Dimension(200, 200));
		setMaximumSize(new Dimension(200, 200));
		setPreferredSize(new Dimension(200, 200));

	}



	public TeamPanel getTeam1() {
		return team1;
	}



	public TeamPanel getTeam2() {
		return team2;
	}

}
