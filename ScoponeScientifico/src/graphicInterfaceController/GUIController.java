package graphicInterfaceController;

import java.util.ArrayList;

import javax.swing.*;

import CardTest.*;
import controller.CardConverter;
import controller.GameController;
import controller.HumanPlayerHandler;
import graphicInterface.CardListener;
import graphicInterface.PlayerPanel;
import graphicInterface.TablePanel;
import graphicInterface.TotalFrame;
import model.Card;
import model.Player;
import model.SeedType;
import utility.TableObserver;

public class GUIController implements TableObserver{

	private CardLabel cardLabel;
	private TotalFrame[] playerView = new TotalFrame[4];
	private TablePanel[] tablePanel = new TablePanel[4];
	// private CardTester card;

	private static GUIController defaultGuiController = null;

	public static GUIController getDefaultGUIController() {
		if (defaultGuiController == null) {
			defaultGuiController = new GUIController();
			return defaultGuiController;
		} else
			return defaultGuiController;
	}

	private GUIController() {
		
	}

	
	public void init(HumanPlayerHandler[] playerHandlers) {
		int i = 0;
		PlayerPanel playerPanel;
		ArrayList<CardLabel> playerCards;
		for (HumanPlayerHandler playerHandler : playerHandlers) {
			playerCards = cardsConverter(playerHandler.getPlayer().getHand());
			for (CardLabel cardLabel : playerCards) {

				cardLabel.addMouseListener(new CardListener(cardLabel, playerHandler));

			}
			playerPanel = new PlayerPanel(playerCards);
			playerHandler.setPlayerPanel(playerPanel);
			tablePanel[i] = new TablePanel();
			playerView[i] = new TotalFrame(playerHandler.getPlayer().getPlayerName(), tablePanel[i], playerPanel);
			playerView[i].unlockPlayer();
			i++;
			//playerView[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}

	private ArrayList<CardLabel> cardsConverter(ArrayList<Card> cards) {

		ArrayList<CardLabel> cardLabels = new ArrayList<CardLabel>();

		for (Card card : cards) {
			cardLabels.add(CardConverter.toCardLabel(card));
		}
		return cardLabels;
	}

	public TotalFrame[] getPlayerView() {
		return playerView;
	}

	//metodi per aggiornare il tavolo e i frame dei giocatori
	@Override
	public void updateOnAddition(Card c) {
		// TODO Auto-generated method stub
		int i = 0;
		for (TotalFrame totalFrame : playerView) {
			this.tablePanel[i].putCardOnTable(CardConverter.toCardLabel(c));
			totalFrame.repaint();
			totalFrame.validate();
			i ++;
		}
	}

	@Override
	public void updateOnRemoval(ArrayList<Card> removedCards) {
		// TODO Auto-generated method stub
		int i = 0;
		
		for (TotalFrame totalFrame : playerView) {
			this.tablePanel[i].removeCardsFromTable(cardsConverter(removedCards));
			totalFrame.repaint();
			totalFrame.validate();
		}
		
	}

	public TablePanel[] getTablePanel() {
		return tablePanel;
	}

}
