package controller;

import graphicInterfaceController.GUIController;
import model.Game;

public class GameController {
	private Game game;
	private CircularArrayList<AbstractPlayerHandler> players;
	private GUIController guiController;

	public GameController() {
		this.game = Game.getDefaultGame();
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		//guiController = getDefaultGuiController(); 
	}

	private void nextPlayer() {
		// players.getNext();
	}
}
