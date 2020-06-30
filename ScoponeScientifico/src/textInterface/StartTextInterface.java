package textInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class StartTextInterface {
	
	private int humanPlayerTeam1;
	private int humanPlayerTeam2;
	private ArrayList<String> humanPlayerNameTeam1;
	private ArrayList<String> humanPlayerNameTeam2;
	//private boolean isHumanPlayerTeam1First;
	//private boolean isHumanPlayerTeam2First;
	private Scanner scanner;
	private String[] config = new String[4];
	
	public StartTextInterface() {
		scanner = new Scanner(System.in);
		System.out.println("Numero giocatori umani team 1:");
		humanPlayerTeam1 = scanner.nextInt();
		humanPlayerNameTeam1 = new ArrayList<String>();
		if(humanPlayerTeam1>1) {
			System.out.println("Inserisci i nomi di giocatori:2");
			config[0] = "Human";
			config[2] = "Human";
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
				config[0] = "Human";
				config[2] = "Computer";
			}
			else{
				config[0] = "Computer";
				config[2] = "Human";
			}
		}
		if(humanPlayerTeam1==0) {
			config[0] = "Computer";
			config[2] = "Computer";
		}
		System.out.println("Numero giocatori umani team 2:");
		humanPlayerTeam2 = scanner.nextInt();
		humanPlayerNameTeam2 = new ArrayList<String>();
		if(humanPlayerTeam2>1) {
			System.out.println("Inserisci i nomi di giocatori:");
			config[1] = "Human";
			config[3] = "Human";
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
				config[1] = "Human";
				config[3] = "Computer";
			}
			else {
				config[1] = "Computer";
				config[3] = "Human";
			}
		}
		if(humanPlayerTeam2==0) {
			config[1] = "Computer";
			config[3] = "Computer";
		}
		//scanner.close();
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

	public String[] getConfig() {
		for (String string : config) {
			System.out.println(string);
		}
		return config;
	}

}
