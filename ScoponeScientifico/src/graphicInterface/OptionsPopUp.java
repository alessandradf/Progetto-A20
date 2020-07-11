package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import model.Card;

import java.awt.GridLayout;

import javax.swing.JComboBox;

/**
 * Frame that allows the user to choose the cards he wants to take when there's
 * a multiple choice
 */
public class OptionsPopUp extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<ArrayList<ArrayList<Card>>> comboBox;
	private JButton okButton;

	/**
	 * constructor
	 * 
	 * @param optionCard cards that the user can choose
	 */
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

		contentPanel.add(comboBox);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(2, 1));
		buttonPane.setBackground(new Color(255, 255, 255));

		JLabel gifLabel = new JLabel();
		gifLabel.setSize(100, 100);
		ImageIcon gif = new ImageIcon("Resources/Gif/ThinkingGif.gif");
		gifLabel.setIcon(gif);

		buttonPane.add(gifLabel);

		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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

	/**
	 * @return comboBox with the options
	 */
	public JComboBox<ArrayList<ArrayList<Card>>> getComboBox() {
		return comboBox;
	}

	/**
	 * @return ok button
	 */
	public JButton getOkButton() {
		return okButton;
	}

}
