package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import model.Card;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
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

		ArrayList<Player> players = new ArrayList<Player>();
		
		players.add(new HumanPlayer("Cicciopasticcio"));
		for (int i = 0; i < 3; i++) {
			players.add(new ComputerPlayer("ComputerPlayer " + i));
		}
		
		for(Player p : players) {
			System.out.println(p);
		};
		
		Team team = new Team("team1");
		team.addPlayer(players.get(0));
		// seeDeck(game.getDeck());
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
