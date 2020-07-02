package controller;

import java.awt.EventQueue;
import java.util.ArrayList;

import graphicInterface.StartFrame;
import graphicInterfaceController.GUIController;
import model.Game;
import model.Player;
import model.Team;
import utility.CircularArrayList;
import utility.TableObserver;
import utility.TeamObserver;

public class GameStartSetup {

	private Game game;
	private GameController gameController;
	private CircularArrayList<AbstractPlayerHandler> players;
	private ArrayList<HumanPlayerHandler> humanPlayers;
	private int humanPlayerNumber;
	private InterfaceController controller;

	public GameStartSetup(String[] config, int human_players_number, InterfaceController controller) {
		this.humanPlayerNumber = human_players_number;
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		this.humanPlayers = new ArrayList<HumanPlayerHandler>();
		this.controller = controller;
		game = new Game();
		init(config);
		// guiController.init(humanPlayers.toArray(new
		// HumanPlayerHandler[human_players_number]), gameController);

		/*
		 * game.getDefaultTable().addObserver(guiController);
		 * 
		 * for (int i = 0; i < human_players_number; i++) { //nota: bisogna mettere
		 * human_players_number e non 4 altrimenti c'è un'eccezione se i giocatori non
		 * sono veramente 4
		 * game.getTeams().get(0).addTeamObserver(guiController.getTeam1Panels().get(i))
		 * ;
		 * game.getTeams().get(1).addTeamObserver(guiController.getTeam2Panels().get(i))
		 * ; }
		 */

		// gameController.init();
	}
	public void init(String[] config) {
		try {		
		gameController = new GameController(players, game);
		createPlayers(config);
		controller.init(getHumanPlayers(), gameController);
		gameController.init();
		addTableObservers(controller);
		//addTeamObservers(controller.getTeam1Observer(), controller.getTeam2Observer());
		gameController.start();
		} catch (Exception e) {
			controller.startGame();
		}
		
	}
	public void addTableObservers(TableObserver tableObserver) {
		game.getDefaultTable().addObserver(tableObserver);

	}
	
	//non serve pi�
	public void addTeamObservers(ArrayList<TeamObserver> team1Observers, ArrayList<TeamObserver> team2Observers) {
		for (int i = 0; i < humanPlayerNumber; i++) {
			// nota: bisogna mettere human_players_number e non 4 altrimenti c'è
			// un'eccezione se i giocatori non sono veramente 4
			game.getTeams().get(0).addTeamObserver(team1Observers.get(i));
			game.getTeams().get(1).addTeamObserver(team2Observers.get(i));
		}
	}

	private void createPlayers(String[] config) {
		if (allComputers(config))
			throw new IllegalArgumentException("Deve esserci almeno un giocatore umano");
		
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
	
	private boolean allComputers(String config[]) {
		for (int i = 0; i < config.length; i++) {
			if (config[i].equals("Human"))
				return false;
		}
		return true;
	}
	public GameController getGameController() {
		return gameController;
	}

	public ArrayList<HumanPlayerHandler> getHumanPlayers() {
		return humanPlayers;
	}

	
}
