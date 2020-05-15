package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta i giocatori.
 * 
 **/

public abstract class Player {

	private Team team;
	protected ArrayList<Card> hand;

	public Player() {

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
