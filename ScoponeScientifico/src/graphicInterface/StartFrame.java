package graphicInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import javax.swing.JTextField;

/**
 * Extends {@link JFrame} and allows the user to set the game's options
 */
@SuppressWarnings("serial")
public class StartFrame extends JFrame {

	private JPanel contentPane;
	private int humanPlayers;
	private int computerPlayers;
	private JTextField txtInserireNome;
	private JTextField txtInserireNome_2;
	private JTextField txtInserireNome_1;
	private JTextField txtInserireNome_3;

	/**
	 * Creates the Frame with 4 JComboBox to choose teams and players (human or
	 * computer), 1 JComboBox to choose the score to reach and the JButton to start
	 * the game
	 * 
	 * @see JComboBox
	 * @see JButton
	 * 
	 */
	@SuppressWarnings("unchecked")
	public StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		this.setLocation(x, y);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 3, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		panel.setForeground(new Color(0, 128, 0));
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setForeground(new Color(0, 128, 0));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(20, 17, 163, 27);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Computer Player", "Human Player" }));
		panel_1.add(comboBox);

		JLabel lblTeam = new JLabel("Team 1");
		lblTeam.setBounds(20, 0, 163, 16);
		lblTeam.setForeground(Color.YELLOW);
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblTeam);

		txtInserireNome = new JTextField();
		txtInserireNome.setEnabled(false);
		txtInserireNome.setBounds(20, 45, 163, 26);
		panel_1.add(txtInserireNome);
		txtInserireNome.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 0));
		panel_2.setForeground(new Color(0, 128, 0));
		contentPane.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 0));
		panel_3.setForeground(new Color(0, 128, 0));
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(0, 42, 163, 26);
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] { "Computer Player", "Human Player" }));
		panel_3.add(comboBox_1);

		JLabel lblTeam_2 = new JLabel("Team 2");
		lblTeam_2.setBounds(0, 26, 163, 16);
		lblTeam_2.setForeground(Color.YELLOW);
		lblTeam_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblTeam_2);

		txtInserireNome_2 = new JTextField();
		txtInserireNome_2.setEnabled(false);
		txtInserireNome_2.setBounds(0, 68, 163, 26);
		panel_3.add(txtInserireNome_2);
		txtInserireNome_2.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 128, 0));
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JButton btnNewButton = new JButton("Start Game!");
		btnNewButton.setBounds(44, 43, 116, 29);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		panel_4.add(btnNewButton);

		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setBounds(44, 72, 119, 48);
		comboBox_4.setBorder(new TitledBorder("Punteggio"));
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] { "5", "10", "15" }));
		panel_4.add(comboBox_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 128, 0));
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setBounds(27, 44, 163, 26);
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] { "Computer Player", "Human Player" }));
		panel_5.add(comboBox_3);

		JLabel lblTeam_3 = new JLabel("Team 2");
		lblTeam_3.setBounds(27, 27, 163, 16);
		lblTeam_3.setForeground(Color.YELLOW);
		lblTeam_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblTeam_3);

		txtInserireNome_3 = new JTextField();
		txtInserireNome_3.setEnabled(false);
		txtInserireNome_3.setBounds(27, 70, 163, 26);
		panel_5.add(txtInserireNome_3);
		txtInserireNome_3.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 128, 0));
		contentPane.add(panel_6);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0, 128, 0));
		contentPane.add(panel_7);
		panel_7.setLayout(null);

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(20, 71, 163, 26);
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] { "Computer Player", "Human Player" }));
		panel_7.add(comboBox_2);

		JLabel lblTeam_1 = new JLabel("Team 1");
		lblTeam_1.setBounds(20, 52, 163, 16);
		lblTeam_1.setForeground(Color.YELLOW);
		lblTeam_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblTeam_1);

		txtInserireNome_1 = new JTextField();
		txtInserireNome_1.setEnabled(false);
		txtInserireNome_1.setBounds(20, 96, 163, 26);
		panel_7.add(txtInserireNome_1);
		txtInserireNome_1.setColumns(10);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 128, 0));
		contentPane.add(panel_8);

		
		@SuppressWarnings("rawtypes")
		JComboBox[] jComboBox = { comboBox, comboBox_1, comboBox_2, comboBox_3 };

		this.humanPlayers = 0;
		this.computerPlayers = 0;

		JTextField[] textFieldArray = { txtInserireNome, txtInserireNome_2, txtInserireNome_1, txtInserireNome_3 };

		for (JTextField jTextField : textFieldArray) {
			jTextField.setEnabled(false);
		}

		int i = 0;

		for (JComboBox<String> jComboBox2 : jComboBox) {
			jComboBox2.addActionListener(new ItemChangeListener(textFieldArray[i], jComboBox2));
			i++;
		}

		btnNewButton.addActionListener(new StartGameBottonListener(jComboBox, comboBox_4, textFieldArray, this));

	}

	/**
	 * @return number of human players
	 */
	public int getHumanPlayers() {
		return humanPlayers;
	}

	/**
	 * Sets the number of human players
	 * 
	 * @param humanPlayers number of human players
	 */
	public void setHumanPlayers(int humanPlayers) {
		this.humanPlayers = humanPlayers;
	}

	/**
	 * @return number of computer players
	 */
	public int getComputerPlayers() {
		return computerPlayers;
	}

	/**
	 * Sets the number of computer players
	 * 
	 * @param computerPlayers number of computer players
	 */
	public void setComputerPlayers(int computerPlayers) {
		this.computerPlayers = computerPlayers;
	}
}
