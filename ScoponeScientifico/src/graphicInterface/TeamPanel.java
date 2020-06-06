package graphicInterface;

import javax.swing.JPanel;

import CardTest.CardLabel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

public class TeamPanel extends JPanel /* implements TeamObserver*/{
	private JPanel team1;
	private JPanel team2;
	//private ArrayList<CardLabel> cardsTeam1List;
	//private ArrayList<CardLabel> cardsTeam2List;
	private JLabel scoreTeam1Label;
	private JLabel scoreTeam2Label;
	private int ScoreTeam1;
	private int ScoreTeam2;
	
	
	/**
	 * Create the panel.
	 */
	public TeamPanel() {
		setLayout(new GridLayout(2, 1, 0, 0));
		
		team2 = new JPanel();
		team2.setBackground(new Color(0, 128, 0));
		add(team2);
		GridBagLayout gbl_team2 = new GridBagLayout();
		gbl_team2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_team2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_team2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_team2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		team2.setLayout(gbl_team2);
		
		JLabel lblCardsteam2 = new JLabel("CardsTeam2");
		GridBagConstraints gbc_lblCardsteam = new GridBagConstraints();
		gbc_lblCardsteam.gridheight = 3;
		gbc_lblCardsteam.insets = new Insets(0, 0, 5, 5);
		gbc_lblCardsteam.gridx = 5;
		gbc_lblCardsteam.gridy = 2;
		team2.add(lblCardsteam2, gbc_lblCardsteam);
		
		scoreTeam2Label = new JLabel("Score Team 2");
		GridBagConstraints gbc_scoreTeam2Label = new GridBagConstraints();
		gbc_scoreTeam2Label.fill = GridBagConstraints.HORIZONTAL;
		gbc_scoreTeam2Label.gridheight = 3;
		gbc_scoreTeam2Label.gridwidth = 2;
		gbc_scoreTeam2Label.insets = new Insets(0, 0, 0, 5);
		gbc_scoreTeam2Label.gridx = 10;
		gbc_scoreTeam2Label.gridy = 2;
		team2.add(scoreTeam2Label, gbc_scoreTeam2Label);
		
		team1 = new JPanel();
		team1.setBackground(new Color(0, 128, 0));
		add(team1);
		GridBagLayout gbl_team1 = new GridBagLayout();
		gbl_team1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_team1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_team1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_team1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		team1.setLayout(gbl_team1);
		
		scoreTeam1Label =  new JLabel("Score Team 1");
		GridBagConstraints gbc_scoreTeam1Label = new GridBagConstraints();
		gbc_scoreTeam1Label.gridwidth = 2;
		gbc_scoreTeam1Label.insets = new Insets(0, 0, 0, 5);
		gbc_scoreTeam1Label.gridheight = 3;
		gbc_scoreTeam1Label.gridx = 10;
		gbc_scoreTeam1Label.gridy = 2;
		team1.add(scoreTeam1Label, gbc_scoreTeam1Label);
		
		JLabel lblcardsTeam1 = new JLabel("CardsTeam1");
		GridBagConstraints gbc_cardsTeam1 = new GridBagConstraints();
		gbc_cardsTeam1.gridheight = 3;
		gbc_cardsTeam1.insets = new Insets(0, 0, 5, 5);
		gbc_cardsTeam1.gridx = 5;
		gbc_cardsTeam1.gridy = 2;
		team1.add(lblcardsTeam1, gbc_cardsTeam1);
		
		
		setMinimumSize(new Dimension(200, 200));
		setMaximumSize(new Dimension(200, 200));
		setPreferredSize(new Dimension(200, 200));

	}
//metodi che mi servono per l'interfaccia observer
	/*
	 *public void updateScore(int scoreTeam1, int scoreTeam2){
	 *scoreTeam1Label.setText("score 1: " + scoreTeam1);
	 *scoreTeam2Label.settext("score 2: " + scoreTeam2);
	 *this.repaint;
	 *this.validate;
	 *}
	 *
	 *public void updateScopaTeam1(CardLabel scopaTeam1){
	 *}
	 *
	 *public void updateScopaTeam2(CardLabel scopaTeam2){
	 *}
	 *
	 *public void updatecartePreseTeam1(){
	 *}
	 *
	 *public void updateCartePreseTeam2(){
	 *}
	 *
	 */
}
