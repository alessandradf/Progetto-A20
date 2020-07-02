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

	public GameStartSetup(String[] config, int human_players_number) {
		this.humanPlayerNumber = human_players_number;
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		this.humanPlayers = new ArrayList<HumanPlayerHandler>();
		game = new Game();
		//game.initGame();
		gameController = new GameController(players, game);
		createPlayers(config);
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

	public void addTableObservers(TableObserver tableObserver) {
		game.getDefaultTable().addObserver(tableObserver);

	}

	public void addTeamObservers(ArrayList<TeamObserver> team1Observers, ArrayList<TeamObserver> team2Observers) {
		for (int i = 0; i < humanPlayerNumber; i++) {
			// nota: bisogna mettere human_players_number e non 4 altrimenti c'è
			// un'eccezione se i giocatori non sono veramente 4
			game.getTeams().get(0).addTeamObserver(team1Observers.get(i));
			game.getTeams().get(1).addTeamObserver(team2Observers.get(i));
		}
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

	public GameController getGameController() {
		return gameController;
	}

	public ArrayList<HumanPlayerHandler> getHumanPlayers() {
		return humanPlayers;
	}

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { StartFrame frame = new StartFrame();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */
}
