package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TotalFrame extends JFrame {

	/*
	 * prova di come potrebbe essere l'interfaccia totale. I pannelli sono ancora
	 * tutti da implementare (quello del player ad esempio sar� quello che c'� gi�
	 * trasformato da JFrame a JPanel)
	 */

	private JPanel contentPane;
	private JFrame thisFrame;
	private JPanel PlayerPanel;
	private JPanel tablePanel;
	private String playerName;
	

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TotalFrame frame = new TotalFrame("ciccio");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public TotalFrame(String playerName, JPanel tablePanel) {
		thisFrame = this;
		this.playerName = playerName;
		this.tablePanel = tablePanel;

		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 973, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 4.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 2.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// Pannello che rappresenta il tavolo
		JPanel TablePanel = new JPanel();
		TablePanel.setBackground(new Color(0, 100, 0));
		GridBagConstraints gbc_TablePanel = new GridBagConstraints();
		gbc_TablePanel.insets = new Insets(0, 0, 5, 5);
		gbc_TablePanel.fill = GridBagConstraints.BOTH;
		gbc_TablePanel.gridx = 0;
		gbc_TablePanel.gridy = 0;
		contentPane.add(TablePanel, gbc_TablePanel);
		TablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// pannello con i mazzi dei team
		JPanel TeamsPanel = new JPanel();
		TeamsPanel.setBackground(new Color(0, 100, 0));
		GridBagConstraints gbc_TeamsPanel = new GridBagConstraints();
		gbc_TeamsPanel.gridheight = 2;
		gbc_TeamsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_TeamsPanel.fill = GridBagConstraints.BOTH;
		gbc_TeamsPanel.gridx = 1;
		gbc_TeamsPanel.gridy = 0;
		contentPane.add(TeamsPanel, gbc_TeamsPanel);
		TeamsPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel Team1 = new JLabel("Team1");
		TeamsPanel.add(Team1);

		JLabel Team2 = new JLabel("Team2");
		TeamsPanel.add(Team2);

		// pannello del giocatore
		 PlayerPanel = new JPanel();
		GridBagConstraints gbc_PlayerPanel = new GridBagConstraints();
		gbc_PlayerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_PlayerPanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerPanel.gridx = 0;
		gbc_PlayerPanel.gridy = 1;
		contentPane.add(PlayerPanel, gbc_PlayerPanel);
	}

	public JPanel getPlayerPanel() {
		return PlayerPanel;
	}

}
