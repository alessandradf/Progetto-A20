package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Card;
import model.SeedType;

import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class OptionsPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private JButton okButton;

	// private ArrayList<String> c = new ArrayList<String>();

	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) { Card c1 = new Card(10,
	 * SeedType.CUORI); Card c2 = new Card(2, SeedType.FIORI); ArrayList<Card> a1 =
	 * new ArrayList<Card>(); ArrayList<Card> a2 = new ArrayList<Card>();
	 * a1.add(c1); a1.add(c2); a2.add(c1); a2.add(c2); ArrayList<ArrayList<Card>>
	 * prova = new ArrayList<ArrayList<Card>>();
	 * 
	 * prova.add(a1); prova.add(a2); try { OptionsPopUp dialog = new
	 * OptionsPopUp(prova);
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public OptionsPopUp(ArrayList<ArrayList<Card>> optionCard) {
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
		{
			okButton = new JButton("OK");
			/*
			 * okButton.addActionListener(new ActionListener() {
			 * 
			 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
			 * method stub for (Card card : (ArrayList<Card>)comboBox.getSelectedItem()) {
			 * 
			 * System.out.println(card);
			 * 
			 * } }
			 * 
			 * });
			 */
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
	}

	// metodo che restituisce la stringa selezionata
	public String getSelectedString() {
		return comboBox.getSelectedItem().toString();
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JButton getOkButton() {
		return okButton;
	}

}
