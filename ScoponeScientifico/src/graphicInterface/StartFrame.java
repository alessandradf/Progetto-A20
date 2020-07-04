package graphicInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import graphicInterfaceController.GUIController;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JTextField;

public class StartFrame extends JFrame {

	private JPanel contentPane;
	private ArrayList<String> s;
	private int humanPlayers;
	private int computerPlayers;
	private JTextField txtInserireNome;
	private JTextField txtInserireNome_2;
	private JTextField txtInserireNome_1;
	private JTextField txtInserireNome_3;

	/**
	 * Launch the application.
	 */




	public static void main(String[] args) {

	


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        this.setLocation(x, y);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		panel.setForeground(new Color(0, 128, 0));
		contentPane.add(panel, BorderLayout.NORTH);
		
		s = new ArrayList<String>();
		
		String s1 = "Computer Player";
		String s2 = "Human Player";
		
		s.add(s1);
		s.add(s2);
		
		JComboBox comboBox = new JComboBox(s.toArray());
		
		JComboBox comboBox_1 = new JComboBox(s.toArray());

		JComboBox comboBox_2 = new JComboBox(s.toArray());

		JComboBox comboBox_3 = new JComboBox(s.toArray());
		
		JComboBox[] jComboBox = {comboBox, comboBox_1, comboBox_2, comboBox_3};
		
		this.humanPlayers = 0;
		this.computerPlayers = 0;
		
		txtInserireNome = new JTextField();
		//txtInserireNome.setText("Inserire nome");
		txtInserireNome.setColumns(10);
		
		txtInserireNome_2 = new JTextField();
		//txtInserireNome_2.setText("Inserire nome");
		txtInserireNome_2.setEnabled(false);
		txtInserireNome_2.setColumns(10);
		
		txtInserireNome_1 = new JTextField();
		//txtInserireNome_1.setText("Inserire nome");
		txtInserireNome_1.setEnabled(false);
		txtInserireNome_1.setColumns(10);
		
		txtInserireNome_3 = new JTextField();
		//txtInserireNome_3.setText("Inserire nome");
		txtInserireNome_3.setEnabled(false);
		txtInserireNome_3.setColumns(10);
		
		JTextField[] textFieldArray = {txtInserireNome, txtInserireNome_2, txtInserireNome_1, txtInserireNome_3};
		
		for (JTextField jTextField : textFieldArray) {
			jTextField.setEnabled(false);
		}
		
		int i = 0;
		
		for (JComboBox jComboBox2 : jComboBox) {
			jComboBox2.addActionListener(new ItemChangeListener(textFieldArray[i], jComboBox2));
			i++;
		}
		
		JButton btnStartGame = new JButton("Start Game!");
		btnStartGame.setForeground(Color.RED);
		btnStartGame.addActionListener(new StartGameBottonListener(jComboBox, textFieldArray, this));
		
		JLabel label = new JLabel("1");
		label.setForeground(Color.YELLOW);
		label.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		label.setBackground(Color.YELLOW);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("2");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		
		JLabel label_1 = new JLabel("3");
		label_1.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		label_1.setForeground(Color.YELLOW);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("4");
		lblNewLabel_1.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		String[] maxPuntiGioco = {"Punteggio", "20", "25", "30", "35"};
		
		JComboBox comboBox_4 = new JComboBox(maxPuntiGioco);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(6, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(6)
											.addComponent(txtInserireNome_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(65)
											.addComponent(lblNewLabel)))
									.addGap(101)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnStartGame, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(6)
											.addComponent(txtInserireNome_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(6)
										.addComponent(txtInserireNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(45)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(6)
													.addComponent(txtInserireNome_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addGap(70))))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label)))
							.addGap(6))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addGap(264))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInserireNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(81)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(lblNewLabel_1))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtInserireNome_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtInserireNome_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(31)
									.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(167, Short.MAX_VALUE)
							.addComponent(btnStartGame)
							.addGap(83)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(txtInserireNome_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);	
	}

	public int getHumanPlayers() {
		return humanPlayers;
	}

	public void setHumanPlayers(int humanPlayers) {
		this.humanPlayers = humanPlayers;
	}

	public int getComputerPlayers() {
		return computerPlayers;
	}

	public void setComputerPlayers(int computerPlayers) {
		this.computerPlayers = computerPlayers;
	}
}
