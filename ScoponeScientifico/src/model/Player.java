package model;

import java.util.ArrayList;

/**
 * Rappresenta i giocatori.
 * 
 **/

public abstract class Player {

	private Team team;
	protected ArrayList<Card> hand; //va messo private, altrimenti tutto il model può modificarlo
	private String playerName;

	public Player() {

	}

	public Player(String name) {
		this.playerName = name;
	}

	/**
	 * @param hand carte date al giocatore all'inzio della partita
	 */
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	/**
	 * @return la carta giocata dal giocatore nel turno
	 */
	public abstract Card playCard();

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}
}
