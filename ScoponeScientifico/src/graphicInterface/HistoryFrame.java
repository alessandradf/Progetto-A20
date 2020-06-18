package graphicInterface;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class HistoryFrame extends JFrame {
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

		historyLabel = new JLabel();
		scrollPane = new JScrollPane(historyLabel);
		add(scrollPane);

	}

	public void setHistory(String newLine) {
		if (historyText == null) {
			historyText = newLine;
		} else {
			historyText += newLine;
		}
		historyLabel.setText(historyText);
		historyLabel.repaint();
		historyLabel.validate();
		this.repaint();
		this.validate();

	}

}
