package graphicInterface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import CardTest.CardLabel;
import controller.HumanPlayerHandler;

public class CardListener implements MouseListener{
	
	private CardLabel cardLabel;
	private static Border BORDER = BorderFactory.createLineBorder(Color.BLUE, 3, true);
	private HumanPlayerHandler humanPlayer;
	
	public CardListener(CardLabel l, HumanPlayerHandler humanPlayer) {
		cardLabel = l;
		this.humanPlayer = humanPlayer;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		cardLabel.setVisible(false);
		humanPlayer.cardPlayed(cardLabel);
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
		cardLabel.setBorder(BORDER);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		cardLabel.setBorder(null);
	}

}
