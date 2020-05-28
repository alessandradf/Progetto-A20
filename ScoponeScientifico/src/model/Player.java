package model;

import java.util.ArrayList;

import exception.CardNotFoundException;

/**
 * Rappresenta i giocatori.
 * 
 **/

public class Player {

	private Team team;
	private String playerName;
	private ArrayList<Card> hand;

	public Player(String name) {
		this.playerName = name;
	}

	public void removeCardFromHand(Card card) throws CardNotFoundException {

	}

	/**
	 * @param hand carte date al giocatore all'inzio della partita
	 */
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}

	public ArrayList<Card> getHand() {
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
