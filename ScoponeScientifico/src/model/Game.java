package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Rappresenta un gioco di scopone scientifico, questa classe fa da controller.
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
		table = createTable();	
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
		try {
			p.removeCardFromHand(c);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		table.putCardOnTable(c);
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
		/*
		Iterator<Card> iterator = deck.iterator();
		ArrayList<Card> deckArrayList = new ArrayList<Card>();
		while(iterator.hasNext()) {
			deckArrayList.add(iterator.next());
		}
		*/
		ArrayList<Card> deckArrayList = new ArrayList<Card>();
		for(Card c : deck) {
			deckArrayList.add(c);
		}
		Collections.shuffle(deckArrayList);
		System.out.println(deckArrayList.size());
		int j = 0;
		for(Card c : deckArrayList) {
			System.out.println(c);
			if(players.get(j).getHand().size() == 10) {
				j++;
				players.get(j).getHand().add(c);
				System.out.println("j = " + j);
			}
			else {
				players.get(j).getHand().add(c);
			}
		}
	}
	private Table createTable() {
		return new Table();
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
