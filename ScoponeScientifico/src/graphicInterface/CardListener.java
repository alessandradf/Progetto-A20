package graphicInterface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import CardTest.CardLabel;
import controller.HumanPlayerHandler;
import graphicInterfaceController.GUIController;

public class CardListener implements MouseListener{
	
	private CardLabel cardLabel;
	private static Border BORDER = BorderFactory.createLineBorder(Color.BLUE, 3, true);
	private HumanPlayerHandler humanPlayer;
	private PlayerPanel playerPanel;
	private GUIController g;
	
	public CardListener(CardLabel l, HumanPlayerHandler humanPlayer, PlayerPanel playerPanel, GUIController g) {
		cardLabel = l;
		this.humanPlayer = humanPlayer;
		this.playerPanel = playerPanel;
		this.cardLabel.setEnabled(true);
		this.g = g;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(cardLabel.isEnable() == true) {
			cardLabel.setVisible(false);
			playerPanel.remove(cardLabel);
			humanPlayer.cardPlayed(cardLabel);
			g.updateHistory(humanPlayer, humanPlayer.getPlayedCard());
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
