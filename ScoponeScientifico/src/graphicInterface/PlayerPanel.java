package graphicInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Extends {@link JPanel}, personal panel of the player, it shows the player's
 * cards.
 * 
 * @see CardLabel
 */
@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {

	ArrayList<CardLabel> playerHand;
	TotalFrame parentFrame;
	private PlayerPanel thisPanel;
	private JButton readyButton;

	/**
	 * Creates the PlayerPanel with the "ready button" that allows the player to
	 * decide when he is ready to see his cards
	 * 
	 * @see JButton
	 */
	public PlayerPanel() {
		thisPanel = this;
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		Dimension minimumSize = new Dimension(25, 175);
		this.setMaximumSize(minimumSize);
		this.setMinimumSize(minimumSize);
		this.setPreferredSize(minimumSize);
		this.playerHand = new ArrayList<CardLabel>();
		readyButton = new JButton("Ready");
		readyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thisPanel.veryUnlock();

			}
		});
		add(readyButton);
	}

	/**
	 * Sets the TotalFrame in which is located the panel
	 * 
	 * @param totalFrame
	 * @see TotalFrame
	 */
	public void setTotalFrame(TotalFrame totalFrame) {
		parentFrame = totalFrame;
	}

	/**
	 * Locks the panel to prevent the player to play
	 */
	public void lockPlayer() {
		for (CardLabel cardLabel : playerHand) {
			readyButton.setVisible(false);
			cardLabel.setEnabled(false);
			cardLabel.setVisible(false);
			parentFrame.setVisible(false);

		}
	}

	/**
	 * Unlocks the panel to make it visible to the user only with the button "Ready"
	 */
	public void unlockPlayer() {

		parentFrame.setVisible(true);
		readyButton.setVisible(true);
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		topFrame.toFront();

	}

	/**
	 * Allows the player to play his card after the click of the button "Ready", it
	 * prevents the other players to see his cards
	 */
	public void veryUnlock() {
		for (CardLabel cardLabel : playerHand) {
			cardLabel.setVisible(true);
			cardLabel.setEnabled(true);
		}
		readyButton.setVisible(false);
	}

	/**
	 * @return player's cards (in cardLabel's form)
	 * @see CardLabel
	 */
	public ArrayList<CardLabel> getPlayerHand() {
		return playerHand;
	}

	/**
	 * Sets the cards on the panel
	 * 
	 * @param playerCards player's cards to set
	 * @see CardLabel
	 */
	public void setCards(ArrayList<CardLabel> playerCards) {
		playerHand.clear();
		playerHand = playerCards;

		for (CardLabel cardLabel : playerHand) {
			add(cardLabel);
			cardLabel.setVisible(false);
		}
		repaint();
		validate();

	}

}
