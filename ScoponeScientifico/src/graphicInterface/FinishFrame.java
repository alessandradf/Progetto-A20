package graphicInterface;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import model.Team;

/**
 * Extends {@link JFrame} and appears at the end of the game. It announces the
 * winner team and allows the user to choose if he wants to play again.
 */
@SuppressWarnings("serial")
public class FinishFrame extends JFrame {
	private JPanel contentPane;
	private JLabel winnerLabel;
	private JRadioButton yes;
	private JRadioButton no;
	private JButton ok;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinishFrame frame = new FinishFrame(new Team("nameTestTeam"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the frame with a Label on which is wrote the winner team, two
	 * JRadioButton that allow to choose to play again or not and the "ok button"
	 * JButton used to finalize the choice
	 * 
	 * @param winnerTeam
	 * @see Team
	 * @see JRadioButton
	 * @see JButton
	 */
	public FinishFrame(Team winnerTeam) {
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(2, 1));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		winnerLabel = new JLabel("Gioco Terminato! complimenti al team vincitore: " + winnerTeam.getTeamName()
				+ "! Punteggio: " + winnerTeam.getScore());
		panel.add(winnerLabel);
		contentPane.add(panel);

		JPanel choosePanel = new JPanel();
		choosePanel.setLayout(new GridLayout(1, 2));
		choosePanel.add(new JLabel("Vuoi giocare ancora?"));
		contentPane.add(choosePanel);

		JPanel yesOrnoPanel = new JPanel();
		yesOrnoPanel.setLayout(new GridLayout(3, 1));
		yes = new JRadioButton("yes");
		yes.setSelected(true);
		no = new JRadioButton("no");
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (no.isSelected()) {
					no.setSelected(false);
				} else if (!yes.isSelected()) {
					yes.setSelected(true);
				}
			}
		});

		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (yes.isSelected()) {
					yes.setSelected(false);
				} else if (!no.isSelected()) {
					no.setSelected(true);
				}

			}

		});

		ok = new JButton("ok");
		yesOrnoPanel.add(yes);
		yesOrnoPanel.add(no);
		yesOrnoPanel.add(ok);
		choosePanel.add(yesOrnoPanel);
		setVisible(true);

	}

	/**
	 * @return ok Button
	 * @see JButton
	 */
	public JButton getOk() {
		return ok;
	}

	/**
	 * @return true if the user want's to play again, false otherwise
	 */
	public boolean getChoose() {
		if (yes.isSelected()) {
			return true;
		}
		return false;
	}

}
