package model;

import java.util.ArrayList;

/**
 * Rappresenta i 4 giocatori.
 * @author Andrea
 *
 */
public class Player {

	private Team team;
	// per ora l'idea � di tenere le carte nell'array list, nel caso dopo lo cambieremo
	// private ArrayList<Card> cards;
	
	public Player() {
		
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
}
