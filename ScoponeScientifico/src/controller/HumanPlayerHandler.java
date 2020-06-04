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
		Card playedCard = CardConverter.toModelCard(cardLabel);
		setPlayedCard(playedCard);
		getController().hasPlayed(this);
	}
	
	@Override
	public boolean unlockPlayer() {
		playerPanel.unlockPlayer();
		return true;
	}
	
	
	@Override
		public boolean lockPlayer() {
			this.playerPanel.lockPlayer();
			return true;
		}
	
	
	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}

	

}
