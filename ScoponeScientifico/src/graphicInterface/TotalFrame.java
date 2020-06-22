package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import CardTest.CardLabel;
import controller.GameController;
import controller.HumanPlayerHandler;
import graphicInterfaceController.GUIController;
import model.Player;
import model.SeedType;

public class TotalFrame extends JFrame {

	/*
	 * prova di come potrebbe essere l'interfaccia totale. I pannelli sono ancora
	 * tutti da implementare (quello del player ad esempio sar� quello che c'� gi�
	 * trasformato da JFrame a JPanel)
	 */

	private JPanel contentPane;
	private PlayerPanel playerPanel;
	private TablePanel tablePanel;
	private TotalTeamPanel teamsPanel;
	private String playerNameAndTeam;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		HumanPlayerHandler[] playerProva = new HumanPlayerHandler[1];
		playerProva[0] = new HumanPlayerHandler(new Player(), GameController.getDefaultGameController());
		GUIController prova = GUIController.getDefaultGUIController();
		prova.init(playerProva);
		String[] cardPaths = new String[2];
		cardPaths[0] = "Resources/Cards/2diCUORI.png";
		cardPaths[1] = "Resources/Cards/2diCUORI.png";
		ArrayList<CardLabel> arrayCards = new ArrayList<CardLabel>();
		CardLabel card = new CardLabel(SeedType.CUORI, 2, cardPaths);
		card.addMouseListener(new CardListener(card, new HumanPlayerHandler(new Player(), GameController.getDefaultGameController())));
		arrayCards.add(card);

		TotalFrame prova1 = new TotalFrame("ciao", new TablePanel(), new PlayerPanel(arrayCards));

		prova1.setVisible(true);
	}
	*/

	/**
	 * Create the frame.
	 */
	public TotalFrame(String playerNameAndTeam, TablePanel tablePanel, PlayerPanel playerPanel, TeamPanel team1, TeamPanel team2) {
		this.playerNameAndTeam = playerNameAndTeam;
		this.tablePanel = tablePanel;
		this.playerPanel = playerPanel;
		
		this.setTitle(playerNameAndTeam);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(425, 50, 1005, 705);
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


		teamsPanel = new TotalTeamPanel( team1, team2);
		GridBagConstraints gbc_TeamsPanel = new GridBagConstraints();
		gbc_TeamsPanel.gridheight = 2;
		gbc_TeamsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_TeamsPanel.fill = GridBagConstraints.BOTH;
		gbc_TeamsPanel.gridx = 1;
		gbc_TeamsPanel.gridy = 0;
		contentPane.add(teamsPanel, gbc_TeamsPanel);
		// pannello del giocatore

		GridBagConstraints gbc_PlayerPanel = new GridBagConstraints();
		gbc_PlayerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_PlayerPanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerPanel.gridx = 0;
		gbc_PlayerPanel.gridy = 1;
		contentPane.add(playerPanel, gbc_PlayerPanel);
		
	}

	public JPanel getPlayerPanel() {
		return playerPanel;
	}

	public TablePanel getTablePanel() {
		return tablePanel;
	}

	// metodi per sbloccare e bloccare il frame del giocatore
	public void unlockPlayer() {
		this.setVisible(true);
	}

	public void lockPlayer() {
		this.setVisible(false);
	}

	public TotalTeamPanel getTeamsPanel() {
		return teamsPanel;
	}

}
