package textInterface;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.GameController;
import controller.GameStartSetup;
import controller.HumanPlayerHandler;
import controller.HumanPlayerInterfaceController;
import graphicInterface.StartFrame;
import graphicInterface.TeamPanel;
import graphicInterfaceController.GUIController;
import model.Card;
import utility.CardConverter;
import utility.TableObserver;
import utility.TeamObserver;

public class TUIController implements TableObserver, HumanPlayerInterfaceController{
	
	private GameController gameController;
	private boolean newScopa;
	private Card lastScopa = null;
	private ArrayList<String> table = new ArrayList<String>();
	private ArrayList<String> player = new ArrayList<String>();
	private ArrayList<ArrayList<String>> playerCards = new ArrayList<ArrayList<String>>(); //un arraylist di carte per ogni player
	private Scanner scanner;
	
	private static TUIController defaultTuiController = null;
	
	public static TUIController getDefaultTUIController() {
		if (defaultTuiController == null) {
			defaultTuiController = new TUIController();
			return defaultTuiController;
		} else
			return defaultTuiController;
	}

	private TUIController() {

	}
	
	public void startGame() {
		StartTextInterface startText = new StartTextInterface();
		int humanPlayer = startText.getHumanPlayerTeam1() + startText.getHumanPlayerTeam2();
		GameStartSetup g = GameStartSetup.getDefaultGameSetup(startText.getConfig(), humanPlayer);
		TUIController.getDefaultTUIController().init(g.getHumanPlayers(), g.getGameController());
		g.getGameController().init();
	}
	
	public void init(ArrayList<HumanPlayerHandler> playerHandlers, GameController gameController) {
		this.gameController = gameController;
		
		scanner = new Scanner(System.in);
		newScopa = false;
		
		System.out.println("Table" + "\n");
		
		ArrayList<String> handCards = new ArrayList<String>();
		
		for (HumanPlayerHandler playerHandler : playerHandlers) {
			for(int i=0; i<10; i++) {
				handCards.add(playerHandler.getPlayer().getHand().get(i).toString());
			}
			playerCards.add(handCards); //per ogni giocatore vengono aggiunte le carte nell'arraylist
			player.add(playerHandler.getPlayer().getPlayerName());
			playerHandler.setInterfaceController(this);
		}
		
		System.out.println(player.get(0) + "\n" + playerCards.get(0));
		
		unlock(playerHandlers.get(0));
	}

	@Override
	public void updateOnAddition(Card c) {
		// TODO Auto-generated method stub
		table.add(c.toString());
	}

	@Override
	public void updateOnRemoval(List<Card> toRemove) {
		// TODO Auto-generated method stub
		for (Card card : toRemove) {
			int i=0;
			if(card.toString().equals(table.get(i))) {
				table.remove(i);
			} 
		}
	}

	@Override
	public void multipleChoice(HumanPlayerHandler humanPlayerHandler, ArrayList<ArrayList<Card>> choices) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(HumanPlayerHandler humanPlayerHandler) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void unlock(HumanPlayerHandler humanPlayerHandler) {
		// TODO Auto-generated method stub
		System.out.println("\n Carte sul tavolo: " + table);
		System.out.println("\n Turno di: " + humanPlayerHandler.getPlayer()+ ", scegli la carta che vuoi giocare!");
		humanPlayerHandler.cardPlayed(CardConverter.toModelCard(scanner.nextLine()));
	}
	
	//test scanner + conversione da stringa a carta
/*	public static void main(String[] args) {
		Scanner scanner; 
		scanner = new Scanner(System.in);
		System.out.println(CardConverter.toModelCard(scanner.nextLine()));
		System.out.println("Scrivi ancora");
		System.out.println(CardConverter.toModelCard(scanner.nextLine()));
		scanner.close();
		
		
	}*/
	
	public static void main(String[] args) {
		TUIController.getDefaultTUIController().startGame();
	}

}
