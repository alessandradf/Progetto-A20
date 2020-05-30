package model;

import java.util.ArrayList;

/**
 * Rappresenta le due squadre di scopone
 * 
 * @author Stefano
 *
 */
public class Team {

	private ArrayList<Player> playersInTeam;
	private int score;
	private ArrayList<Card> cardsTaken;
	private String teamName;

	
	/*
	 * Il team viene inizializzato senza giocatori e carte
	 */
	public Team(String teamName) {
		this.playersInTeam = new ArrayList<Player>();
		this.cardsTaken = new ArrayList<Card>();
		this.score = 0;
		this.teamName = teamName;
	}
	
	/*
	 * Calcola il punteggio della squadra e lo restituisce
	 */
	public int calculateScore() {
		/*
		 * TODO va aggiunta la logica con cui calcolare il punteggio in base alle carte
		 */
		return 0;
	}

	
	/**
	 * Aggiunge un Player al Team. Controlla prima se � gi� stato aggiunto
	 */
	public boolean addPlayer(Player p) {
		if (!(this.playersInTeam.contains(p))) {
			this.playersInTeam.add(p);
			return true;
		}
		return false;
	}

	
	/*
	 * Aggiunge le carte passate a quelle del team
	 */
	public void addCards(ArrayList<Card> cards) {
		this.cardsTaken.addAll(cards);
	}
	
	/*
	 * Svuota l'ArrayList di carte prese dal team
	 */
	private void resetTeamCards() {
		this.cardsTaken.clear();
	}
	
	public int getScore() {
		return this.score;
	}
	
	public ArrayList<Player> getPlayers(){
		return this.playersInTeam;
	}
	
	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @return the playersInTeam
	 */
	public ArrayList<Player> getPlayersInTeam() {
		return playersInTeam;
	}
	
}
