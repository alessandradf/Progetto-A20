package start;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.GameController;
import controller.GameStartSetup;
import controller.HumanPlayerHandler;
import controller.HumanPlayerInterfaceController;
import controller.InterfaceTurnFinalizer;
import exception.CardNotFoundException;
import controller.InterfaceController;
import model.Card;
import model.Team;
import textInterface.StandardOutput;
import textInterface.StartTextInterface;
import utility.CardConverter;

/**
 * Implements {@link InterfaceController},
 * {@link HumanPlayerInterfaceController}, {@link InterfaceTurnFinalizer}
 * 
 * Manages the text interface
 * 
 * @see StartTextInterface
 *
 */
public class TUIController implements InterfaceController, HumanPlayerInterfaceController, InterfaceTurnFinalizer {

	private static boolean playAgain = true;
	private Team winnerTeam;
	private GameController gameController;
	private StartTextInterface startText;
	private ArrayList<String> table = new ArrayList<String>();
	private Scanner scanner;
	private StandardOutput historyOutput;

	/**
	 * Makes the game start. This method instantiates a Scanner, the
	 * {@link StartTextInterface} and {@link GameStartSetup}
	 * 
	 * @see Scanner
	 */

	public void startGame() {

		scanner = new Scanner(System.in);
		startText = new StartTextInterface();
		@SuppressWarnings("unused")
		GameStartSetup g = new GameStartSetup(startText.getConfig(), this, startText.getPlayersNames(),
				startText.getMaxScore());

	}

	/**
	 * Overload, it is called only if the user tries to play with only
	 * ComputerPlayers
	 * 
	 * @param message String with a warning message that explain to the user to
	 *                select at least one HumanPlayer
	 *
	 */
	public void startGame(String message) {
		System.out.println(message);
		scanner = new Scanner(System.in);
		startText = new StartTextInterface();
		@SuppressWarnings("unused")
		GameStartSetup g = new GameStartSetup(startText.getConfig(), this, startText.getPlayersNames(),
				startText.getMaxScore());

	}

	/**
	 * 
	 * Allows the initialization of the {@link TUIController}
	 * 
	 * @param playerHandlers ArrayList that contains the HumanPlayerHandlers created
	 *                       by the GameStartSetUp
	 * @param gameController GameController in which is set the TurnFinalizer
	 * @see GameStartSetup
	 * @see GameController
	 */
	@Override
	public void init(ArrayList<HumanPlayerHandler> playerHandlers, GameController gameController) {
		this.gameController = gameController;
		gameController.setTurnFinalizer(this);
		historyOutput = new StandardOutput();
		this.gameController.getHistory().setOutput(historyOutput);

		for (HumanPlayerHandler playerHandler : playerHandlers) {

			playerHandler.setInterfaceController(this);
		}

	}

	/**
	 * Updates the cards on the table when there is a new card on it
	 * 
	 * @param c the new card on the table
	 * @see Card
	 */
	@Override
	public void updateOnAddition(Card c) {
		table.add(c.toString());
	}

	/**
	 * Updates the table when some cards are removed from it
	 * 
	 * @param toRemove List with the cards that need to be removed from the table
	 * @see Card
	 */
	@Override
	public void updateOnRemoval(List<Card> toRemove) {

		for (int i = 0; i < toRemove.size(); i++) {
			for (int j = 0; j < table.size(); j++) {
				if (toRemove.get(i).toString().equals(table.get(j))) {
					table.remove(j);
				}

			}
		}
	}

	/**
	 * Allows the user to choose which cards do he wants to take from the table when
	 * there are more possibilities
	 * 
	 * @param humanPlayerHandler player that has to make the choice
	 * @param choices            options of cards that can be chosen
	 * @see HumanPlayerHandler
	 * @see Card
	 */
	@Override
	public void multipleChoice(HumanPlayerHandler humanPlayerHandler, ArrayList<ArrayList<Card>> choices) {
		// TODO Auto-generated method stub
		System.out.println(
				"\n Scegli quali carte vuoi tra le seguenti opzioni (inserici il numero dell'opzione corrispondente): ");
		int i = 1;
		for (ArrayList<Card> arrayList : choices) {
			System.out.println("\n Opzione " + i + ": " + arrayList.toString());
			i++;
		}
		int scelta = 0;
		boolean isValid = false;
		while (!isValid) {
			try {
				scelta = Integer.parseInt(scanner.nextLine());
				if ((scelta > 0) && (scelta <= choices.size())) {
					isValid = true;
				} else {
					System.out.println("Devi inserire un numero tra quelli indicati tra le opzioni!");

				}
			} catch (NumberFormatException e) {
				System.out.println("Devi inserire un numero tra quelli indicati tra le opzioni!");

			}
		}

		try {

			gameController.endTurn(humanPlayerHandler, choices.get(scelta - 1));
		} catch (CardNotFoundException e) {

		}

	}

