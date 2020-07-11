package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * This listener makes the StartFrame change depending on the selected item in the comboBox
 */
public class ItemChangeListener implements ActionListener{
	
	private JTextField textField;
	JComboBox jComboBox;
	
	public ItemChangeListener(JTextField textField, JComboBox jComboBox) {
		this.textField = textField;
		this.jComboBox = jComboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(jComboBox.getSelectedItem().equals("Human Player")) {
			textField.setEnabled(true);
			textField.setText("Inserire nome");
		}
		else{
			textField.setEnabled(false);
			textField.setText(null);
		}
	}

}
