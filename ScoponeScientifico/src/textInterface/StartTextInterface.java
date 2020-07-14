package textInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Manages the game options chose by the user through the console
 *
 */
public class StartTextInterface {
	private int humanNumbersTeam1;
	private int humanNumbersTeam2;
	private ArrayList<String> allNames;
	private Scanner scanner;
	private String[] config = new String[4];
	private String[] playersNames = new String[4];
	private int maxScore;

	/**
	 * Creates the StartTextInterface and sets all the options trough the inputs of
	 * the user read from the Scanner.
	 * 
	 * @see Scanner
	 */
	public StartTextInterface() {

		scanner = new Scanner(System.in);
		System.out.println("Numero giocatori umani Team1:");
		this.humanNumbersTeam1 = readHumanNumbers();

		readNames(humanNumbersTeam1, 1);

		chooseOrder(1, this.humanNumbersTeam1);

		System.out.println("Numero giocatori umani Team2:");
		this.humanNumbersTeam2 = readHumanNumbers();

		readNames(humanNumbersTeam2, 2);

		chooseOrder(2, this.humanNumbersTeam2);

		this.allNames = new ArrayList<String>();

		for (String s : playersNames) {
			allNames.add(s);
		}
		chooseMaxScore();

	}

	/**
	 * Reads how many human players are in the team from the user's input through
	 * the Scanner
	 * 
	 * @return humanNumber number of human players
	 * @see Scanner
	 */
	private int readHumanNumbers() {

		int humanNumber = 0;
		boolean numberOk = false;

		while (numberOk == false) {
			try {
				humanNumber = Integer.parseInt(scanner.next());
				if (humanNumber < 0 || humanNumber > 2) {
					throw new IOException();
				}
				break;

			} catch (IOException e) {
				System.err.println("Il numero deve essere compreso tra 0 e 2");
			} catch (NumberFormatException numberFormatException) {
				System.err.println("Inserisci un numero tra 0 e 2");
			}

		}
		return humanNumber;
	}

	/**
	 * Allows the user to choose if he wants to play before or after the computer in
	 * his team (if there is a computer player in his team)
	 * 
	 * @param teamNumber   team's id
	 * @param humanPlayers number of human players in the team
	 */
	private void chooseOrder(int teamNumber, int humanPlayers) {

		String firstPlayer = null;
		boolean condition = false;

		if (humanPlayers == 0) {
			this.config[teamNumber - 1] = "Computer";
			this.config[teamNumber + 1] = "Computer";
		} else if (humanPlayers == 2) {
			this.config[teamNumber - 1] = "Human";
			this.config[teamNumber + 1] = "Human";
		} else {

			System.out.println("Gioca prima l'umano o il computer? [Digitare 'umano' o 'computer']");

			while (condition == false) {

				try {
					firstPlayer = scanner.next();

					if (firstPlayer.equalsIgnoreCase("umano")) {

						this.config[teamNumber - 1] = "Human";
						this.config[teamNumber + 1] = "Computer";
					} else if (firstPlayer.equalsIgnoreCase("computer")) {
						this.config[teamNumber - 1] = "Computer";
						this.config[teamNumber + 1] = "Human";
						swapOrderNames(teamNumber);
					} else {
						throw new Exception();
					}
					condition = true;
				} catch (Exception e) {
					System.err.println("Formato non valido");
				}
			}
		}
	}

	/**
	 * Reads the names of the human players in the team from the user's input
	 * through the Scanner
	 * 
	 * @param humanPlayers number of human players in the team
	 * @param teamNumber   team's id
	 * @see Scanner
	 */
	private void readNames(int humanPlayers, int teamNumber) {

		String[] teamNames = { null, null };

		if (humanPlayers >= 1) {
			System.out.println("Inserisci i nomi dei giocatori umani Team" + teamNumber + ":");
			for (int i = 0; i < humanPlayers; i++) {
				teamNames[i] = scanner.next();
			}
		}

		this.playersNames[teamNumber - 1] = teamNames[0];
		this.playersNames[teamNumber + 1] = teamNames[1];
	}

	/**
	 * Swaps the names in the team in order to make them coherent with the game's
	 * configuration
	 * 
	 * @param teamNumber team's id
	 */
	private void swapOrderNames(int teamNumber) {

		String first = this.playersNames[teamNumber - 1];
		this.playersNames[teamNumber - 1] = this.playersNames[teamNumber + 1];
		this.playersNames[teamNumber + 1] = first;
	}

	/**
	 * Allows the user to choose the score to reach to finish the game
	 * 
	 * @see Scanner
	 */
	private void chooseMaxScore() {
		System.out.println("Inserisci il punteggio che deve essere raggiunto dal team vincitore della partita: ");
		boolean isValid = false;
		while (!isValid) {
			try {
				maxScore = Integer.parseInt(scanner.next());
				if (maxScore <= 0) {
					throw new IOException();
				}
				isValid = true;
			} catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
				System.out.println("Devi inserire il numero di punti da raggiungere per terminare la partita!");
			} catch (IOException ex) {
				System.out.println("Il punteggio deve essere maggiore di 0!");
			}
		}
	}

	/**
	 * @return the number of humans in team 1
	 */
	public int getHumanNumbersTeam1() {
		return humanNumbersTeam1;
	}

	/**
	 * @return the number of humans in team 2
	 */
	public int getHumanNumbersTeam2() {
		return humanNumbersTeam2;
	}

	/**
	 * @return the names of the human players
	 */
	public ArrayList<String> getPlayersNames() {
		return allNames;
	}

	/**
	 * @return configuration
	 */
	public String[] getConfig() {
		return config;
	}

	/**
	 * @return the score to reach to finish the game
	 */
	public int getMaxScore() {
		return maxScore;
	}
}
