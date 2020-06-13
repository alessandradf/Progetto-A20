package graphicInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import javax.swing.JComboBox;

public class OptionsPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	
	//private ArrayList<String> c = new ArrayList<String>();
	
	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		String b = "bella";
		String b1 = "ciao";
		ArrayList<String> c = new ArrayList<String>();
		c.add(b);
		c.add(b1);
		try {
			OptionsPopUp dialog = new OptionsPopUp(c);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Create the dialog.
	 */
	public OptionsPopUp(ArrayList<String> s) {
		setBounds(100, 100, 500, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1));
		{
			JComboBox comboBox = new JComboBox(s.toArray());
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	//metodo che restituisce la stringa selezionata
	public void getSelectedString() {
		
	}

}
