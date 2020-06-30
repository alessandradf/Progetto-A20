package controller;

import java.util.List;

import model.Card;
import model.Player;
import utility.OutputInterface;
import utility.PlayerObserver;
import utility.TableObserver;
import utility.TeamObserver;

public class History implements TeamObserver, TableObserver, PlayerObserver {
	
	private OutputInterface output;

	private StringBuilder entry;	//contiene la String che verrà stampata a fine turno
	
	public History() {
		this.entry = new StringBuilder();
	}

	public void setOutput(OutputInterface output) {
		this.output = output;
	}
	
	/*
	 * Scrive la string relativa al turno corrente sull'output e la resetta
	 */
	public void writeOnOutput() {
		this.output.writeOnOutput(this.entry.toString());
		this.entry = new StringBuilder();
	}

	
	/*
	 * Quando un giocatore gioca una carta
	 */
	@Override
	public void updateOnPlayedCard(Player p, Card playedCard) {
		String s = p.getPlayerName() + " ha giocato la carta " + playedCard.toString();
		this.entry.append(s);
	}

	// La History non viene aggiornata quando viene messa una carta sul tavolo
	@Override
	public void updateOnAddition(Card c) {
	}

	/*
	 * Quando vengono effettuate delle prese dal tavolo
	 */
	@Override
	public void updateOnRemoval(List<Card> toRemove) {
		String s = "\ne ha preso le seguenti carte : \n< ";
		for (Card c : toRemove) {
			s += c.toString() + " ";
		}
		s += ">";
		
		this.entry.append(s);
	}

	
	@Override
	public void updateScore(int score) {
	}

	@Override
	public void scopa(Card scopaCard) {
		String s = "\nE' stata fatta la seguente scopa: " + scopaCard.toString();
		this.entry.append(s);
	}

}
