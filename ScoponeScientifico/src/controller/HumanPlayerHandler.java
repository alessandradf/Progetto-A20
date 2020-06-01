package controller;

import javax.swing.JPanel;

import CardTest.CardLabel;
import graphicInterface.PlayerPanel;
import model.Card;
import model.Player;

public class HumanPlayerHandler extends AbstractPlayerHandler {
	private PlayerPanel playerPanel;

	public HumanPlayerHandler(Player player, GameController controller) {
		super(player, controller);		
	}

	public void cardPlayed(CardLabel cardLabel) {
		Card card = CardConverter.toModelCard(cardLabel);
		//controller.playCard(this, card);
	}
	
	@Override
	public boolean unlockPlayer() {
		playerPanel.unlockPlayer();
		return true;
	}

	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}

}
