package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exception.CardNotFoundException;
import utility.GameProcessor;
import utility.ScoreProcessor;

/**
 * * This class represent an implementation of an entire game of scopone. It
 * creates all the entities that participate to the match (4 players and 2
 * teams) and gives methods to update the domain.
 */

public class Game {

	public static final int PLAYER_NUMBER = 4;
	public static final int TEAM_NUMBER = 2;

	private Table table;
	private Set<Card> deck;
	private ArrayList<Player> players;
	private ArrayList<Team> teams;
	private ScoreProcessor scoreProcessor;
	private int maxScore;

	private Player lastTakePlayer; // ultimo giocatore ad avere affettuato una presa. Prender� le carte sul tavolo
									// a fine mano

	private Card lastCardPlayed; // ultima carta fetchata nel game

	public Game(ArrayList<String> playersNames) {
		maxScore = 1; // punteggio di default, � settato a 1 per debug, ma si pu� settare esternamente

		createDeck();
		players = createPlayers(playersNames);
		shuffleDeck();
		teams = createTeams();
		populateTeams();
		table = new Table();
		scoreProcessor = new ScoreProcessor(teams.get(0), teams.get(1));
	}

	/**
	 * Creates a deck of 40 cards.
	 */
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

	/**
	 * Creates 4 Players. This method is private and only called in the Game
	 * constructor
	 * 
	 * @param names the player names
	 * @return the Players
	 */
	private ArrayList<Player> createPlayers(ArrayList<String> names) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < PLAYER_NUMBER; i++) {
			if (names.get(i) != null) {
				players.add(new Player(names.get(i)));
			} else {
				players.add(new Player("Player" + i));
			}
		}
		return players;
	}

	/**
	 * Creates two empty teams. This method is private and only called in the
	 * constructor.
	 * 
	 * @return the Teams.
	 */
	private ArrayList<Team> createTeams() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int i = 0; i < TEAM_NUMBER; i++) {
			teams.add(new Team("Team " + (i + 1)));
		}

		return teams;
	}

	/**
	 * Populates the teams, adding, in order, the first two Player in the first team
	 * and the other two in the second team.
	 */
	private void populateTeams() {
		int j = 0;
		for (int i = 0; i < TEAM_NUMBER; i++) {
			// mette i primi due giocatori nel team 1 e gli altri 2 nel team 2
			teams.get(i).addPlayer(players.get(j));
			j++; // dovrebbe valere 4 alla fine del ciclo
		}
	}

	/**
	 * Shuffles the deck and gives 10 cards to each player.
	 */
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

	/**
	 * Tries to put a card into the table and returns all the possible choices of
	 * Cards that a player can pick from the table with the specified Card.
	 * 
	 * @param c the Card to be fetched
	 * @return an ArrayList of choices. If no choice can be made, because the card
	 *         has to be put on the table and no cards can be picked, the Arraylist
	 *         is empty.
	 */
	public ArrayList<ArrayList<Card>> fetchCard(Card c) {
		lastCardPlayed = c;
		ArrayList<ArrayList<Card>> choices = GameProcessor.searchHandle(this.table.getCardsOnTable(), c);

		if (choices.size() == 0) {
			return choices;
		}

		return choices;
	}

	/**
	 * Finishes the turn, removing the chosenCards from the table and giving them to
	 * the Player's Team.
	 * 
	 * @param p           the player that's finishing the turn.
	 * @param chosenCards the cards to be removed from the Table and picked by the
	 *                    Player.
	 * @throws CardNotFoundException, if the selected Card isn't present on the
	 *                                Player hand
	 */
	public void finalizeTurn(Player p, ArrayList<Card> chosenCards) throws CardNotFoundException {
		if (p.getHand().size() == 0)
			return;
		p.removeCardFromHand(lastCardPlayed);

		if (chosenCards.size() != 0) {
			this.table.removeCardsFromTable(chosenCards);
			p.getTeam().addCards(chosenCards);
			lastTakePlayer = p;
			if (this.table.getCardsOnTable().size() == 0) {
				p.getTeam().scopa(lastCardPlayed);
			}
		}
	}

	/**
	 * Finishes the turn, removing the Card from the player hand and putting it on
	 * the Table.
	 * 
	 * @param p the player that's finishing the turn.
	 * @param c the card to be removed from the Player's Hand
	 * @throws CardNotFoundException, if the selected Card isn't present on the
	 *                                Player hand.
	 */
	public void finalizeTurn(Player p, Card c) throws CardNotFoundException {
		if (p.getHand().size() == 0)
			return;
		p.removeCardFromHand(lastCardPlayed);

		this.table.putCardOnTable(c);
	}

	/**
	 * Finalizes a single hand (round) of scopone, when all 40 Cards have been
	 * played.
	 * 
	 * @return true if maxScore has been reached.
	 */
	public boolean finalizeHand() {
		// le carte rimaste sul tavolo vanno date all'ultimo giocatore che ha fatto una
		// presa
		lastTakePlayer.getTeam().addCards(table.getCardsOnTable());
		table.clearTable();
		scoreProcessor.giveScore(); // calcola il punteggio

		// e controlla se è stato raggiunto il punteggio massimo
		if (getTeams().get(0).getScore() >= maxScore || getTeams().get(1).getScore() >= maxScore) {
			return true;
		}

		// altrimenti si va avanti a giocare
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

	/**
	 * @return The last player that took a card from the table
	 */
	public Player getLastTakePlayer() {
		return lastTakePlayer;
	}

}
