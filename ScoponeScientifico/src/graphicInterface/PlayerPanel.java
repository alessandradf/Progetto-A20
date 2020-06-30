package graphicInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import CardTest.CardLabel;
import model.Card;
import model.SeedType;
import utility.CardConverter;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
	
	ArrayList<CardLabel> playerHand;
	TotalFrame parentFrame; //frame in cui è contenuto il panel
	private boolean playerReady;
	private PlayerPanel thisPanel;
	private JButton readyButton;
	
/*	public static void main(String[] args) {
		ArrayList<CardLabel> playerHand = new ArrayList<CardLabel>();
		playerHand.add(CardConverter.toCardLabel(new Card(3, SeedType.CUORI)));
		playerHand.add(CardConverter.toCardLabel(new Card(3, SeedType.FIORI)));
		PlayerPanel prova = new PlayerPanel(playerHand);
		JFrame contenitore = new JFrame();
		contenitore.setSize(400, 400);
		contenitore.add(prova);
		
		contenitore.setVisible(true);
	}*/
	/**
	 * Create the panel.
	 */
	public PlayerPanel(ArrayList<CardLabel> playerHand) {
		playerReady = false;
		thisPanel = this;
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//Dimension maximumSize = new Dimension(25, 175);
		Dimension minimumSize = new Dimension(25, 175);
		this.setMaximumSize(minimumSize);
		this.setMinimumSize(minimumSize); //fa si che il pannello non si ridimensioni
		this.setPreferredSize(minimumSize);
		this.playerHand = playerHand;
		for (CardLabel cardLabel : playerHand) {
			add(cardLabel);
			cardLabel.setVisible(false);
		}
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
	public void setTotalFrame(TotalFrame totalFrame) {
		parentFrame = totalFrame;
	}
	
	//metodo lock, blocca il pannello una volta passato il turno,
	//impedendo al giocatore di interagirvi
	public void lockPlayer() {
		for (CardLabel cardLabel : playerHand) {
			readyButton.setVisible(false);
			cardLabel.setEnabled(false);
			cardLabel.setVisible(false);
			parentFrame.setVisible(false);
			
		}
	}
	
	//metodo unlock, sblocca il pannello de giocatore non appena
	//Ã¨ il suo turno
	public void unlockPlayer(){
		
		parentFrame.setVisible(true);
		readyButton.setVisible(true);
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		topFrame.toFront();
		
	}
	public void veryUnlock() {
		for (CardLabel cardLabel : playerHand) {
			cardLabel.setVisible(true);
			cardLabel.setEnabled(true);
			
			
		}
		readyButton.setVisible(false);
	}

	public ArrayList<CardLabel> getPlayerHand() {
		return playerHand;
	}
	
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
