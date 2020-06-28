package textInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class StartTextInterface {
	
	private ArrayList<String> players;
	private Scanner scanner;
	
	public StartTextInterface() {
		scanner = new Scanner(System.in);
		System.out.println("Inserire il tipo di giocatori nell'ordine di gioco:");
		players = new ArrayList<String>();
		while(players.size()<4) {
			players.add(scanner.next());
		}
		scanner.close();
		//System.out.println(players);
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

}
