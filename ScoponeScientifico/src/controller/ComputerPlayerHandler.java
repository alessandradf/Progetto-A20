package controller;

import model.Player;

public class ComputerPlayerHandler extends AbstractPlayerHandler {

	public ComputerPlayerHandler(Player player) {
		super(player);
		
	}

	@Override
	public boolean unlockPlayer() {
		
		return false;
	}
	
	public void pickACard() {
		
	}

}
