package model;

import java.util.ArrayList;
import utility.TeamObserver;

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
	private ArrayList<Card> scope;
	private String teamName;
	
	private ArrayList<TeamObserver> teamObservers;	//bisogna ancora inizializzarlo da qualche parte -Andrea

	
	/*
	 * Il team viene inizializzato senza giocatori e carte
	 */
	public Team(String teamName) {
		this.playersInTeam = new ArrayList<Player>();
		this.cardsTaken = new ArrayList<Card>();
		this.scope = new ArrayList<Card>();
		this.teamObservers = new ArrayList<TeamObserver>();
		this.score = 0;
		this.teamName = teamName;
	}
	
	/*
	 * Aggiora il punteggio con le carte della mano corrente
	 * Questo metodo dovrebbe essere chiamato solo dallo ScoreProcessor e solo alla fine della mano!
	 * NB qua le carte non vengono resettate!
	 */
	public void addScore(int newScore) {
		this.score += newScore;
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
	
	public void addTeamObserver(TeamObserver teamObserver) {
		this.teamObservers.add(teamObserver);
	}
	
	public ArrayList<Card> getCards() {
		return this.cardsTaken;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public ArrayList<Card> getScope() {
		return this.scope;
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
	
	public void scopa(Card scopaCard) {
		for (TeamObserver o : teamObservers)
			o.scopa(scopaCard);
	}
	
}
