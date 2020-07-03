package model;

import java.util.ArrayList;
import java.util.List;

import exception.CardNotFoundException;
import utility.PlayerObserver;

/**
 * Rappresenta i giocatori.
 * 
 **/

public class Player {

	public static final int CARDS_PER_PLAYER = 10;
	
	private Team team;
	private String playerName;
	private ArrayList<Card> hand;
	private ArrayList<PlayerObserver> obs;

	public Player(String name) {
		this.playerName = name;
		hand = new ArrayList<Card>();
		this.obs = new ArrayList<PlayerObserver>();
	}
	
	public Player() {
		this("DefaultName");
	}
	
	/*
	 * Rimuove la carta dalla mano 
	 */
	protected void removeCardFromHand(Card card) throws CardNotFoundException {
	//	System.out.println("CARTA RIMOSSA" + card);
		if (hand.contains(card)) {
			
			hand.remove(card);
			updateObserver(card);
		}
		else {
			throw new CardNotFoundException("Unexpected card: " + card);
			
		}
	}
	
	public void addObserver(PlayerObserver o) {
		this.obs.add(o);
	}
	
	/*
	 * Aggiorna tutti gli observer a seguito di una carta giocata
	 */
	public void updateObserver(Card playedCard) {
		for (PlayerObserver o : this.obs) {
			o.updateOnPlayedCard(this, playedCard);
		}
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
