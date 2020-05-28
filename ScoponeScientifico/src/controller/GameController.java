package controller;

import model.Game;

public class GameController {
	private Game game;
	private CircularArrayList<AbstractPlayerHandler> players;

	public GameController() {
		this.game = Game.getDefaultGame();
		this.players = new CircularArrayList<AbstractPlayerHandler>();
	}

	private void nextPlayer() {
		// players.getNext();
	}
}
