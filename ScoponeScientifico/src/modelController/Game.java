package modelController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.Card;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
import model.SeedType;
import model.Table;
import model.Team;

/**
 * Rappresenta un gioco di scopone scientifico, questa classe fa da controller.
 * 
 * @author Andrea
 *
 */
public class Game {

	public static final int PLAYER_NUMBER = 4;
	public static final int CARD_NUMBER = 40;
	public static final int CARD_PER_PLAYER = 10;
	public static final int TEAM_NUMBER = 2;
	public static final int PLAYERS_PER_TEAM = 2;

	private int humanPlayersNumber;
	private static Game defaultGame = null;
	private Table defaultTable;
	private Set<Card> deck;
	private ArrayList<Player> players;
	private ArrayList<HumanPlayer> humanPlayers;
	private ArrayList<ComputerPlayer> computerPlayers;
	private ArrayList<Team> teams;

	private Game() {
		this(1);
	}

	private Game(int n) {
		if(n <= 4) {
			humanPlayersNumber = n;
		}
		else humanPlayersNumber = 4;
		createDeck();
		humanPlayers = createHumanPlayers();
		computerPlayers = createComputerPlayers();
		players = new ArrayList<Player>();
		for(HumanPlayer p : humanPlayers)
			players.add(p);
		for(ComputerPlayer p : computerPlayers)
			players.add(p);
		teams = createTeams();
		defaultTable = createTable();
		shuffleDeck();
	}
	/**
	 * Restituisce il Game, con un solo giocatore umano
	 * @return
	 */
	public static Game getDefaultGame() {
		if (defaultGame == null) {
			defaultGame = new Game();
		}
		return defaultGame;
	}

	/**
	 * Permette di giocare un round di scopone scientifico, con un solo giocatore
	 * umano
	 */
	public void playRound(Player p, Card c) {
		p.playCard(c);
		defaultTable.putCard(c);
		//e poi ci va la logica delle prese
	}

	private void createDeck() {
		deck = new HashSet<Card>();
		SeedType seeds[] = SeedType.values();
		for (int i = 1; i <= Card.CARD_VALUES.length; i++) {
			for (SeedType type : seeds) {
				Card temp = new Card(i, type);
				deck.add(temp);
			}
		}
	}

	private ArrayList<HumanPlayer> createHumanPlayers() {
		ArrayList<HumanPlayer> players = new ArrayList<HumanPlayer>();
		/*
		 * Questo pezzo istanzia fisicamente i giocatori, per ora uno solo è un vero
		 * giocatore, gli altri sono controllati dal computer
		 */
		for (int i = 0; i < humanPlayersNumber; i++) {
			players.add(new HumanPlayer("Giocatore" + i));
		}
		
		return players;
	}

	private ArrayList<ComputerPlayer> createComputerPlayers() {
		ArrayList<ComputerPlayer> result = new ArrayList<ComputerPlayer>();
		for (int i = 0; i < PLAYER_NUMBER - humanPlayersNumber; i++) {
			result.add(new ComputerPlayer());
		}
		return result;
	}

	/**
	 * Crea due istanze di team, e lega i giocatori ai rispettivi team
	 * 
	 * @return i team di gioco
	 */

	private ArrayList<Team> createTeams() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int i = 0; i < TEAM_NUMBER; i++) {
			teams.add(new Team("Team " + i));
		}
		Iterator<Player> iterator = players.iterator();
		for (int i = 0; i < TEAM_NUMBER; i++) {
			for(int j = 0; j < PLAYERS_PER_TEAM; j++) {
				while(iterator.hasNext())
					teams.get(i).addPlayer(iterator.next());
			}
		}
		return teams;
	}


	private Table createTable() {
		return new Table();
	}

	// Mettere private questo metodo finito il testing -Andrea
	public void shuffleDeck() {
		ArrayList<Card> deckToShuffle = new ArrayList<Card>();
		Iterator<Card> iterator = deck.iterator();
		while (iterator.hasNext()) {
			deckToShuffle.add(iterator.next());
		}
		Collections.shuffle(deckToShuffle);

		Iterator<Card> iterator2 = deckToShuffle.iterator();
		for (int i = 0; i < PLAYER_NUMBER; i++) {
			for (int j = 0; j < CARD_PER_PLAYER; j++) {
				players.get(i).getHand().add(iterator2.next());
			}
		}
	}

	/**
	 * @return the humanPlayersNumber
	 */
	public int getHumanPlayersNumber() {
		return humanPlayersNumber;
	}

	/**
	 * @return the players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * @return the humanPlayers
	 */
	public ArrayList<HumanPlayer> getHumanPlayers() {
		return humanPlayers;
	}

	/**
	 * @return the computerPlayers
	 */
	public ArrayList<ComputerPlayer> getComputerPlayers() {
		return computerPlayers;
	}
	
	/**
	 * @return the defaultTable
	 */
	public Table getDefaultTable() {
		return defaultTable;
	}

	/**
	 * @return the deck
	 */
	public Set<Card> getDeck() {
		return deck;
	}

	

	/**
	 * @return the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

}
