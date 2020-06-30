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
					FinishFrame frame = new FinishFrame("Team 1");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinishFrame(String winnerTeam) {
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(400, 200);
	//	setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(2, 1));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		winnerLabel = new JLabel("Gioco Terminato! complimenti al team vincitore: " + winnerTeam + "!");
		panel.add(winnerLabel);
		contentPane.add(panel);

		JPanel choosePanel = new JPanel();
		choosePanel.setLayout(new GridLayout(1, 2));
		choosePanel.add(new JLabel("Vuoi giocare ancora?"));
		contentPane.add(choosePanel);

		JPanel yesOrnoPanel = new JPanel();
		yesOrnoPanel.setLayout(new GridLayout(3, 1));
		yes = new JRadioButton("sì");
		yes.setSelected(true);
		no = new JRadioButton("no");
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (no.isSelected()) {
					no.setSelected(false);
				}

			}
		});

		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (yes.isSelected()) {
					yes.setSelected(false);
				}

			}

		});
		
		ok = new JButton("ok");
		yesOrnoPanel.add(yes);
		yesOrnoPanel.add(no);
		yesOrnoPanel.add(ok);
		choosePanel.add(yesOrnoPanel);
		
		
	}

	public JButton getOk() {
		return ok;
	}
	public boolean getChoose() {
		if(yes.isSelected()) {
			return true;
		}
		return false;
	}

}


