package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Scrollbar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.plaf.ScrollBarUI;

import CardTest.CardLabel;
import graphicInterfaceController.GUIController;
import model.Card;
import model.SeedType;
import utility.CardConverter;
import utility.TeamObserver;

public class TeamPanel extends JPanel implements TeamObserver {
	private JLabel scoreTeamlbl;
	private JPanel scoreTeamPanel;
	private JPanel scope;
	private CardLabel ultimaScopa;
	private int scoreTeam;
	private int teamNumber;

	public TeamPanel(int teamNumber) {
		// TODO Auto-generated constructor stub

		this.teamNumber = teamNumber;

		ultimaScopa = null;

		this.setLayout(new GridLayout(1, 2));

		setBackground(new Color(0, 128, 0));

		scoreTeamlbl = new JLabel("Score Team" + teamNumber);
		scoreTeamPanel = new JPanel();
		scoreTeamPanel.setBorder(BorderFactory.createTitledBorder("Punteggio"));
		scoreTeamPanel.setBackground(new Color(0, 128, 0));
		scoreTeamPanel.setLayout(new BorderLayout());
		scoreTeamPanel.add(scoreTeamlbl, BorderLayout.CENTER);

		scope = new JPanel();
		scope.setLayout(new GridLayout(1, 1));
		scope.setBackground(new Color(0, 128, 0));
		scope.setBorder(BorderFactory.createTitledBorder("Ultima Scopa"));

		/*
		 * String[] paths = new String[2]; paths[0] = "Resources/Cards/2diCUORI.png";
		 * paths[1] = paths[0];
		 * 
		 * scope.add(new CardLabel(SeedType.CUORI, 2, paths));
		 */

		add(scope);
		add(scoreTeamPanel);

	}

	@Override
	public void updateScore(int score) {
		// TODO Auto-generated method stub
		scoreTeam = score;
		scoreTeamlbl.setText("Punteggio Team" + teamNumber + ":\n " + scoreTeam);
		this.repaint();
		this.validate();
	}

	@Override
	public void scopa(Card scopaCard) {
		// TODO Auto-generated method stub
		if (ultimaScopa != null) {
			scope.remove(ultimaScopa);
		}
		ultimaScopa = CardConverter.toCardLabel(scopaCard);
		GUIController.getDefaultGUIController().updateHistoryScopa(scopaCard);
		scope.add(ultimaScopa);
		scope.repaint();
		scope.validate();

	}

}
