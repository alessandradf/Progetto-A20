package graphicInterfaceController;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import CardTest.*;
import controller.AbstractPlayerHandler;
import controller.GameController;
import controller.HumanPlayerHandler;
import controller.HumanPlayerInterfaceController;
import graphicInterface.CardListener;
import graphicInterface.HistoryFrame;
import graphicInterface.OptionsPopUp;
import graphicInterface.PlayerPanel;
import graphicInterface.StartFrame;
import graphicInterface.TablePanel;
import graphicInterface.TeamPanel;
import graphicInterface.TotalFrame;
import model.Card;
import utility.CardConverter;
import utility.TableObserver;

public class GUIController implements TableObserver, HumanPlayerInterfaceController{
	private GameController gameController;
	private ArrayList<TotalFrame> playerView = new ArrayList<TotalFrame>();
	private ArrayList<TablePanel> tablePanel = new ArrayList<TablePanel>();
	private ArrayList<TeamPanel> team1Panels = new ArrayList<TeamPanel>();
	private ArrayList<TeamPanel> team2Panels = new ArrayList<TeamPanel>();
	private boolean newScopa;
	private Card lastScopa = null;
	private JFrame tableFrame; //frame per visualizzare il tavolo durante la scelta delle carte 
	
	private HashMap<HumanPlayerHandler, PlayerPanel> playerPanels = new HashMap<HumanPlayerHandler, PlayerPanel>(); //hashmap che associa playerpanel al rispettivo playerhandler

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

	public void init(HumanPlayerHandler[] playerHandlers, GameController gameController) {
		this.gameController = gameController;
		
		newScopa = false;
		
		int i = 0;
		historyFrame = new HistoryFrame();
		TotalFrame totalFrame;
		PlayerPanel playerPanel;
		ArrayList<CardLabel> playerCards;
		
		for (HumanPlayerHandler playerHandler : playerHandlers) {
			playerHandler.setInterfaceController(this);
			playerCards = cardsConverter(playerHandler.getPlayer().getHand());
			playerPanel = new PlayerPanel(playerCards);
			playerPanels.put(playerHandler, playerPanel);
			for (CardLabel cardLabel : playerCards) {

				cardLabel.addMouseListener(new CardListener(cardLabel, playerHandler, playerPanel));

			}
		//	String playerNameAndTeam = playerHandler.getPlayer().getPlayerName() +"--"+ playerHandler.getPlayer().getTeam().getTeamName();
			
			
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
		tablePanel.add(new TablePanel());
	//	i++;
	//	howManyTablePanels = i;
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
		for (TablePanel tablePanel : tablePanel) {
			tablePanel.putCardOnTable(CardConverter.toCardLabel(c));
			tablePanel.repaint();
			tablePanel.validate();
			
		}
		for (TotalFrame totalFrame : playerView) {
			
			totalFrame.repaint();
			totalFrame.validate();
			i++;
		}
	}

	@Override
	public void updateOnRemoval(List<Card> removedCards) {
		// TODO Auto-generated method stub
		int i = 0;
		for (TablePanel tablePanel : tablePanel) {
			tablePanel.removeCardsFromTable(cardsConverter(removedCards));
			tablePanel.repaint();
			tablePanel.validate();
			
		}
		for (TotalFrame totalFrame : playerView) {
			
			totalFrame.repaint();
			totalFrame.validate();
			i++;
		}
		this.updateHistory((ArrayList)removedCards);

	}

/*	public void chooseOptions(ArrayList<ArrayList<Card>> optionCard, ChoiceReceiver choiceReceiver) {

		OptionsPopUp op = new OptionsPopUp(optionCard);
		tableFrame = new JFrame();
		tableFrame.setSize(700, 400);
		tableFrame.add(tablePanel.get(tablePanel.size()-1));
		tableFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tableFrame.setResizable(false);
		tableFrame.setVisible(true);
		op.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				choiceReceiver.choiceMade((ArrayList<Card>) op.getComboBox().getSelectedItem());
				tableFrame.dispose();
				op.dispose();
			}
		});

	}*/

	// metodo per aggiornare la cronologia di gioco nel caso in cui non sia stata
	// presa nessuna carta dal giocatore
	public void updateHistory(AbstractPlayerHandler playerHandler, Card cardPlayed) {

		historyFrame.setHistory("<html><br>" + playerHandler.getPlayer().getPlayerName() + " ha giocato la carta: "
				+ cardPlayed.toString());
	}

	// overload del metodo precedente nel caso in cui siano state prese delle carte
	public void updateHistory(ArrayList<Card> cardsTaken) {

		historyFrame.setHistory("<html><br>" 
				+ " sono state prese le seguenti carte: " + cardsTaken.toString());
	}

	// aggiornamento della cronologia nel caso in cui venga fatta una scopa
	public void updateHistoryScopa(Card cardPlayed) {
		if(newScopa == false)
		{
		lastScopa = cardPlayed;
		historyFrame.setHistory("<html><br>" + 
				 " nuova scopa con la carta: " + cardPlayed.toString());
		newScopa = true;
		}
		if(newScopa == true && lastScopa != cardPlayed) {
			newScopa = false;
		}
		
	}

	public ArrayList<TeamPanel> getTeam1Panels() {
		return team1Panels;
	}

	public ArrayList<TeamPanel> getTeam2Panels() {
		return team2Panels;
	}

	@Override
	public void multipleChoice(HumanPlayerHandler humanPlayerHandler, ArrayList<ArrayList<Card>> choices) {
		// TODO Auto-generated method stub
		OptionsPopUp op = new OptionsPopUp(choices);
		tableFrame = new JFrame();
		tableFrame.setSize(700, 400);
		tableFrame.add(tablePanel.get(tablePanel.size()-1));
		tableFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tableFrame.setResizable(false);
		tableFrame.setVisible(true);
		op.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameController.endTurn(humanPlayerHandler,(ArrayList<Card>) op.getComboBox().getSelectedItem());
				tableFrame.dispose();
				op.dispose();
			}
		});
	}

	@Override
	public void lock(HumanPlayerHandler humanPlayerHandler) {
		// TODO Auto-generated method stub
		playerPanels.get(humanPlayerHandler).lockPlayer();
		
	}

	@Override
	public void unlock(HumanPlayerHandler humanPlayerHandler) {
		// TODO Auto-generated method stub
		playerPanels.get(humanPlayerHandler).unlockPlayer();
	}


}
