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

public class GUIController {

	private CardLabel cardLabel;
	private TotalFrame[] playerView = new TotalFrame[4];
	private TablePanel tablePanel;
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
		tablePanel = new TablePanel();
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
			playerView[i] = new TotalFrame(playerHandler.getPlayer().getPlayerName(), tablePanel, playerPanel);
			playerView[i].unlockPlayer();
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

}
