package graphicInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import CardTest.CardLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
	
	ArrayList<CardLabel> playerHand;
	TotalFrame parentFrame; //frame in cui è contenuto il panel

	/**
	 * Create the panel.
	 */
	public PlayerPanel(ArrayList<CardLabel> playerHand) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//Dimension maximumSize = new Dimension(25, 175);
		Dimension minimumSize = new Dimension(25, 175);
		this.setMaximumSize(minimumSize);
		this.setMinimumSize(minimumSize); //fa si che il pannello non si ridimensioni
		this.setPreferredSize(minimumSize);
		this.playerHand = playerHand;
		for (CardLabel cardLabel : playerHand) {
			add(cardLabel);
		}
	}
	public void setTotalFrame(TotalFrame totalFrame) {
		parentFrame = totalFrame;
	}
	
	//metodo lock, blocca il pannello una volta passato il turno,
	//impedendo al giocatore di interagirvi
	public void lockPlayer() {
		for (CardLabel cardLabel : playerHand) {
			cardLabel.setEnabled(false);
			parentFrame.setVisible(false);
			
		}
	}
	
	//metodo unlock, sblocca il pannello de giocatore non appena
	//Ã¨ il suo turno
	public void unlockPlayer(){
		for (CardLabel cardLabel : playerHand) {
			cardLabel.setEnabled(true);
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			topFrame.toFront();
			parentFrame.setVisible(true);
		}
	}

	public ArrayList<CardLabel> getPlayerHand() {
		return playerHand;
	}

}
