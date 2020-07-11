package graphicInterface;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import utility.OutputInterface;

/**
 * Frame on which is allowed to write through the OutputInterface. 
 * It's used from the GUIController to set the history.
 */
public class HistoryFrame extends JFrame implements OutputInterface {
	private JLabel historyLabel;
	private JScrollPane scrollPane;

	/**
	 * constructor
	 */
	public HistoryFrame() {
		// TODO Auto-generated constructor stub

		setSize(new Dimension(400, 200));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 40;
		int y = (dim.height - h) / 5;
		this.setLocation(x, y);
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

		String[] parts = update.split("\n");
		String entry = "<html><body><ul><li>";

		for (String s : parts) {
			entry += s + "<br>";
		}

		entry += "</li></ul>";

		String text = historyLabel.getText() + entry;

		historyLabel.setText(text);
		historyLabel.repaint();
		historyLabel.validate();
		this.repaint();
		this.validate();

		JScrollBar vert = this.scrollPane.getVerticalScrollBar();
		vert.setValue(vert.getMaximum());
	}

}
