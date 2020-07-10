package graphicInterface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import controller.HumanPlayerHandler;
import exception.CardNotFoundException;
import graphicInterfaceController.GUIController;
import utility.CardConverter;

public class CardListener implements MouseListener{
	
	private CardLabel cardLabel;
	private static Border BORDER = BorderFactory.createLineBorder(Color.BLUE, 3, true);
	private HumanPlayerHandler humanPlayer;
	private PlayerPanel playerPanel;
	
	public CardListener(CardLabel l, HumanPlayerHandler humanPlayer, PlayerPanel playerPanel) {
		cardLabel = l;
		this.humanPlayer = humanPlayer;
		this.playerPanel = playerPanel;
		this.cardLabel.setEnabled(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(cardLabel.isEnable() == true) {
			cardLabel.setVisible(false);
			playerPanel.remove(cardLabel);
			try {
			humanPlayer.cardPlayed(CardConverter.toModelCard(cardLabel));
			}
			catch(CardNotFoundException c) {
				System.out.println("Carta non trovata!");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(cardLabel.isEnable() == true) {
			cardLabel.setBorder(BORDER);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(cardLabel.isEnable() == true) {
			cardLabel.setBorder(null);
		}
	}

}
