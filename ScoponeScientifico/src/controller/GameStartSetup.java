package controller;

import java.util.ArrayList;
import exception.HumanNotFoundException;
import model.Game;
import utility.CircularArrayList;
import utility.TableObserver;

/**
 * Takes care of setting up the game and starting it.
 * 
 * @see InterfaceController
 * @see GameController
 */
public class GameStartSetup {

	private Game game;
	private GameController gameController;
	private CircularArrayList<AbstractPlayerHandler> players;
	private ArrayList<HumanPlayerHandler> humanPlayers;
	private InterfaceController controller;
	private ArrayList<String> playersNames;
	private String[] configuration;
	private int maxScore;

	/**
	 * Initialize and start the Game
	 * 
	 * @param configuration       configuration of players
	 * @param controller   controller of the interface
	 * @param playersNames names of the players
	 * @param maxScore     maximum game score
	 */
	public GameStartSetup(String[] configuration, InterfaceController controller, ArrayList<String> playersNames,
			int maxScore) {
		this.players = new CircularArrayList<AbstractPlayerHandler>();
		this.humanPlayers = new ArrayList<HumanPlayerHandler>();
		this.controller = controller;
		this.playersNames = playersNames;
		this.maxScore = maxScore;
		this.configuration = configuration;
		init();
	}

	/**
	 * Initialize {@link controller.GameController},
	 * {@link controller.InterfaceController} and start the game
	 * 
	 * 
	 */
	public void init() {
		try {
			game = new Game(playersNames);
			gameController = new GameController(players, maxScore, game);
			createPlayers(this.configuration);
			controller.init(getHumanPlayers(), gameController);
			gameController.init();
			addTableObservers(controller);
			gameController.start();
		} catch (HumanNotFoundException e) {
			controller.startGame("Inserisci almeno un giocatore umano!");
		}

	}

	/**
	 * Adds observer to the table
	 * 
	 * @param tableObserver observer of the table
	 */
	public void addTableObservers(TableObserver tableObserver) {
		game.getDefaultTable().addObserver(tableObserver);

	}

	/**
	 * creates players based on the configuration
	 * 
	 * @param config configuration of the players
	 */
	private void createPlayers(String[] config) {
		if (checkAllComputers(config))
			throw new HumanNotFoundException();

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

	/**
	 * Checks if there are only computers in the configuration string.
	 * 
	 * @param config configuration
	 * @return true if there are only computers, false otherwise
	 */
	private boolean checkAllComputers(String config[]) {
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
