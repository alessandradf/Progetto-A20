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
import javax.swing.DefaultComboBoxModel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(20, 17, 163, 27);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Computer Player", "Human Player"}));
		panel_1.add(comboBox);
		
		JLabel label = new JLabel("1");
		label.setBounds(20, 0, 163, 16);
		label.setForeground(Color.YELLOW);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);
		
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(0, 42, 163, 26);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Computer Player", "Human Player"}));
		panel_3.add(comboBox_1);
		
		JLabel label_1 = new JLabel("2");
		label_1.setBounds(0, 26, 163, 16);
		label_1.setForeground(Color.YELLOW);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label_1);
		
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
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(44, 72, 119, 27);
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Punteggio", "20", "25", "30"}));
		panel_4.add(comboBox_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 128, 0));
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(27, 44, 163, 26);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Computer Player", "Human Player"}));
		panel_5.add(comboBox_3);
		
		JLabel label_3 = new JLabel("4");
		label_3.setBounds(27, 27, 163, 16);
		label_3.setForeground(Color.YELLOW);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(label_3);
		
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
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(20, 71, 163, 26);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Computer Player", "Human Player"}));
		panel_7.add(comboBox_2);
		
		JLabel label_2 = new JLabel("3");
		label_2.setBounds(20, 52, 163, 16);
		label_2.setForeground(Color.YELLOW);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(label_2);
		
		txtInserireNome_1 = new JTextField();
		txtInserireNome_1.setEnabled(false);
		txtInserireNome_1.setBounds(20, 96, 163, 26);
		panel_7.add(txtInserireNome_1);
		txtInserireNome_1.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 128, 0));
		contentPane.add(panel_8);
		
		JComboBox[] jComboBox = {comboBox, comboBox_1, comboBox_2, comboBox_3};
		
		this.humanPlayers = 0;
		this.computerPlayers = 0;
		
		JTextField[] textFieldArray = {txtInserireNome, txtInserireNome_2, txtInserireNome_1, txtInserireNome_3};
		
		for (JTextField jTextField : textFieldArray) {
			jTextField.setEnabled(false);
		}
		
		int i = 0;
		
		for (JComboBox jComboBox2 : jComboBox) {
			jComboBox2.addActionListener(new ItemChangeListener(textFieldArray[i], jComboBox2));
			i++;
		}
		
		btnNewButton.addActionListener(new StartGameBottonListener(jComboBox, textFieldArray, this));
		
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
