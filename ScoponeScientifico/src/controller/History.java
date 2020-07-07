package controller;

import java.util.ArrayList;
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
		String s = "\ne ha preso le seguenti carte : \n[ ";
		for (Card c : toRemove) {
			s += c.toString() + " ";
		}
		s += "]";
		
		this.entry.append(s);
	}

	
	@Override
	public void updateScore(int score, int lastHandScore) {
	}

	@Override
	public void scopa(Card scopaCard) {
		String s = "\nE' stata fatta la seguente scopa: " + scopaCard.toString();
		this.entry.append(s);
	}
	
	/*
	 * viene chiamato direttamente da GameController alla fine dell'ultima mano
	 */
	public void finalizeHand(Player lastToTake, ArrayList<Card> lastCards) {
		this.entry = new StringBuilder();		// resetto lo StringBuilder per non avere una entry incompleta
		String s = "Le carte rimanenti sul tavolo sono\n state prese da " + lastToTake.getPlayerName();
		
		s += "\n[ ";
		
		for(Card c : lastCards) {
			s += c.toString() + " ";
		}
		s += " ]";
		
		this.entry.append(s);
	}

}
