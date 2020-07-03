package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exception.CardNotFoundException;
import utility.GameProcessor;
import utility.ScoreProcessor;

/**
 * Rappresenta una mano di scopone scientifico, costruendo mazzo, giocatori e
 * team e permettendo di giocare le carte e consegnarle al giocatore che fa le
 * prese. Più precisamente...
 * 
 * @author Andrea
 *
 */
public class Game {

	public static final int PLAYER_NUMBER = 4;
	public static final int TEAM_NUMBER = 2;

	private static Game defaultGame = null;
	private Table table;
	private Set<Card> deck;
	private ArrayList<Player> players;
	private ArrayList<Team> teams;
	private ScoreProcessor scoreProcessor;
	private int maxScore;
	
	private Player lastTakePlayer;
	private Card lastCardPlayed;
	


	public Game() {
		maxScore = 1; //punteggio di default, per ora è messo a venti per non creare confusione
		// nella versione finale bisognerà settarlo all'inizio
		createDeck();
		players = createPlayers();
		shuffleDeck();
		teams = createTeams();
		populateTeams();
		table = new Table();
		scoreProcessor = new ScoreProcessor(teams.get(0), teams.get(1));
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

	private ArrayList<Player> createPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		/*
		 * Istanzia 4 giocatori, tutti uguali. La distinzione fra giocatore vero e finto
		 * viene fatta a livello più alto
		 */
		for (int i = 0; i < PLAYER_NUMBER; i++) {
			players.add(new Player("Player" + i));
		}
		return players;
	}

	/**
	 * Crea due istanze di team
	 * 
	 * @return i team di gioco
	 */
	private ArrayList<Team> createTeams() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int i = 0; i < TEAM_NUMBER; i++) {
			teams.add(new Team("Team " + i));
		}

		return teams;
	}

	private void populateTeams() {
		int j = 0;
		for (int i = 0; i < TEAM_NUMBER; i++) {
			// mette i primi due giocatori nel team 1 e gli altri 2 nel team 2
			teams.get(i).addPlayer(players.get(j));
			j++; // dovrebbe valere 4 alla fine del ciclo
		}
	}

	private void shuffleDeck() {
		ArrayList<Card> deckArrayList = new ArrayList<Card>();
		for (Card c : deck) {
			deckArrayList.add(c);
		}
		Collections.shuffle(deckArrayList);
		int j = 0;
		for (Card c : deckArrayList) {
			if (players.get(j).getHand().size() == 10) {
				j++;

				players.get(j).getHand().add(c);

			} else {
				players.get(j).getHand().add(c);

			}
		}
	}

	/*
	 * Serve a controllare cosa succede quando viene giocata la carta.
	 * 
	 * @return ArrayList con le possibili scelte, se ce ne sono.
	 * Se non ci sono scelte, si ritorna un Arraylist vuoto 
	 */
	public ArrayList<ArrayList<Card>> fetchCard(Card c) {
		lastCardPlayed = c;
		ArrayList<ArrayList<Card>> choices = GameProcessor.searchHandle(this.table.getCardsOnTable(), c);
		
		if(choices.size() == 0) {
			return choices;
		}
		
		return choices;	
	}
	
	
	/*
	 * Conclude il turno, togliendo le carte dal tavolo e dandole a team
	 */
	public void finalizeTurn(Player p, ArrayList<Card> chosenCards) throws CardNotFoundException {
		
			p.removeCardFromHand(lastCardPlayed);
		
		if(chosenCards.size() != 0) {
			this.table.removeCardsFromTable(chosenCards);
			p.getTeam().addCards(chosenCards);
			lastTakePlayer = p;
			if(this.table.getCardsOnTable().size() == 0) {
				p.getTeam().scopa(lastCardPlayed);
			}
		}
	}
	
	public void finalizeTurn(Player p, Card c) throws CardNotFoundException{
	    
			p.removeCardFromHand(lastCardPlayed);
		
		this.table.putCardOnTable(c);
	}
	
	/**
	 * Permette di concludere la mano, calcolare i punteggi e resettare il tavolo
	 */
	public boolean finalizeHand() {
		// le carte rimaste sul tavolo vanno date all'ultimo giocatore che ha fatto una
		// presa
		lastTakePlayer.getTeam().addCards(table.getCardsOnTable());
		table.clearTable();
		scoreProcessor.giveScore(); // calcola il punteggio
		
		//e controlla se è stato raggiunto il punteggio massimo
		if(getTeams().get(0).getScore() >= maxScore || getTeams().get(1).getScore() >= maxScore) {
			return true;
		}

		//altrimenti si va avanti a giocare
		for (Team t : teams) {
			t.resetTeamCards();
		}
		shuffleDeck();
		return false;

	}

	/**
	 * @return the defaultTable
	 */
	public Table getDefaultTable() {
		return table;
	}

	/**
	 * @return the deck
	 */
	public Set<Card> getDeck() {
		return deck;
	}

	/**
	 * @return the players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * @return the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

	/**
	 * @return the maxScore
	 */
	public int getMaxScore() {
		return maxScore;
	}

	/**
	 * @param maxScore the maxScore to set
	 */
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	
}
