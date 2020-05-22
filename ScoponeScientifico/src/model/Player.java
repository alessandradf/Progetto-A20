package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta i giocatori.
 * 
 **/

public abstract class Player {

	private Team team;
	private String playerName;
	private List<Card> hand;

	public Player(String name) {
		this.playerName = name;
		hand = new ArrayList<Card>();
	}
	
	public boolean playCard(Card c) {
		return hand.remove(c);
	}
	
	/**
	 * @param hand carte date al giocatore all'inzio della partita
	 */
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}

	public List<Card> getHand() {
		return hand;
	}

	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String toString() {
		return "Player (" + playerName + ", " + hand + ")";
	}
}
