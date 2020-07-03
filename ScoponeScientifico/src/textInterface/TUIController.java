package textInterface;

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
import graphicInterface.StartFrame;
import graphicInterface.TeamPanel;
import graphicInterfaceController.GUIController;
import model.Card;
import model.Team;
import utility.CardConverter;
import utility.TableObserver;

public class TUIController implements InterfaceController, HumanPlayerInterfaceController, InterfaceTurnFinalizer {

	// TODO togliere variabili non usate
	private static boolean playAgain = true;
	private Team winnerTeam;

	private GameController gameController;
	StartTextInterface startText;
	private ArrayList<String> table = new ArrayList<String>();
	private Scanner scanner;
	private StandardOutput historyOutput;

	public void startGame() {

		scanner = new Scanner(System.in);
		startText = new StartTextInterface();
		int humanPlayer = startText.getHumanNumbersTeam1() + startText.getHumanNumbersTeam2();
		GameStartSetup g = new GameStartSetup(startText.getConfig(), humanPlayer, this, startText.getPlayersNames());
	}

	public void startGame(String message) {
		System.out.println(message);
		scanner = new Scanner(System.in);
		startText = new StartTextInterface();
		int humanPlayer = startText.getHumanNumbersTeam1() + startText.getHumanNumbersTeam2();
		GameStartSetup g = new GameStartSetup(startText.getConfig(), humanPlayer, this, startText.getPlayersNames());
	}

	public void init(ArrayList<HumanPlayerHandler> playerHandlers, GameController gameController) {
		this.gameController = gameController;
		gameController.setTurnFinalizer(this);
		historyOutput = new StandardOutput();
		this.gameController.getHistory().setOutput(historyOutput);

		for (HumanPlayerHandler playerHandler : playerHandlers) {

			playerHandler.setInterfaceController(this);
		}

	}

	@Override
	public void updateOnAddition(Card c) {
		// TODO Auto-generated method stub
		table.add(c.toString());
	}

	@Override
	public void updateOnRemoval(List<Card> toRemove) {
		// TODO Auto-generated method stub

		for (int i = 0; i < toRemove.size(); i++) {
			for (int j = 0; j < table.size(); j++) {
				if (toRemove.get(i).toString().equals(table.get(j))) {
					table.remove(j);
				}

			}
		}
	}

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

		int scelta = Integer.parseInt(scanner.nextLine());
		try {

			gameController.endTurn(humanPlayerHandler, choices.get(scelta - 1));
		} catch (CardNotFoundException e) {

		}
	}

	@Override
	public void lock(HumanPlayerHandler humanPlayerHandler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unlock(HumanPlayerHandler humanPlayerHandler) {

		if (humanPlayerHandler.getPlayer().getHand().size() == 0)
			return;

		System.out.println("\n Carte sul tavolo: " + table.toString());
		System.out.println("\n Turno di: " + humanPlayerHandler.getPlayer() + ", scegli la carta che vuoi giocare!");
		boolean isValid = false;
		while (!isValid) {

			try {
				String stringa = scanner.nextLine();

				humanPlayerHandler.cardPlayed(CardConverter.toModelCard(stringa));
				isValid = true;
			} catch (NumberFormatException e) {
				System.out.println("devi inserire una delle carte che hai in mano nel formato 'valore SEME'");
				isValid = false;
			}
			catch (CardNotFoundException c) {
				// TODO: handle exception
				System.out.println("devi inserire una delle carte che hai in mano nel formato 'valore SEME'");
				isValid = false;
			}
			catch (IllegalArgumentException il) {
				// TODO: handle exception
				System.out.println("devi inserire una delle carte che hai in mano nel formato 'valore SEME'");
				isValid = false;
			}
			catch(NoSuchElementException no) {
				System.out.println("devi inserire una delle carte che hai in mano nel formato 'valore SEME'");
				isValid = false;
				
			}
		}

	}

	@Override
	public void newHand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameFinished(Team winnerTeam) {
		this.winnerTeam = winnerTeam;
	}

	private void trueEnd() {
		// TODO Auto-generated method stub
		System.out.println("GIOCO TERMINATO! COMPLIMENTI AL TEAM VINCITORE: " + winnerTeam.getTeamName() + "!");
		System.out.println(
				"\n Vuoi fare un'altra partita? scrivi yes, se vuoi giocare ancora, se no schiaccia una lettera qualunque");
		String scelta = scanner.nextLine();

		if (scelta.equals("yes")) {
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
			System.out.println("lalala");
		}
	}

}
