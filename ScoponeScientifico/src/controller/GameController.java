package controller;

import java.util.List;
import java.util.ArrayList;

import exception.CardNotFoundException;
import model.Card;
import model.Game;
import model.Player;
import model.Team;
import utility.CircularArrayList;
import utility.History;
import utility.TeamObserver;

/**
 * This class manages the high level players {@link AbstractPlayerHandler} and
 * the game, creating a turn system between the Players and setting a maximum
 * score, which determines when the game finishes.
 */
public class GameController {

	private Game game;
	private CircularArrayList<AbstractPlayerHandler> players;
	private History history;
	private InterfaceTurnFinalizer turnFinalizer;
	private int turn;
	private int maxScore;

	/**
	 * Setup the game, given the AbstractPlayerHandlers
	 * 
	 * @param players the AbstractPlayerHandler playing the game
	 */
	public GameController(CircularArrayList<AbstractPlayerHandler> players, Game g) {
		this.game = g;
		this.players = players;
		this.turn = 0;
		this.history = new History();
	}

	/**
	 * Constructor with the setup of the maxScore
	 * 
	 * @param players  the AbstractPlayerHandler playing the game
	 * @param maxScore the maxScore decided by the players
	 */
	public GameController(CircularArrayList<AbstractPlayerHandler> players, int maxScore, Game g) {
		this(players, g);
		this.maxScore = maxScore;
	}

	/**
	 * Initializes the Game, locking the players.
	 */
	public void init() {
		ArrayList<Team> teams = game.getTeams();
		for (AbstractPlayerHandler p : players) {
			p.lockPlayer();
		}
		/*
		 * Questo pezzo associa il team al giocatore e viceversa. È comodo farlo
		 * direttamente qui una volta sola e non più in basso
		 * 
		 */
		teams.get(0).addPlayer(players.get(0).getPlayer());
		players.get(0).getPlayer().setTeam(teams.get(0));

		teams.get(0).addPlayer(players.get(2).getPlayer());
		players.get(2).getPlayer().setTeam(teams.get(0));

		teams.get(1).addPlayer(players.get(1).getPlayer());
		players.get(1).getPlayer().setTeam(teams.get(1));

		teams.get(1).addPlayer(players.get(3).getPlayer());
		players.get(3).getPlayer().setTeam(teams.get(1));

		game.setMaxScore(maxScore);
		initHistoryObserver();
	}

	/**
	 * Unlocks the first player and starts the match
	 */
	public void start() {
		players.get(0).unlockPlayer();
	}

	/**
	 * Adds the History as observer of teams, players and table
	 */
	private void initHistoryObserver() {

		for (Team t : this.game.getTeams()) {
			t.addTeamObserver(this.history);
		}

		this.game.getDefaultTable().addObserver(this.history);

		for (Player p : this.game.getPlayers()) {
			p.addObserver(this.history);
		}
	}

	/**
	 * Adds the TeamObservers
	 * 
	 * @param team1obs observers for the Team 1
	 * @param team2obs observers for the Team 2
	 */
	public void addTeamObserver(ArrayList<TeamObserver> team1obs, ArrayList<TeamObserver> team2obs) {
		for (int i = 0; i < team1obs.size(); i++) {
			this.game.getTeams().get(0).addTeamObserver(team1obs.get(i));
		}
		for (int i = 0; i < team2obs.size(); i++) {
			this.game.getTeams().get(1).addTeamObserver(team2obs.get(i));
		}
	}

	/**
	 * Locks the current player and unlocks the next one
	 */
	private void nextPlayer() {
		AbstractPlayerHandler next_player;
		next_player = players.unlockNext(turn);
		next_player.unlockPlayer();

	}

	/**
	 * Fetches the Card into the game, returning the possible choices. If there are
	 * no choices, the Arraylist is empty
	 * 
	 * @param c the Card to be fetched
	 * @return the choices
	 */
	public ArrayList<ArrayList<Card>> fetchCard(Card c) {
		ArrayList<ArrayList<Card>> choices = game.fetchCard(c);
		turn++;
		return choices;
	}

	/**
	 * Ends the player turn, picking the Cards from the table
	 * 
	 * @param p          the player that finishes the turn
	 * @param choiceMade the Cards to be picked from the table
	 * @throws CardNotFoundException
	 */
	public void endTurn(AbstractPlayerHandler p, ArrayList<Card> choiceMade) throws CardNotFoundException {
		game.finalizeTurn(p.getPlayer(), choiceMade);
		history.writeOnOutput();
		p.lockPlayer();
		if (!isLastTurn())
			nextPlayer();
	}

	/**
	 * Finishes the turn, putting the fetched card on the table
	 * 
	 * @param player the player that finishes the turn
	 * @throws CardNotFoundException
	 */
	// è protetto perchè va chiamato solo dagli AbstractPlayerHandler
	protected void endTurn(AbstractPlayerHandler player) throws CardNotFoundException {
		game.finalizeTurn(player.getPlayer(), player.getPlayedCard());
		history.writeOnOutput();
		player.lockPlayer();
		if (!isLastTurn())
			nextPlayer();
	}

	/**
	 * Check if the current turn is the last
	 * 
	 * @return true if it's the last turn of the hand
	 */
	private boolean isLastTurn() {
		if (turn == 40) {
			turn = 0;
			finalizeHand();
			return true;
		}
		return false;
	}

	/**
	 * Finalizes the current hand, and controls if the maxScore was reached
	 */
	private void finalizeHand() {
		ArrayList<Card> lastCardsOnTable = new ArrayList<Card>();

		for (Card c : game.getDefaultTable().getCardsOnTable()) {
			lastCardsOnTable.add(c);
		}

		boolean isFinished = game.finalizeHand();
		history.finalizeHand(game.getLastTakePlayer(), lastCardsOnTable);
		history.writeOnOutput();
		if (isFinished) {
			Team winner;
			if (game.getTeams().get(0).getScore() > game.getTeams().get(1).getScore()) {
				winner = game.getTeams().get(0);
			} else {
				winner = game.getTeams().get(1);
			}
			turnFinalizer.gameFinished(winner);
		} else {
			turnFinalizer.newHand();
			this.start();
		}

	}

	/**
	 * 
	 * @return the Teams
	 */
	public List<Team> getTeams() {
		return game.getTeams();
	}

	/**
	 * @return the players
	 */
	public CircularArrayList<AbstractPlayerHandler> getPlayers() {
		return players;
	}

	/**
	 * @return the history
	 */
	public History getHistory() {
		return history;
	}

	/**
	 * @param history the history to set
	 */
	public void setHistory(History history) {
		this.history = history;
	}

	/**
	 * @return the turnFinalizer
	 */
	public InterfaceTurnFinalizer getTurnFinalizer() {
		return turnFinalizer;
	}

	/**
	 * @param turnFinalizer the turnFinalizer to set
	 */
	public void setTurnFinalizer(InterfaceTurnFinalizer turnFinalizer) {
		this.turnFinalizer = turnFinalizer;
	}

}
