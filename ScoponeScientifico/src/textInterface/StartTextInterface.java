package textInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class StartTextInterface {
	
	private int humanPlayerTeam1;
	private int humanPlayerTeam2;
	private ArrayList<String> humanPlayerNameTeam1;
	private ArrayList<String> humanPlayerNameTeam2;
	private boolean isHumanPlayerTeam1First;
	private boolean isHumanPlayerTeam2First;
	private Scanner scanner;
	
	public StartTextInterface() {
		scanner = new Scanner(System.in);
		System.out.println("Numero giocatori umani team 1:");
		humanPlayerTeam1 = scanner.nextInt();
		humanPlayerNameTeam1 = new ArrayList<String>();
		if(humanPlayerTeam1>1) {
			System.out.println("Inserisci i nomi di giocatori:2");
		}
		else if(humanPlayerTeam1==1) {
			System.out.println("Inserisci il nome del giocatore:");
		}
		for(int i=0; i<humanPlayerTeam1; i++) {
			humanPlayerNameTeam1.add(scanner.next());
		}
		if(humanPlayerTeam1==1) {
			System.out.println("Gioca prima l'umano o il computer?");
			if(scanner.next().equals("umano")) {
				isHumanPlayerTeam1First = true;
			}
			else isHumanPlayerTeam1First = false;
		}
		System.out.println("Numero giocatori umani team 2:");
		humanPlayerTeam2 = scanner.nextInt();
		humanPlayerNameTeam2 = new ArrayList<String>();
		if(humanPlayerTeam2>1) {
			System.out.println("Inserisci i nomi di giocatori:");
		}
		else if(humanPlayerTeam2==1) {
			System.out.println("Inserisci il nome del giocatore:");
		}
		for(int i=0; i<humanPlayerTeam2; i++) {
			humanPlayerNameTeam2.add(scanner.next());
		}
		if(humanPlayerTeam2==1) {
			System.out.println("Gioca prima l'umano o il computer?");
			if(scanner.next().equals("umano")) {
				isHumanPlayerTeam2First = true;
			}
			else isHumanPlayerTeam2First = false;
		}
		scanner.close();
	}

	public int getHumanPlayerTeam1() {
		return humanPlayerTeam1;
	}

	public int getHumanPlayerTeam2() {
		return humanPlayerTeam2;
	}

	public ArrayList<String> getHumanPlayerNameTeam1() {
		return humanPlayerNameTeam1;
	}

	public ArrayList<String> getHumanPlayerNameTeam2() {
		return humanPlayerNameTeam2;
	}

	public boolean isHumanPlayerTeam1First() {
		return isHumanPlayerTeam1First;
	}

	public boolean isHumanPlayerTeam2First() {
		return isHumanPlayerTeam2First;
	}

}
