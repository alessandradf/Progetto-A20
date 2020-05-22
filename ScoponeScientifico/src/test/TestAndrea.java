package test;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Card;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
import model.Table;
import model.Team;
import modelController.Game;

/**
 * NON TOCCATEMELA SERVE A ME PER FARE DEI PASTICCI
 * 
 * @author Andrea
 *
 */
public class TestAndrea {

	public static void main(String args[]) {
		Game game = Game.getDefaultGame();
		List<Player> players = game.getPlayers();
		for(Player p : players) {
			System.out.println(p.getHand());
		}
		List<Card> hand = players.get(0).getHand();
		game.playRound(players.get(0), players.get(0).getHand().get(0));
		Table t = game.getDefaultTable();
		System.out.println(t.readCards());
	}

	/**
	 * Consente di visualizzare un deck di carte passato come Set
	 * 
	 * @param deck
	 */
	public static void seeDeck(Set<Card> deck) {
		ArrayList<String> arrayCarte = new ArrayList<String>();
		Iterator<Card> iterator = deck.iterator();
		while (iterator.hasNext()) {
			arrayCarte.add(iterator.next().toString());
		}
		Collections.sort(arrayCarte);
		for (String s : arrayCarte) {
			System.out.println(s);
		}
	}
}
