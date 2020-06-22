package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import model.Card;
import model.SeedType;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JComboBox;

public class OptionsPopUp extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<ArrayList<ArrayList<Card>>> comboBox;
	private JButton okButton;
	
/*	public static void main(String[] args) {
		ArrayList<Card> ar1 = new ArrayList<Card>();
		ar1.add(new Card(3, SeedType.CUORI));
		ar1.add(new Card(4, SeedType.DENARI));
		ArrayList<Card> ar2 = new ArrayList<Card>();
		ar2.add(new Card(5, SeedType.FIORI));
		ar2.add(new Card(7, SeedType.PICCHE));
		
		ArrayList<ArrayList<Card>> prova = new ArrayList<ArrayList<Card>>();
		prova.add(ar1);
		prova.add(ar2);
		
		OptionsPopUp optionsPopUp = new OptionsPopUp(prova);
		
	}*/

	public OptionsPopUp(ArrayList<ArrayList<Card>> optionCard) {
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(800, 100, 500, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1));
		
		contentPanel.setBackground(new Color(255, 255, 255));
		
		this.setSize(new Dimension(400, 200));
		this.setResizable(false);
		this.setTitle("Choose your Cards");

		comboBox = new JComboBox(optionCard.toArray());
		comboBox.setBackground(new Color(255, 255, 255));
		
	//	comboBox.setBackground(new Color(255, 26, 102));
		contentPanel.add(comboBox);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(2, 1));
		buttonPane.setBackground(new Color(255, 255, 255));
	
		JLabel gifLabel = new JLabel();
		gifLabel.setSize(100, 100);
		ImageIcon gif = new ImageIcon("Resources/Gif/ThinkingGif.gif");
		gifLabel.setIcon(gif);
		//gifLabel.setIcon(new ImageIcon(ImageIO.read(new File("Resources/Gif/ThinkingGif.gif"))));
		
		buttonPane.add(gifLabel);
	
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	//	buttonPane.setBackground(new Color(255, 102, 153));
		okButton = new JButton("OK");
		okButton.setBackground(new Color(255, 51, 153));
		buttonPane.add(okButton);

		
		getRootPane().setDefaultButton(okButton);
		JFrame myFrame = this;
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				myFrame.toFront();
				myFrame.repaint();
			}
		});
		this.setVisible(true);
	}

	public JComboBox<ArrayList<ArrayList<Card>>> getComboBox() {
		return comboBox;
	}

	public JButton getOkButton() {
		return okButton;
	}

}
