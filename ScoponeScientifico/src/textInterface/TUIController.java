package textInterface;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import controller.GameController;
import controller.GameStartSetup;
import controller.HumanPlayerHandler;
import controller.HumanPlayerInterfaceController;
import controller.InterfaceTurnFinalizer;
import graphicInterface.StartFrame;
import graphicInterface.TeamPanel;
import graphicInterfaceController.GUIController;
import model.Card;
import model.Team;
import utility.CardConverter;
import utility.TableObserver;
import utility.TeamObserver;

public class TUIController implements TableObserver, HumanPlayerInterfaceController, InterfaceTurnFinalizer {

	//TODO togliere variabili non usate
	private static boolean playAgain = true;
	private Team winnerTeam;
	
	private GameController gameController;
	StartTextInterface startText;
	private ArrayList<String> table = new ArrayList<String>();
	private ArrayList<String> player = new ArrayList<String>();
//	private ArrayList<ArrayList<String>> playerCards = new ArrayList<ArrayList<String>>(); //un arraylist di carte per ogni player
	private Scanner scanner;
	private HashMap<HumanPlayerHandler, ArrayList<String>> playerCards = new HashMap<HumanPlayerHandler, ArrayList<String>>();
	private ArrayList<HumanPlayerHandler> playerHandlers = new ArrayList<HumanPlayerHandler>();
	private boolean isFirstUnlock;
	private StandardOutput historyOutput;
	private boolean isFirstGame;

	private static TUIController defaultTuiController = null;

	

	private TUIController() {
		isFirstGame = true;

	}

	public void startGame() {

		scanner = new Scanner(System.in);
		startText = new StartTextInterface();
		int humanPlayer = startText.getHumanPlayerTeam1() + startText.getHumanPlayerTeam2();
		GameStartSetup g = new GameStartSetup(startText.getConfig(), humanPlayer);
		this.init(g.getHumanPlayers(), g.getGameController());
		g.getGameController().init();

		g.addTableObservers(this);
		g.getGameController().start();
	//	unlock(playerHandlers.get(0));
	}

	public void init(ArrayList<HumanPlayerHandler> playerHandlers, GameController gameController) {
		this.gameController = gameController;
		gameController.setTurnFinalizer(this);
		historyOutput = new StandardOutput();
		this.gameController.getHistory().setOutput(historyOutput);
		this.playerHandlers = playerHandlers;
		isFirstUnlock = true;

		

		System.out.println("Table" + "\n");

		ArrayList<String> handCards = new ArrayList<String>();

		for (HumanPlayerHandler playerHandler : playerHandlers) {
			for (int i = 0; i < 10; i++) {
				handCards.add(playerHandler.getPlayer().getHand().get(i).toString());
			}
			playerCards.put(playerHandler, handCards);// per ogni giocatore vengono aggiunte le carte nell'arraylist
			player.add(playerHandler.getPlayer().getPlayerName());
			playerHandler.setInterfaceController(this);
		}

		System.out.println(player.get(0) + "\n" + playerCards.get(0));

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

		gameController.endTurn(humanPlayerHandler, choices.get(scelta - 1));

	}

	@Override
	public void lock(HumanPlayerHandler humanPlayerHandler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unlock(HumanPlayerHandler humanPlayerHandler) {
		// TODO Auto-generated method stub
	/*	if (isFirstUnlock) {
			isFirstUnlock = false;
			return;

		}*/
		if(humanPlayerHandler.getPlayer().getHand().size() == 0)
			return;
		
		System.out.println("\n Carte sul tavolo: " + table.toString());
		System.out.println("\n Turno di: " + humanPlayerHandler.getPlayer() + ", scegli la carta che vuoi giocare!");
		String stringa = scanner.nextLine();
		humanPlayerHandler.cardPlayed(CardConverter.toModelCard(stringa));
		//playerCards.get(humanPlayerHandler).remove((String) stringa);

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
		System.out.println("GIOCO TERMINATO! COMPLIMENTI AL TEAM VINCITORE: " + winnerTeam + "!");
		System.out.println("\n Vuoi fare un'altra partita? scrivi yes, se vuoi giocare ancora, se no schiaccia una lettera qualunque");
		String scelta = scanner.nextLine();
		
		if(scelta.equals( "yes")) {
			//scanner.close();
			table.clear();
			player.clear();
			playerHandlers.clear();
			playerCards.clear();
			//startGame();
		}
		else
		{
			System.exit(0);
		}
	}

	// test scanner + conversione da stringa a carta
	/*
	 * public static void main(String[] args) { Scanner scanner; scanner = new
	 * Scanner(System.in);
	 * System.out.println(CardConverter.toModelCard(scanner.nextLine()));
	 * System.out.println("Scrivi ancora");
	 * System.out.println(CardConverter.toModelCard(scanner.nextLine()));
	 * scanner.close();
	 * 
	 * 
	 * }
	 */

	public static void main(String[] args) {
		
		while(TUIController.playAgain) {
			TUIController tui = new TUIController();
			tui.startGame();
			tui.trueEnd();
			System.out.println("lalala");
		}
	}



}
