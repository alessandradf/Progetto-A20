package model;

import java.util.ArrayList;

import exception.CardNotFoundException;
import utility.PlayerObserver;

/**
 * Represents a Player in a match of Scopone
 * One Player is defines by his name, his @see model.Team and his Cards, stored in his hand
 * 
 */

public class Player {
	/**
	 * Every player always starts with 10 cards
	 */
	public static final int CARDS_PER_PLAYER = 10;
	
	private Team team;
	private String playerName;
	private ArrayList<Card> hand;
	private ArrayList<PlayerObserver> obs;

	/**
	 * Creates a Player given the name
	 * @param name of the player
	 */
	public Player(String name) {
		this.playerName = name;
		hand = new ArrayList<Card>();
		this.obs = new ArrayList<PlayerObserver>();
	}
	
	/**
	 * Creates a Player with a default name
	 */
	public Player() {
		this("DefaultName");
	}
	
	/**
	 * Search the given Card from the hand and removes it
	 * @param card to be removed
	 * @throws CardNotFoundException if the Card cannot be found in the hand
	 */
	protected void removeCardFromHand(Card card) throws CardNotFoundException {
		if (hand.contains(card)) {
			
			hand.remove(card);
			updateObserver(card);
		}
		else {
			throw new CardNotFoundException("Unexpected card: " + card);
			
		}
	}
	
	/**
	 * Adds a PlayerObserver to the Player
	 * @param o the PlayerObserver to be added
	 */
	public void addObserver(PlayerObserver o) {
		this.obs.add(o);
	}
	
	/**
	 * Notify all the observers that a Card has been played
	 * @param playedCard the Card that was played by this Player
	 */
	public void updateObserver(Card playedCard) {
		for (PlayerObserver o : this.obs) {
			o.updateOnPlayedCard(this, playedCard);
		}
	}
	

	/**
	 * @param hand the Cards given to the player when a new round starts
	 */
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	/**
	 * 
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * 
	 * @return the Team
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * 
	 * @return the hand, as an ArrayList of Cards
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	/**
	 * 
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	/**
	 * Returns a String with the name and hand of this Player
	 */
	public String toString() {
		return "Player (" + playerName + ", " + hand + ")";
	}
}
