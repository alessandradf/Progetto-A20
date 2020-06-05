package graphicInterface;

import javax.swing.JPanel;

import CardTest.CardLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
	
	ArrayList<CardLabel> playerHand;

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
	
	//metodo lock, blocca il pannello una volta passato il turno,
	//impedendo al giocatore di interagirvi
	public void lockPlayer() {
		setVisible(false);
		for (CardLabel cardLabel : playerHand) {
			cardLabel.setEnabled(false);
		}
	}
	
	//metodo unlock, sblocca il pannello de giocatore non appena
	//è il suo turno
	public void unlockPlayer(){
		setVisible(true);
		for (CardLabel cardLabel : playerHand) {
			cardLabel.setEnabled(true);
		}
	}

	public ArrayList<CardLabel> getPlayerHand() {
		return playerHand;
	}

}
