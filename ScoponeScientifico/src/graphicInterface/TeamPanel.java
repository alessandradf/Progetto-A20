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
import javax.swing.border.TitledBorder;
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
	
	private JLabel scoreLastHand1;

	public TeamPanel(int teamNumber) {
		// TODO Auto-generated constructor stub

		this.teamNumber = teamNumber;

		ultimaScopa = null;

		this.setLayout(new GridLayout(1, 2));

		setBackground(new Color(0, 128, 0));

		scoreTeamlbl = new JLabel("<html><body><p style=\"font-size:10px;\">Punteggio totale: </p></body></html>");
		scoreLastHand1 = new JLabel("<html><body><p style=\"font-size:10px;\">Ultima mano: </p></body></html>");
		scoreTeamPanel = new JPanel();
		scoreTeamPanel.setBorder(BorderFactory.createTitledBorder("Punteggio Team" + teamNumber));
		scoreTeamPanel.setBackground(new Color(0, 128, 0));
		scoreTeamPanel.setLayout(new GridLayout(2, 1));
		scoreTeamPanel.add(scoreTeamlbl);
		scoreTeamPanel.add(scoreLastHand1);

		scope = new JPanel();
		scope.setLayout(new GridLayout(1, 1));
		scope.setBackground(new Color(0, 128, 0));
		scope.setBorder(BorderFactory.createTitledBorder("Ultima Scopa Team" + teamNumber));

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
	public void updateScore(int score, int lastHandScore) {
		// TODO Auto-generated method stub
		scoreTeam = score;
		scoreTeamlbl.setText("<html><body><p style=\"font-size:10px;\">Punteggio totale:" + score + "</p></body></html>");
		scoreLastHand1.setText("<html><body><p style=\"font-size:10px;\">Ultima mano:" + lastHandScore + "</p></body></html>");
		this.repaint();
		this.validate();
	}

	@Override
	public void scopa(Card scopaCard) {
		// TODO Auto-generated method stub
		this.clear();
		ultimaScopa = CardConverter.toCardLabel(scopaCard);
		scope.add(ultimaScopa);
		scope.repaint();
		scope.validate();

	}
	
	public void clear() {
		if (ultimaScopa != null) {
			scope.remove(ultimaScopa);
		}
	}

}
