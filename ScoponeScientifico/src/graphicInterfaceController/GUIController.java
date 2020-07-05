package graphicInterfaceController;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import CardTest.*;

import controller.GameController;
import controller.HumanPlayerHandler;
import controller.HumanPlayerInterfaceController;
import controller.InterfaceController;
import controller.InterfaceTurnFinalizer;
import exception.CardNotFoundException;
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
import utility.TeamObserver;

public class GUIController implements HumanPlayerInterfaceController, InterfaceTurnFinalizer, InterfaceController {
	private GameController gameController;
//	private ArrayList<TotalFrame> playerView = new ArrayList<TotalFrame>();
	private HashMap<HumanPlayerHandler, TotalFrame> playerView = new HashMap<HumanPlayerHandler, TotalFrame>();
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

	public void startGame(String message) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame messageFrame = new JFrame();
					JOptionPane.showMessageDialog(messageFrame, message, "Warning", JOptionPane.WARNING_MESSAGE);
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

		for (HumanPlayerHandler playerHandler : playerHandlers) {
			playerHandler.setInterfaceController(this);
			players.add(playerHandler);
			playerPanel = new PlayerPanel();
			playerPanels.put(playerHandler, playerPanel);

			tablePanel.add(new TablePanel());
			team1Panels.add(new TeamPanel(1));
			team2Panels.add(new TeamPanel(2));
			totalFrame = new TotalFrame(playerHandler.getPlayer().getPlayerName(), tablePanel.get(i), playerPanel,
					team1Panels.get(i), team2Panels.get(i));
			playerPanel.setTotalFrame(totalFrame);
			playerView.put(playerHandler,totalFrame);
			i++;
			// playerView[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		initPlayerCards(players);
		tablePanel.add(new TablePanel());
		// i++;
		// howManyTablePanels = i;
		// playerView.get(0).unlockPlayer();
		addTeamObserver();
	}

	/**
	 * @param playerHandlers
	 * 
	 *                       Si occupa di inizializzare i playerPanel con le carte
	 *                       del Player per ogni Player
	 */
	private void initPlayerCards(ArrayList<HumanPlayerHandler> playerHandlers) {
		ArrayList<CardLabel> playerCards;
		PlayerPanel playerPanel;
		for (HumanPlayerHandler playerHandler : playerHandlers) {
			playerCards = cardsConverter(playerHandler.getPlayer().getHand());
			playerPanel = this.playerPanels.get(playerHandler);
			for (CardLabel cardLabel : playerCards) {
				cardLabel.addMouseListener(new CardListener(cardLabel, playerHandler, playerPanel));
			}
			playerPanel.setCards(playerCards);
		}
	}

	private void addTeamObserver() {
		gameController.addTeamObserver(getTeam1Observer(), getTeam2Observer());
	}

	private ArrayList<CardLabel> cardsConverter(List<Card> cards) {
		ArrayList<CardLabel> cardLabels = new ArrayList<CardLabel>();
		for (Card card : cards) {
			cardLabels.add(CardConverter.toCardLabel(card));
		}
		return cardLabels;
	}

	public HashMap<HumanPlayerHandler, TotalFrame> getPlayerView() {
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
		for (TotalFrame totalFrame : playerView.values()) {

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
		for (TotalFrame totalFrame : playerView.values()) {

			totalFrame.repaint();
			totalFrame.validate();
			i++;
		}

	}

	public ArrayList<TeamObserver> getTeam1Observer() {
		ArrayList<TeamObserver> team1Observers = new ArrayList<TeamObserver>();
		for (TeamPanel t : getTeam1Panels()) {
			team1Observers.add(t);
		}
		return team1Observers;
	}

	public ArrayList<TeamObserver> getTeam2Observer() {
		ArrayList<TeamObserver> team2Observers = new ArrayList<TeamObserver>();
		for (TeamPanel t : getTeam2Panels()) {
			team2Observers.add(t);
		}
		return team2Observers;
	}

	public ArrayList<TeamPanel> getTeam1Panels() {
		return team1Panels;
	}

	public ArrayList<TeamPanel> getTeam2Panels() {
		return team2Panels;
	}
	public ArrayList<HumanPlayerHandler> getPlayers() {
		return players;
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
				try {
					gameController.endTurn(humanPlayerHandler, (ArrayList<Card>) op.getComboBox().getSelectedItem());
				} catch (CardNotFoundException c) {
					System.out.println("Carta non trovata!");
				}
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

		initPlayerCards(this.players);
		for (TotalFrame totalFrame : playerView) {
			totalFrame.getTeamsPanel().getTeam1().clear();
			totalFrame.getTeamsPanel().getTeam2().clear();

			totalFrame.repaint();
			totalFrame.validate();
		}
	}

	@Override
	public void gameFinished(Team winnerTeam) {
		// TODO Auto-generated method stub
		for (TotalFrame totalFrame : playerView.values()) {
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