	/**
	 * This method is void because it is supposed to lock the player, but nothing
	 * has to be done in the text interface
	 * 
	 * @param humanPlayerHandler player that has to be locked
	 * @see HumanPlayerHandler
	 *
	 */
	@Override
	public void lock(HumanPlayerHandler humanPlayerHandler) {
		// TODO Auto-generated method stub

	}

	/**
	 * Unlocks the player, allowing him to play his turn through the choice of the
	 * card
	 * 
	 * @param humanPlayerHandler player that has to be unlocked
	 * @see HumanPlayerHandler
	 */
	@Override
	public void unlock(HumanPlayerHandler humanPlayerHandler) {

		if (humanPlayerHandler.getPlayer().getHand().size() == 0)
			return;

		System.out.println("\n Carte sul tavolo: " + table.toString());
		System.out.println("\n Turno di: " + humanPlayerHandler.getPlayer() + "appartenente al team:  "
				+ humanPlayerHandler.getPlayer().getTeam().getTeamName() + ", scegli la carta che vuoi giocare!");
		boolean isValid = false;
		while (!isValid) {

			try {
				String stringa = scanner.nextLine();
				Card cardPlayed = CardConverter.toModelCard(stringa);

				humanPlayerHandler.cardPlayed(cardPlayed);
				isValid = true;
			} catch (NumberFormatException e) {
				System.out.println("devi inserire una delle carte che hai in mano nel formato 'valore SEME'");

			} catch (CardNotFoundException c) {
				// TODO: handle exception
				System.out.println("devi inserire una delle carte che hai in mano nel formato 'valore SEME'");

			} catch (IllegalArgumentException il) {
				// TODO: handle exception
				System.out.println("devi inserire una delle carte che hai in mano nel formato 'valore SEME'");

			} catch (NoSuchElementException no) {
				System.out.println("devi inserire una delle carte che hai in mano nel formato 'valore SEME'");

			}
		}

	}

	/**
	 * This method is called at the end of every hand. It shows the scores of the
	 * teams on the standard output
	 */
	@Override
	public void newHand() {
		// TODO Auto-generated method stub
		System.out.println("Mano Terminata!");
		System.out.println("Punteggio Team1:  " + gameController.getTeams().get(0).getScore());
		System.out.println("Punteggio Team2:  " + gameController.getTeams().get(1).getScore());

	}

	/**
	 * This method is called at the end of the game and it's used to set the winner
	 * team
	 * 
	 * @param winnerTeam team that won
	 * @see Team
	 */
	@Override
	public void gameFinished(Team winnerTeam) {
		this.winnerTeam = winnerTeam;
		System.out.println("Ha vinto il Team: " + winnerTeam.getTeamName() + " con un punteggio di: "
				+ winnerTeam.getScore() + " punti!");
	}

	/**
	 * It is called in the main method and allows the users to choose if he wants to
	 * play again or not
	 */
	private void trueEnd() {
		// TODO Auto-generated method stub
		System.out.println("GIOCO TERMINATO! COMPLIMENTI AL TEAM VINCITORE: " + winnerTeam.getTeamName() + "!");
		System.out.println(
				"\n Vuoi fare un'altra partita? scrivi yes, se vuoi giocare ancora, se no schiaccia una lettera qualunque");
		String scelta = scanner.nextLine();

		if (scelta.equalsIgnoreCase("yes")) {
			table.clear();

		} else {
			System.exit(0);
		}
	}

	public static void main(String[] args) {

		while (TUIController.playAgain) {
			TUIController tui = new TUIController();
			tui.startGame();
			tui.trueEnd();

		}
	}

}
