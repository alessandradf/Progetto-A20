package controller;

import java.awt.EventQueue;
import java.util.ArrayList;

import graphicInterface.StartFrame;
import graphicInterfaceController.GUIController;
import model.Game;
import model.Player;
import model.Team;
import utility.CircularArrayList;

public class GameStartSetup {

	private static GameStartSetup defaultGameSetup = null;

	private Game game;
	private GameController gameController;
	private GUIController guiController;
	private CircularArrayList<AbstractPlayerHandler> players;
	private ArrayList<HumanPlayerHandler> humanPlayers;
	private int humanPlayerNumber;

	private GameStartSetup(String[] config, int human_players_number) {
		this.humanPlayerNumber = human_players_number;
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		this.humanPlayers = new ArrayList<HumanPlayerHandler>();
		game = Game.getDefaultGame();
		gameController = new GameController(players);
		guiController = GUIController.getDefaultGUIController();
		createPlayers(config);
		guiController.init(humanPlayers.toArray(new HumanPlayerHandler[human_players_number]), gameController);

		game.getDefaultTable().addObserver(guiController);

		for (int i = 0; i < human_players_number; i++) {
			//nota: bisogna mettere human_players_number e non 4 altrimenti c'è un'eccezione se i giocatori non sono veramente 4
			game.getTeams().get(0).addTeamObserver(guiController.getTeam1Panels().get(i));
			game.getTeams().get(1).addTeamObserver(guiController.getTeam2Panels().get(i));
		}

		gameController.init();
	}

	public static GameStartSetup getDefaultGameSetup(String[] config, int human_players_number) {
		if (defaultGameSetup == null)
			defaultGameSetup = new GameStartSetup(config, human_players_number);
		return defaultGameSetup;
	}

	private void createPlayers(String[] config) {
		// istanzia i giocatori umani e li associa ordinatamente a quelli del game
		for (int i = 0; i < config.length; i++) {			
			if (config[i].equals("Human")) {
				HumanPlayerHandler newest = new HumanPlayerHandler(game.getPlayers().get(i), this.gameController);
				humanPlayers.add(newest);
				players.add(newest);

			} else {
				players.add(new ComputerPlayerHandler(game.getPlayers().get(i), this.gameController));
			}
		}
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}