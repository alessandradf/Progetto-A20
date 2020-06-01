package controller;

import java.util.ArrayList;

import graphicInterfaceController.GUIController;
import model.Card;
import model.Game;

public class GameController {

	private static GameController defaultGameController = null;
	
	private Game game;
	private CircularArrayList<AbstractPlayerHandler> players;
	private ArrayList<HumanPlayerHandler> humanPlayers;
	private HumanPlayerHandler[] humanPlayers2 = new HumanPlayerHandler[1];

	private GUIController guiController;
	
	public static GameController getDefaultGameController() {
		if(defaultGameController == null) {
			defaultGameController =  new GameController();
		}
		return defaultGameController;
	}
	
	private GameController() {
		this.game = Game.getDefaultGame();
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		this.humanPlayers = new ArrayList<HumanPlayerHandler>();
		guiController = GUIController.getDefaultGUIController();
		createPlayers();
		guiController.init((humanPlayers2)); //lista di giocatori umani
	}

	/**
	 * Crea i PlayerHandler del gioco
	 */
	// per ora uno solo
	private void createPlayers() {
		players.add(new HumanPlayerHandler(game.getPlayers().get(0), this));
		humanPlayers2[0] = new HumanPlayerHandler(game.getPlayers().get(0), this); // orribile, va tolto appena si riesce -Andrea
		System.out.println(humanPlayers2[0].getPlayer().getHand());
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
	
	public static void main(String args[]) {
		GameController g = getDefaultGameController();
	}
}
