package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Implements {@link ActionListener} and makes the StartFrame change depending
 * on the selected item in the JComboBox. If the string selected in the
 * JComboBox is "Human Player", it gives the possibility to set the player's
 * name.
 * 
 * @see StartFrame
 * @see JComboBox
 *
 */
public class ItemChangeListener implements ActionListener {

	private JTextField textField;
	JComboBox<String> jComboBox;

	/**
	 * Creates ItemChangeListener
	 * 
	 * @param textField to enable if the String selected is "Human Player"
	 * @param jComboBox with the options "Human Player" or "Computer Player"
	 */
	public ItemChangeListener(JTextField textField, JComboBox<String> jComboBox) {
		this.textField = textField;
		this.jComboBox = jComboBox;
	}

	/**
	 * Enables the JTextField in which the user can write the player's name, if the
	 * string selected in the ComboBox is "Human Player".
	 * @see JTextField
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (jComboBox.getSelectedItem().equals("Human Player")) {
			textField.setEnabled(true);
			textField.setText("Inserire nome");
		} else {
			textField.setEnabled(false);
			textField.setText(null);
		}
	}

}
