package graphicInterface;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import start.GUIController;
import utility.OutputInterface;

/**
 * Extends {@link JFrame} and implements {@link OutputInterface} It's used from
 * the GUIController to show the history.
 * 
 * @see GUIController
 */
@SuppressWarnings("serial")
public class HistoryFrame extends JFrame implements OutputInterface {
	private JLabel historyLabel;
	private JScrollPane scrollPane;

	/**
	 * Creates HistoryFrame
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

	/**
	 * Allows to write a new line on the HistoryFrame
	 * 
	 * @param update new line of the history
	 */
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
