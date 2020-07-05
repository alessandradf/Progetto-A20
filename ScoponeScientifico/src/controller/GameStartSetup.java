package controller;

import java.awt.EventQueue;
import java.util.ArrayList;

import exception.HumanNotFoundException;
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

	public GameStartSetup(String[] config, int human_players_number, InterfaceController controller, ArrayList<String> playersNames) {
		this.humanPlayerNumber = human_players_number;
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		this.humanPlayers = new ArrayList<HumanPlayerHandler>();
		this.controller = controller;
		game = new Game(playersNames);
		init(config);		
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
		} catch (HumanNotFoundException e) {
			controller.startGame("Inserisci almeno un giocatore umano!");
		}
		
	}
	public void addTableObservers(TableObserver tableObserver) {
		game.getDefaultTable().addObserver(tableObserver);

	}
	
	//non serve più
	public void addTeamObservers(ArrayList<TeamObserver> team1Observers, ArrayList<TeamObserver> team2Observers) {
		for (int i = 0; i < humanPlayerNumber; i++) {
			// nota: bisogna mettere human_players_number e non 4 altrimenti c'Ã¨
			// un'eccezione se i giocatori non sono veramente 4
			game.getTeams().get(0).addTeamObserver(team1Observers.get(i));
			game.getTeams().get(1).addTeamObserver(team2Observers.get(i));
		}
	}

	private void createPlayers(String[] config) {
		if (allComputers(config))
			throw new HumanNotFoundException();
		
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
