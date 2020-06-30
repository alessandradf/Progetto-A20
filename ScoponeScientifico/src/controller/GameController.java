package controller;

import java.util.ArrayList;


import model.Card;
import model.Game;
import model.Player;
import model.Team;
import utility.CircularArrayList;

//TODO: non è ancora implementata la logica di fine mano e di fine partita
public class GameController {

	private Game game;
	private CircularArrayList<AbstractPlayerHandler> players;
	private History history;
	private InterfaceTurnFinalizer turnFinalizer;
	private int turn;
	
	

	/**
	 * Crea una partita, dati i giocatori
	 * @param players gli AbstractPlayerHandler che giocano la partita
	 */
	public GameController(CircularArrayList<AbstractPlayerHandler> players, Game g) {
		this.game = g;
		this.players = players;
		this.turn = 0;
		this.history = new History();
	}

	/**
	 * Consente di settare il punteggio massimo
	 * @param players gli AbstractPlayerHandler che giocano la partita
	 * @param maxScore il punteggio massimo a cui si arriva
	 */
	public GameController(CircularArrayList<AbstractPlayerHandler> players, int maxScore, Game g) {
		this(players, g);
		game.setMaxScore(maxScore);
	}
	
	public void init() {
		ArrayList<Team> teams = game.getTeams();
		for (AbstractPlayerHandler p : players) {
			p.lockPlayer();
		}
		/*
		 * Questo pezzo associa il team al giocatore e viceversa. Fatto così fa schifo,
		 * ma mi serve per evitare dei NullPointerException. Considerare di spostarlo
		 * dentro al Game nella versione finale Ad essere onesti non è nemmeno
		 * necessario che i team conoscano i giocatori. -Andrea
		 */
		teams.get(0).addPlayer(players.get(0).getPlayer());
		players.get(0).getPlayer().setTeam(teams.get(0));

		teams.get(0).addPlayer(players.get(2).getPlayer());
		players.get(2).getPlayer().setTeam(teams.get(0));

		teams.get(1).addPlayer(players.get(1).getPlayer());
		players.get(1).getPlayer().setTeam(teams.get(1));

		teams.get(1).addPlayer(players.get(3).getPlayer());
		players.get(3).getPlayer().setTeam(teams.get(1));

		initHistoryObserver();
	}
	
	public void start() {
		players.get(0).unlockPlayer();
	}
	
	/*
	 * Aggiunge la History come observer a Table, Team e ai Player
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

	
	
	private void nextPlayer() {
		AbstractPlayerHandler next_player;
		next_player = players.unlockNext(turn);
		System.out.println(next_player);
		next_player.unlockPlayer();
		
	}

	/*
	 * Deve controllare che cosa succede se viene giocata la carta che viene
	 * passata, e restituire le eventuali prese, null se non viene preso nulla
	 */
	public ArrayList<ArrayList<Card>> fetchCard(Card c) {
		turn++;
		System.out.println(turn + "turno normale");
		return game.fetchCard(c);
	}

	/**
	 * Pone fine al turno del giocatore corrente, togliendo al tavolo le carte scelte
	 */
	public void endTurn(AbstractPlayerHandler p, ArrayList<Card> choiceMade) {
		game.finalizeTurn(p.getPlayer(), choiceMade);
		history.writeOnOutput();
		p.lockPlayer();
		if(!isLastTurn())
			nextPlayer();
	}

	/*
	 * finalizza il turno MA senza modificare nulla del dominio, utile quando non si
	 * prendono delle carte dal tavolo
	 */
	// è protetto perchè va chiamato solo dagli AbstractPlayerHandler
	protected void endTurn(AbstractPlayerHandler player) {
		game.finalizeTurn(player.getPlayer(), player.getPlayedCard());
		history.writeOnOutput();
		player.lockPlayer();
		if(!isLastTurn())
			nextPlayer();
	}

	private boolean isLastTurn() {
		if(turn == 40) {
			turn = 0;
			System.out.println(turn + "turno finale");
			finalizeHand();
			return true;
		}
		return false;
	}
	
	private void finalizeHand() {
		boolean isFinished = game.finalizeHand();
		if(isFinished) {
			Team winner;
			if(game.getTeams().get(0).getScore() > game.getTeams().get(1).getScore()) {
				winner = game.getTeams().get(0);
			}
			else {
				winner = game.getTeams().get(1);
			}
			turnFinalizer.gameFinished(winner);
		}
	}
	
	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
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
