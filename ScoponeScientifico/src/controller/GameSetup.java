package controller;

import java.util.ArrayList;

import graphicInterfaceController.GUIController;
import model.Game;
import utility.CircularArrayList;

public class GameSetup {
	
	private static GameSetup defaultGameSetup = null;
	
	private Game game;
	private GameController gameController;
	private GUIController guiController;
	private CircularArrayList<AbstractPlayerHandler> players;
	private ArrayList<HumanPlayerHandler> humanPlayers;
	private int human_players_number;
	
	private GameSetup(int human_players_number) {
		this.human_players_number = human_players_number;
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		this.humanPlayers = new ArrayList<HumanPlayerHandler>();
		game = Game.getDefaultGame();
		gameController = new GameController(players);
		guiController = GUIController.getDefaultGUIController();
		createPlayers(this.human_players_number);
		guiController.init(humanPlayers.toArray(new HumanPlayerHandler[human_players_number]));
	}
	
	private GameSetup getDefaultGameSetup(int human_players_number) {
		if(defaultGameSetup == null)
			defaultGameSetup = new GameSetup(human_players_number);
		return defaultGameSetup;
	}
	
	private void createPlayers(int human_players_number) {
		//istanzia i giocatori umani e li associa ordinatamente a quelli del game
		for(int i = 0; i < human_players_number; i++) {
			humanPlayers.add(new HumanPlayerHandler(game.getPlayers().get(i), this.gameController));
			players.add(humanPlayers.get(i));
		}
	}
	
	public static void main(String[] args) {
		GameSetup g = new GameSetup(4);
	}
}
