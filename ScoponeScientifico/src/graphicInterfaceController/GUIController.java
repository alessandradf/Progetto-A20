package graphicInterfaceController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import CardTest.*;
import controller.AbstractPlayerHandler;
import controller.CardConverter;
import controller.ChoiceReceiver;

import controller.HumanPlayerHandler;

import graphicInterface.CardListener;
import graphicInterface.HistoryFrame;
import graphicInterface.OptionsPopUp;
import graphicInterface.PlayerPanel;
import graphicInterface.StartFrame;
import graphicInterface.TablePanel;
import graphicInterface.TeamPanel;
import graphicInterface.TotalFrame;
import model.Card;

import utility.TableObserver;

public class GUIController implements TableObserver {

	private ArrayList<TotalFrame> playerView = new ArrayList<TotalFrame>();
	private ArrayList<TablePanel> tablePanel = new ArrayList<TablePanel>();
	private ArrayList<TeamPanel> team1Panels = new ArrayList<TeamPanel>();
	private ArrayList<TeamPanel> team2Panels = new ArrayList<TeamPanel>();

	private HistoryFrame historyFrame;

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
		historyFrame = new HistoryFrame();
		TotalFrame totalFrame;
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
			team1Panels.add(new TeamPanel(1));
			team2Panels.add(new TeamPanel(2));
			totalFrame = new TotalFrame(playerHandler.getPlayer().getPlayerName(), tablePanel.get(i), playerPanel,
					team1Panels.get(i), team2Panels.get(i));
			playerPanel.setTotalFrame(totalFrame);
			playerView.add(totalFrame);
			i++;
			// playerView[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		playerView.get(0).unlockPlayer();

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
			totalFrame.repaint();
			totalFrame.validate();
			i++;
		}

	}

	public void chooseOptions(ArrayList<ArrayList<Card>> optionCard, ChoiceReceiver choiceReceiver) {

		OptionsPopUp op = new OptionsPopUp(optionCard);
		op.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				choiceReceiver.choiceMade((ArrayList<Card>) op.getComboBox().getSelectedItem());
				op.dispose();
			}
		});

	}

	// metodo per aggiornare la cronologia di gioco nel caso in cui non sia stata
	// presa nessuna carta dal giocatore
	public void updateHistory(AbstractPlayerHandler playerHandler, Card cardPlayed) {

		historyFrame.setHistory("<html><br>" + playerHandler.getPlayer().getPlayerName() + " ha giocato la carta: "
				+ cardPlayed.toString());
	}

	// overload del metodo precedente nel caso in cui siano state prese delle carte
	public void updateHistory(AbstractPlayerHandler playerHandler, ArrayList<Card> cardsTaken) {

		historyFrame.setHistory("<html><br>" + playerHandler.getPlayer().getPlayerName()
				+ " ha preso le seguenti carte: " + cardsTaken.toString());
	}

	// aggiornamento della cronologia nel caso in cui venga fatta una scopa
	public void updateHistoryScopa(AbstractPlayerHandler playerHandler, Card cardPlayed) {

		historyFrame.setHistory("<html><br>" + playerHandler.getPlayer().getPlayerName()
				+ " ha fatto scopa con la carta: " + cardPlayed.toString());
	}

	public ArrayList<TeamPanel> getTeam1Panels() {
		return team1Panels;
	}

	public ArrayList<TeamPanel> getTeam2Panels() {
		return team2Panels;
	}

}
