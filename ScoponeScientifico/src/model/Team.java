package model;

import java.util.ArrayList;
import utility.TeamObserver;

/**
 * Represents one Team of 2 Players in the game
 * This class tracks also the information about the score (both of the last hand and of the entire game)
 * and the scopas done in the current hand of the game
 */
public class Team {

	private ArrayList<Player> playersInTeam;
	private int score;
	private int lastHandScore;
	private ArrayList<Card> cardsTaken;
	private ArrayList<Card> scope;
	private String teamName;
	private ArrayList<TeamObserver> teamObservers;

	
	/**
	 * Creates an empty team, wih the given name
	 * @param teamName the name of the team
	 */
	public Team(String teamName) {
		this.playersInTeam = new ArrayList<Player>();
		this.cardsTaken = new ArrayList<Card>();
		this.scope = new ArrayList<Card>();
		this.teamObservers = new ArrayList<TeamObserver>();
		this.score = 0;
		this.lastHandScore = 0;
		this.teamName = teamName;
	}
	
	/*
	 * Aggiora il punteggio con le carte della mano corrente
	 * Questo metodo dovrebbe essere chiamato solo dallo ScoreProcessor e solo alla fine della mano!
	 * NB qua le carte non vengono resettate!
	 */
	
	/**
	 * Updates the score done in the last hand and sums it to the total score of the game.
	 * This method must be called only at the end of the hand.
	 * @param newScore the score of the last hand
	 */
	public void addScore(int newScore) {
		this.lastHandScore = newScore;
		this.score += newScore;
		
		for (TeamObserver o : teamObservers) {
			o.updateScore(this.score, lastHandScore);
		}
	}

	
	/**
	 * Add one Player to the Team, controlling that the Player isn't alredy known.
	 * @return true if the Player is correctly added.
	 */
	public boolean addPlayer(Player p) {
		if (!(this.playersInTeam.contains(p))) {
			this.playersInTeam.add(p);
			return true;
		}
		return false;
	}

	
	/**
	 * Add the list of Cards to those taken by the team
	 * @param cards the cards to be added
	 */
	public void addCards(ArrayList<Card> cards) {
		this.cardsTaken.addAll(cards);
	}
	
	/**
	 * Clears the cards taken by the team.
	 */
	protected void resetTeamCards() {
		this.cardsTaken.clear();
	}
	
	public void addTeamObserver(TeamObserver teamObserver) {
		this.teamObservers.add(teamObserver);
	}
	
	/**
	 * 
	 * @return the cards taken by the team
	 */
	public ArrayList<Card> getCards() {
		return this.cardsTaken;
	}
	
	/**
	 * 
	 * @return the score of the Team since the start of the game
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * 
	 * @return the scopas made by the team
	 */
	public ArrayList<Card> getScope() {
		return this.scope;
	}
	
	/**
	 * 
	 * @return the players in the Team
	 */
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
	 * Notify the team that the card scopaCard made a scopa.
	 * @param scopaCard the card which made a scopa
	 */
	public void scopa(Card scopaCard) {
		this.scope.add(scopaCard);
		for (TeamObserver o : teamObservers)
			o.scopa(scopaCard);
	}
	
}
