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
		guiController = GUIController.getDefaultGUIController();
		createPlayers();
		//guiController.init(humanPlayers);	//lista di giocatori umani
	}
	/**
	 * Crea i PlayerHandler del gioco
	 */
	// per ora uno solo
	private void createPlayers() {
		players.add(new HumanPlayerHandler(game.getPlayers().get(0)));
	}
	
	private void nextPlayer() {
		/*
		 *  next_player = players.getNext();
		 *  next_player.unlock();
		 */
	}
	
	public void hasPlayed(AbstractPlayerHandler p) {
		/*
		 * Converter.ConvertIntoDomainCard(p.getPlayedCard());
		 */
		//game.playRound(Card c, Player p);
	}
}
