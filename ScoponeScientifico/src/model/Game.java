package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exception.CardNotFoundException;

/**
 * Rappresenta una mano di scopone scientifico, costruendo mazzo, giocatori e team e permettendo di giocare le carte e consegnarle
 * al giocatore che fa le prese.
 * Più precisamente...
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

	private Game() {
		createDeck();
		players = createPlayers();
		shuffleDeck();
		teams = createTeams();
		populateTeams();
		table = new Table();	
	}

	public static Game getDefaultGame() {
		if (defaultGame == null) {
			defaultGame = new Game();
		}
		return defaultGame;
	}

	/**
	 * Permette di far giocare al giocatore p la carta c, mettendola sul tavolo
	 * @param p giocatore che gioca
	 * @param c	carta da giocare
	 */
	public void playRound(Player p, Card c) {
		System.out.println(p.getTeam());
		try {
			p.removeCardFromHand(c);
		} catch (CardNotFoundException e) {
			// TODO: handle exception
			// in realtà non dovrebbe mai succedere
			e.printStackTrace();
		}
		
		//
		//Bozza del vero playRound()
		 
		List<Card> result = table.putCardOnTable(c);
		System.out.println(result);
		if(result != null) {
			p.getTeam().addCards((ArrayList<Card>)result);
		}

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
		 * Istanzia 4 giocatori, tutti uguali. La distinzione fra giocatore vero e finto viene fatta a livello più alto
		 */
		for (int i = 0; i < PLAYER_NUMBER; i++) {
			players.add(new Player("Player" + i));
		}
		return players;
	}

	/**
	 * Crea due istanze di team
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
		for(int i = 0; i < TEAM_NUMBER; i++) {
			//mette i primi due giocatori nel team 1 e gli altri 2 nel team 2
			teams.get(i).addPlayer(players.get(j));
			j++;	//dovrebbe valere 4 alla fine del ciclo
		}
	}
	
	private void shuffleDeck() {
		ArrayList<Card> deckArrayList = new ArrayList<Card>();
		for(Card c : deck) {
			deckArrayList.add(c);
		}
		Collections.shuffle(deckArrayList);
		int j = 0;
		for(Card c : deckArrayList) {
			if(players.get(j).getHand().size() == 10) {
				j++;
				players.get(j).getHand().add(c);
			}
			else {
				players.get(j).getHand().add(c);
			}
		}
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


}
