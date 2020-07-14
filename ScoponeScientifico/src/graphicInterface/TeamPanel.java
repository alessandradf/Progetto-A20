package graphicInterface;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Card;
import model.Team;
import utility.CardConverter;
import utility.TeamObserver;

/**
 * Extends {@link JPanel} and implements {@link TeamObserver} It shows the
 * team's scores and the last "scopa" made
 * 
 * @see Team
 * 
 */
@SuppressWarnings("serial")
public class TeamPanel extends JPanel implements TeamObserver {
	private JLabel scoreTeamlbl;
	private JPanel scoreTeamPanel;
	private JPanel scope;
	private CardLabel ultimaScopa;
	private JLabel scoreLastHand1;

	/**
	 * Creates the panel
	 * 
	 * @param teamNumber team's number id
	 */
	public TeamPanel(int teamNumber) {
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
		add(scope);
		add(scoreTeamPanel);

	}

	/**
	 * Updates the team's score displayed
	 * 
	 * @param score         total team's score
	 * @param lastHandScore team's score in the last hand
	 */
	@Override
	public void updateScore(int score, int lastHandScore) {

		scoreTeamlbl
				.setText("<html><body><p style=\"font-size:10px;\">Punteggio totale:" + score + "</p></body></html>");
		scoreLastHand1.setText(
				"<html><body><p style=\"font-size:10px;\">Ultima mano:" + lastHandScore + "</p></body></html>");
		this.repaint();
		this.validate();
	}

	/**
	 * Updates the last scopa displayed
	 * 
	 * @param scopaCard card's that represents the last scopa
	 * @see Card
	 */
	@Override
	public void scopa(Card scopaCard) {
		// TODO Auto-generated method stub
		this.clear();
		ultimaScopa = CardConverter.toCardLabel(scopaCard);
		scope.add(ultimaScopa);
		scope.repaint();
		scope.validate();

	}

	/**
	 * Removes the scopa displayed
	 */
	public void clear() {
		if (ultimaScopa != null) {
			scope.remove(ultimaScopa);
		}
	}

}
