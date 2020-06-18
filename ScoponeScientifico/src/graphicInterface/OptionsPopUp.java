package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import model.Card;

import java.awt.GridLayout;

import javax.swing.JComboBox;

public class OptionsPopUp extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<ArrayList<ArrayList<Card>>> comboBox;
	private JButton okButton;

	public OptionsPopUp(ArrayList<ArrayList<Card>> optionCard) {
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1));
		this.setSize(new Dimension(200, 200));
		this.setResizable(false);
		this.setTitle("Choose your Cards");

		comboBox = new JComboBox(optionCard.toArray());
		contentPanel.add(comboBox);

		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		okButton = new JButton("OK");

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

	}

	public JComboBox<ArrayList<ArrayList<Card>>> getComboBox() {
		return comboBox;
	}

	public JButton getOkButton() {
		return okButton;
	}

}
