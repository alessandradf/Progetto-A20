package graphicInterface;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import utility.OutputInterface;

public class HistoryFrame extends JFrame implements OutputInterface {
	private JLabel historyLabel;
	private JScrollPane scrollPane;
	private String historyText; // stringa contenente tutto il testo della cronologia

	public HistoryFrame() {
		// TODO Auto-generated constructor stub
		historyText = new String();

		setSize(new Dimension(400, 200));
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setTitle("Cronologia Eventi");
		
		historyLabel = new JLabel();
		scrollPane = new JScrollPane(historyLabel);
		add(scrollPane);

	}


	@Override
	public void writeOnOutput(String update) {
		// TODO Auto-generated method stub
		if (historyText == null) {
			historyText = update;
		} else {
			historyText += update;
		}
		historyLabel.setText("<html><br>" +historyText);
		historyLabel.repaint();
		historyLabel.validate();
		this.repaint();
		this.validate();
		
	}


}
