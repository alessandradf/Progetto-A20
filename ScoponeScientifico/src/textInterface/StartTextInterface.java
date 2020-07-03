package textInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StartTextInterface {
	private int humanNumbersTeam1;
	private int humanNumbersTeam2;
	
	/*
	 * contiene i nomi dei giocatoi dei team
	 * l'ordine di inserimento è congruo a quello di config
	 */
	private ArrayList<String> allNames;
	private Scanner scanner;
	private String[] config = new String[4];
	private String[] playersNames = new String[4];
	
	public StartTextInterface() {
		
		scanner = new Scanner(System.in);
		System.out.println("Giocatori umani Team1:");
		this.humanNumbersTeam1 = readHumanNumbers();
		
		readNames(humanNumbersTeam1, 1);
		
		chooseOrder(1, this.humanNumbersTeam1);
		
		System.out.println("Numero giocatori umani Team2:");
		this.humanNumbersTeam2 = readHumanNumbers();
		
		readNames(humanNumbersTeam2, 2);
		
		chooseOrder(2, this.humanNumbersTeam2);
		
		this.allNames = new ArrayList<String>();
		
		for(String s : playersNames) {
			allNames.add(s);
		}
		
	}
	
	/*
	 * Legge da scanner il numero di giocatori umani di un team
	 */
	private int readHumanNumbers() {
		
		int humanNumber = 0;
		boolean numberOk = false;
		
		while(numberOk == false) {
			try {
				humanNumber = scanner.nextInt();
				if(humanNumber<0 || humanNumber>2) {
					throw new IOException();
				}
				numberOk = true;

			} catch (Exception e) {
				System.err.println("Il numero deve essere compreso tra 0 e 2");
			}
		}
		return humanNumber;	
	}
	
	/*
	 * Legge da scanner se gioca prima umano o computer
	 */
	private void chooseOrder(int teamNumber, int humanPlayers) {
		
		String firstPlayer = null;
		boolean condition = false;
		
		if(humanPlayers == 0) {
			this.config[teamNumber - 1] = "Computer";
			this.config[teamNumber + 1] = "Computer";
		}
		else if(humanPlayers == 2) {
			this.config[teamNumber - 1] = "Human";
			this.config[teamNumber + 1] = "Human";	
		}
		else {
			
			System.out.println("Gioca prima l'umano o il computer? [Digitare 'umano' o 'computer']");
			
			while(condition == false) {
				
				try {
					firstPlayer = scanner.next();
					
					if(firstPlayer.equalsIgnoreCase("umano")) {
						
						this.config[teamNumber - 1] = "Human";
						this.config[teamNumber + 1] = "Computer";
					}
					else if(firstPlayer.equalsIgnoreCase("computer")) {
						this.config[teamNumber - 1] = "Computer";
						this.config[teamNumber + 1] = "Human";
						swapOrderNames(teamNumber);
					}
					else {
						throw new Exception();
					}
					condition = true;
				}
				catch (Exception e) {
					System.err.println("Formato non valido");
				}
			}
		}
	}
	
	
	/*
	 * Legge da scanner i nomi dei giocatori umani di un teamn e li aggiunge all'ArrayList.
	 * I nomi vengono aggiunti senza tenere conto dell'ordine di gioco effettivo
	 */
	private void readNames(int humanPlayers, int teamNumber) {
		
		String[] teamNames = {null, null};
		
		if(humanPlayers >= 1) {
			System.out.println("Inserisci i nomi dei giocatori umani Team1:");
			for(int i=0; i<humanPlayers; i++) {
				teamNames[i] = scanner.next();
			}
		}
		
		this.playersNames[teamNumber - 1] = teamNames[0];
		this.playersNames[teamNumber + 1] = teamNames[1];
	}
	
	/*
	 * Viene chiamato nel caso in cui gioca prima il computer dell'umano
	 * Inverte i nomi così che l'ordine coincida con l'ordine di gioco
	 */
	private void swapOrderNames(int teamNumber) {
		
		String first = this.playersNames[teamNumber - 1];
		this.playersNames[teamNumber - 1] = this.playersNames[teamNumber + 1];
		this.playersNames[teamNumber + 1] = first;
	}
	

	public int getHumanNumbersTeam1() {
		return humanNumbersTeam1;
	}

	public int getHumanNumbersTeam2() {
		return humanNumbersTeam2;
	}

	public ArrayList<String> getPlayersNames() {
		return allNames;
	}

	public String[] getConfig() {
		return config;
	}
}
