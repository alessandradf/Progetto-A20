package graphicInterface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import controller.HumanPlayerHandler;

public class CardListener implements MouseListener{
	
	private JLabel l;
	private static Border BORDER = BorderFactory.createLineBorder(Color.BLUE, 3, true);
	private HumanPlayerHandler humanPlayer;
	
	public CardListener(JLabel l, HumanPlayerHandler humanPlayer) {
		this.l = l;
		this.humanPlayer = humanPlayer;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		l.setVisible(false);
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
		l.setBorder(BORDER);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		l.setBorder(null);
	}

}
