package graphicInterfaceController;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import CardTest.*;
import controller.GameController;
import controller.HumanPlayerHandler;
import controller.HumanPlayerInterfaceController;
import controller.InterfaceTurnFinalizer;
import graphicInterface.CardListener;
import graphicInterface.FinishFrame;
import graphicInterface.HistoryFrame;
import graphicInterface.OptionsPopUp;
import graphicInterface.PlayerPanel;
import graphicInterface.StartFrame;
import graphicInterface.TablePanel;
import graphicInterface.TeamPanel;
import graphicInterface.TotalFrame;
import model.Card;
import model.Team;
import utility.CardConverter;
import utility.TableObserver;

public class GUIController implements TableObserver, HumanPlayerInterfaceController, InterfaceTurnFinalizer {
	private GameController gameController;
	private ArrayList<TotalFrame> playerView = new ArrayList<TotalFrame>();
	private ArrayList<TablePanel> tablePanel = new ArrayList<TablePanel>();
	private ArrayList<TeamPanel> team1Panels = new ArrayList<TeamPanel>();
	private ArrayList<TeamPanel> team2Panels = new ArrayList<TeamPanel>();
	private ArrayList<HumanPlayerHandler> players = new ArrayList<HumanPlayerHandler>();

	private JFrame tableFrame; // frame per visualizzare il tavolo durante la scelta delle carte

	// hashmap che associa playerpanel al rispettivo playerhandler
	private HashMap<HumanPlayerHandler, PlayerPanel> playerPanels = new HashMap<HumanPlayerHandler, PlayerPanel>();

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

	public void startGame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void init(ArrayList<HumanPlayerHandler> playerHandlers, GameController gameController) {
		this.gameController = gameController;
		gameController.setTurnFinalizer(this);

		int i = 0;
		historyFrame = new HistoryFrame();
		gameController.getHistory().setOutput(historyFrame);
		TotalFrame totalFrame;
		PlayerPanel playerPanel;
		ArrayList<CardLabel> playerCards;

		for (HumanPlayerHandler playerHandler : playerHandlers) {
			playerHandler.setInterfaceController(this);
			playerCards = cardsConverter(playerHandler.getPlayer().getHand());
			playerPanel = new PlayerPanel(playerCards);
			players.add(playerHandler);
			playerPanels.put(playerHandler, playerPanel);
			for (CardLabel cardLabel : playerCards) {

				cardLabel.addMouseListener(new CardListener(cardLabel, playerHandler, playerPanel));

			}
			// String playerNameAndTeam = playerHandler.getPlayer().getPlayerName() +"--"+
			// playerHandler.getPlayer().getTeam().getTeamName();

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
		// i++;
		// howManyTablePanels = i;
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
		lock(humanPlayerHandler);
		OptionsPopUp op = new OptionsPopUp(choices);
		tableFrame = new JFrame();
		tableFrame.setSize(700, 400);
		tableFrame.add(tablePanel.get(tablePanel.size() - 1));
		tableFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tableFrame.setResizable(false);
		tableFrame.setVisible(true);
		op.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.endTurn(humanPlayerHandler, (ArrayList<Card>) op.getComboBox().getSelectedItem());
				tableFrame.dispose();
				op.dispose();
			}
		});
	}

	@Override
	public void lock(HumanPlayerHandler humanPlayerHandler) {
		playerPanels.get(humanPlayerHandler).lockPlayer();

	}

	@Override
	public void unlock(HumanPlayerHandler humanPlayerHandler) {
		playerPanels.get(humanPlayerHandler).unlockPlayer();
	}

	@Override
	public void newHand() {
		// TODO Auto-generated method stub
		ArrayList<CardLabel> playerCards = new ArrayList<CardLabel>();
		for (HumanPlayerHandler humanPlayerHandler : players) {
			playerCards = cardsConverter(humanPlayerHandler.getPlayer().getHand());
			playerPanels.get(humanPlayerHandler).setCards(playerCards);

		}
		for (TotalFrame totalFrame : playerView) {
			totalFrame.getTeamsPanel().getTeam1().removeAll();
			totalFrame.getTeamsPanel().getTeam2().removeAll();
			totalFrame.repaint();
			totalFrame.validate();

		}
	}

	@Override
	public void gameFinished(Team winnerTeam) {
		// TODO Auto-generated method stub
		for (TotalFrame totalFrame : playerView) {
			totalFrame.getPlayerPanel().removeAll();
			totalFrame.getTeamsPanel().getTeam1().removeAll();
			totalFrame.getTeamsPanel().getTeam2().removeAll();
			totalFrame.getTablePanel().removeAll();
			totalFrame.dispose();
		}
		playerView.clear();
		playerPanels.clear();
		players.clear();
		tablePanel.clear();
		team1Panels.clear();
		team2Panels.clear();
		historyFrame.dispose();
		FinishFrame finishFrame = new FinishFrame(winnerTeam.getTeamName());
		finishFrame.getOk().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (finishFrame.getChoose()) {
					finishFrame.dispose();
					GUIController.getDefaultGUIController().startGame();

				} else {
					finishFrame.dispose();
					System.exit(0);
				}
			}
		});
	}

	public static void main(String[] args) {
		GUIController.getDefaultGUIController().startGame();
	}

}
