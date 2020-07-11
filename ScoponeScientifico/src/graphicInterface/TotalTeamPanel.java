package graphicInterface;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Dimension;

/**
 * Panel that contains the two team panels
 *
 */
public class TotalTeamPanel extends JPanel {
	private TeamPanel team1;
	private TeamPanel team2;

	/**
	 * Creates the panel
	 * 
	 * @param team1 team1's panel
	 * @param team2 team2's panel
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

	/**
	 * @return team1's panel
	 */
	public TeamPanel getTeam1() {
		return team1;
	}

	/**
	 * @return team2's panel
	 */
	public TeamPanel getTeam2() {
		return team2;
	}

}
