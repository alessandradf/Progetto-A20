package graphicInterfaceController;

import java.util.ArrayList;
import java.util.List;

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

public class GUIController implements TableObserver {

	private CardLabel cardLabel;
	private ArrayList<TotalFrame> playerView = new ArrayList<TotalFrame>();
	private ArrayList<TablePanel> tablePanel = new ArrayList<TablePanel>();
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
			tablePanel.add(new TablePanel());
			playerView.add( new TotalFrame(playerHandler.getPlayer().getPlayerName(), tablePanel.get(i), playerPanel));
			playerView.get(i).unlockPlayer();
			i++;
			// playerView[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}

	private ArrayList<CardLabel> cardsConverter(List<Card> cards) {
		ArrayList<CardLabel> cardLabels = new ArrayList<CardLabel>();
		for (Card card : cards) {
			cardLabels.add(CardConverter.toCardLabel(card));			
		}
		return cardLabels;
	}

	public ArrayList<TotalFrame> getPlayerView() {
		return playerView;
	}

	// metodi per aggiornare il tavolo e i frame dei giocatori
	@Override
	public void updateOnAddition(Card c) {
		int i = 0;
		for (TotalFrame totalFrame : playerView) {
			this.tablePanel.get(i).putCardOnTable(CardConverter.toCardLabel(c));
			totalFrame.repaint();
			totalFrame.validate();
			i++;
		}
	}

	@Override
	public void updateOnRemoval(List<Card> removedCards) {
		// TODO Auto-generated method stub
		int i = 0;
		for (TotalFrame totalFrame : playerView) {
			this.tablePanel.get(i).removeCardsFromTable(cardsConverter(removedCards));
			//totalFrame.repaint();
			//totalFrame.validate();
			i++;
		}

	}

}
