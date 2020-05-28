package model;

import java.util.ArrayList;
import java.util.List;

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
	
	public Player() {
		this("DefaultName");
	}
	
	/*
	 * Questo metodo va protetto perchè non può e non deve essere chiamato da fuori del controller
	 */
	protected void removeCardFromHand(Card card) throws CardNotFoundException {

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
	
	

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String toString() {
		return "Player (" + playerName + ", " + hand + ")";
	}
}
