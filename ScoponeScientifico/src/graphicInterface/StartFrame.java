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
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;

public class StartFrame extends JFrame {

	private JPanel contentPane;
	private ArrayList<String> s;
	private int humanPlayers;
	private int computerPlayers;

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
		setBounds(100, 100, 455, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		panel.setForeground(new Color(0, 128, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		
		s = new ArrayList<String>();
		
		String s1 = "Human Player";
		String s2 = "Computer Player";
		
		s.add(s1);
		s.add(s2);
		
		JComboBox comboBox = new JComboBox(s.toArray());
		
		JComboBox comboBox_1 = new JComboBox(s.toArray());

		JComboBox comboBox_2 = new JComboBox(s.toArray());

		JComboBox comboBox_3 = new JComboBox(s.toArray());
		
		JComboBox[] jComboBox = {comboBox, comboBox_1, comboBox_2, comboBox_3};
		
		this.humanPlayers = 0;
		this.computerPlayers = 0;
		
		JButton btnStartGame = new JButton("Start Game!");
		btnStartGame.setForeground(Color.RED);
		btnStartGame.addActionListener(new StartGameBottonListener(jComboBox, this));
		
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

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(148)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(148)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(161, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(66)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(95))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStartGame, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(215)
					.addComponent(label_1)
					.addContainerGap(226, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(215)
					.addComponent(label)
					.addContainerGap(219, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addGap(41)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStartGame)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
