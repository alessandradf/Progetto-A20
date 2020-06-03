package controller;

import java.util.ArrayList;

import graphicInterfaceController.GUIController;
import model.Card;
import model.Game;
import utility.CircularArrayList;

public class GameController {

	private static GameController defaultGameController = null;
	
	private Game game;
	private CircularArrayList<AbstractPlayerHandler> players;
	private HumanPlayerHandler[] humanPlayers2 = new HumanPlayerHandler[1];

	private GUIController guiController;
	
	/*
	public static GameController getDefaultGameController() {
		if(defaultGameController == null) {
			defaultGameController =  new GameController();
		}
		return defaultGameController;
	}
	*/
	
	public GameController(CircularArrayList<AbstractPlayerHandler> players) {
		this.game = Game.getDefaultGame();
		this.players = players;
	}

	private void nextPlayer() {
		AbstractPlayerHandler next_player;
		next_player = players.getNext();
		next_player.unlockPlayer();

	}

	public void hasPlayed(AbstractPlayerHandler p) {
		Card card = p.getPlayedCard();
		game.playRound(p.getPlayer(), card);
		// o comunque qualcosa del genere //
		nextPlayer();
	}

}
