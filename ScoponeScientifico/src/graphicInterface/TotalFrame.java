package graphicInterface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Extends {@link JFrame} Personal player's frame, it shows the PlayerPanel, the
 * TablePanel and the TotalTeamPanel
 * 
 * @see PlayerPanel
 * @see TablePanel
 * @see TotalTeamPanel
 *
 */
@SuppressWarnings("serial")
public class TotalFrame extends JFrame {

	private JPanel contentPane;
	private PlayerPanel playerPanel;
	private TablePanel tablePanel;
	private TotalTeamPanel teamsPanel;

	/**
	 * Creates the frame
	 * 
	 * @param playerNameAndTeam names of the player and of his team
	 * @param tablePanel
	 * @param playerPanel
	 * @param team1             team1's panel
	 * @param team2             team2's panel
	 * @see TablePanel
	 * @see PlayerPanel
	 * @see TeamPanel
	 */
	public TotalFrame(String playerNameAndTeam, TablePanel tablePanel, PlayerPanel playerPanel, TeamPanel team1,
			TeamPanel team2) {
		this.tablePanel = tablePanel;
		this.playerPanel = playerPanel;

		this.setTitle(playerNameAndTeam);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1005, 705);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w);
		int y = (dim.height - h) / 5;
		this.setLocation(x, y);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 4.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 2.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc_TablePanel = new GridBagConstraints();
		gbc_TablePanel.insets = new Insets(0, 0, 5, 5);
		gbc_TablePanel.fill = GridBagConstraints.BOTH;
		gbc_TablePanel.gridx = 0;
		gbc_TablePanel.gridy = 0;

		contentPane.add(tablePanel, gbc_TablePanel);

		teamsPanel = new TotalTeamPanel(team1, team2);
		GridBagConstraints gbc_TeamsPanel = new GridBagConstraints();
		gbc_TeamsPanel.gridheight = 2;
		gbc_TeamsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_TeamsPanel.fill = GridBagConstraints.BOTH;
		gbc_TeamsPanel.gridx = 1;
		gbc_TeamsPanel.gridy = 0;
		contentPane.add(teamsPanel, gbc_TeamsPanel);

		GridBagConstraints gbc_PlayerPanel = new GridBagConstraints();
		gbc_PlayerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_PlayerPanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerPanel.gridx = 0;
		gbc_PlayerPanel.gridy = 1;
		contentPane.add(playerPanel, gbc_PlayerPanel);

	}

	/**
	 * @return player's panel
	 * @see PlayerPanel
	 */
	public JPanel getPlayerPanel() {
		return playerPanel;
	}

	/**
	 * @return table's panel
	 * @see TablePanel
	 */
	public TablePanel getTablePanel() {
		return tablePanel;
	}

	/**
	 * @return TotalTeamPanel in which are located the team panels
	 * @see TotalTeamPanel
	 */
	public TotalTeamPanel getTeamsPanel() {
		return teamsPanel;
	}

	/**
	 * Sets the title of the frame
	 * 
	 * @param playerAndTeam names of the player and of his team
	 */
	public void setText(String playerAndTeam) {
		this.setTitle(playerAndTeam);
	}

}
