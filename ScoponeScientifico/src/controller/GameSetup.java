package controller;

import java.util.ArrayList;

import graphicInterfaceController.GUIController;
import model.Game;
import model.Player;
import model.Team;
import utility.CircularArrayList;

public class GameSetup {

	private static GameSetup defaultGameSetup = null;

	private Game game;
	private GameController gameController;
	private GUIController guiController;
	private CircularArrayList<AbstractPlayerHandler> players;
	private ArrayList<HumanPlayerHandler> humanPlayers;
	private int humanPlayerNumber;

	private GameSetup(int human_players_number) {
		this.humanPlayerNumber = human_players_number;
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		this.humanPlayers = new ArrayList<HumanPlayerHandler>();
		game = Game.getDefaultGame();
		gameController = new GameController(players);
		guiController = GUIController.getDefaultGUIController();
		createPlayers(this.humanPlayerNumber);
		guiController.init(humanPlayers.toArray(new HumanPlayerHandler[human_players_number]));

		game.getDefaultTable().addObserver(guiController);

		for (int i = 0; i < human_players_number; i++) {
			//nota: bisogna mettere human_players_number e non 4 altrimenti c'è un'eccezione se i giocatori non sono veramente 4
			game.getTeams().get(0).addTeamObserver(guiController.getTeam1Panels().get(i));
			game.getTeams().get(1).addTeamObserver(guiController.getTeam2Panels().get(i));
		}

		gameController.init();
	}

	public static GameSetup getDefaultGameSetup(int human_players_number) {
		if (defaultGameSetup == null)
			defaultGameSetup = new GameSetup(human_players_number);
		return defaultGameSetup;
	}

	private void createPlayers(int human_players_number) {
		// istanzia i giocatori umani e li associa ordinatamente a quelli del game
		for (int i = 0; i < human_players_number; i++) {
			humanPlayers.add(new HumanPlayerHandler(game.getPlayers().get(i), this.gameController));
			players.add(humanPlayers.get(i));
		}

		for (int i = human_players_number; i < 4; i++) {
			players.add(new ComputerPlayerHandler(game.getPlayers().get(i), this.gameController));
		}
	}

	public static void main(String[] args) {
		GameSetup g = new GameSetup(4);
	}
}
