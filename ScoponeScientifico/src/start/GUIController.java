package start;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.GameController;
import controller.HumanPlayerHandler;
import controller.HumanPlayerInterfaceController;
import controller.InterfaceController;
import controller.InterfaceTurnFinalizer;
import exception.CardNotFoundException;
import graphicInterface.CardLabel;
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

/**
 * This class manages the graphic interface
 *
 */
public class GUIController implements HumanPlayerInterfaceController, InterfaceTurnFinalizer, InterfaceController {
	private GameController gameController;
	private HashMap<HumanPlayerHandler, TotalFrame> playerView = new HashMap<HumanPlayerHandler, TotalFrame>();
	private ArrayList<TablePanel> tablePanel = new ArrayList<TablePanel>();
	private ArrayList<TeamPanel> team1Panels = new ArrayList<TeamPanel>();
	private ArrayList<TeamPanel> team2Panels = new ArrayList<TeamPanel>();
	private ArrayList<HumanPlayerHandler> players = new ArrayList<HumanPlayerHandler>();
	private JFrame tableFrame;
	private HashMap<HumanPlayerHandler, PlayerPanel> playerPanels = new HashMap<HumanPlayerHandler, PlayerPanel>();
	private HistoryFrame historyFrame;
	private static GUIController defaultGuiController = null;

	/**
	 * Initialize the GUIController
	 * 
	 * @return defoultGUIController
	 */
	public static GUIController getDefaultGUIController() {
		if (defaultGuiController == null) {
			defaultGuiController = new GUIController();
			return defaultGuiController;
		} else
			return defaultGuiController;
	}

	private GUIController() {

	}

	/**
	 * Makes the start window to initialize the players number and the type of
	 * players
	 */
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

	/**
	 * Overload of the previous method, it is called only if the user tries to play
	 * with only ComputerPlayers
	 * 
	 * @param message String with warning massage that explain the user have to
	 *                choose almost one human player
	 */
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

	/**
	 * Generates totalFrame and the relative playerPanel, tablePlanel, teamPanel,
	 * historyFrame
	 * 
	 * @param playerHandles  ArrayList of HumanPlayerHandler created by
	 *                       GameStartSetUp
	 * @param gameController GameController witch implements TurnFinalizer
	 */
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
			playerView.put(playerHandler, totalFrame);
			i++;
		}
		initPlayerCards(players);
		tablePanel.add(new TablePanel());
		addTeamObserver();
	}

	/**
	 * Initializes the playerPanel with in the player cards for each player
	 * 
	 * @param playerHandlers ArrayList of HumanPlayerHandles created by
	 *                       GameStartSetUp
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

	/**
	 * Add the observer to the teams
	 */
	private void addTeamObserver() {
		gameController.addTeamObserver(getTeam1Observer(), getTeam2Observer());
	}

	/**
	 * Converts the model card into cardLabels for the graphic interface
	 * 
	 * @param cards List of Card
	 * @return cardLabels ArrayList of CardLabel
	 */
	private ArrayList<CardLabel> cardsConverter(List<Card> cards) {
		ArrayList<CardLabel> cardLabels = new ArrayList<CardLabel>();
		for (Card card : cards) {
			cardLabels.add(CardConverter.toCardLabel(card));
		}
		return cardLabels;
	}

	/**
	 * @return playerView HashMap of HumanPlayerHandler
	 */
	public HashMap<HumanPlayerHandler, TotalFrame> getPlayerView() {
		return playerView;
	}

	/**
	 * Updates the table adding the card played by the players
	 * 
	 * @param c Card played by the player
	 */
	@Override
	public void updateOnAddition(Card c) {
		for (TablePanel tablePanel : tablePanel) {
			tablePanel.putCardOnTable(CardConverter.toCardLabel(c));
			tablePanel.repaint();
			tablePanel.validate();

		}
		for (TotalFrame totalFrame : playerView.values()) {

			totalFrame.repaint();
			totalFrame.validate();
		}
	}

	/**
	 * Update the table removing the card taken
	 * 
	 * @param removedCard List of card
	 */
	@Override
	public void updateOnRemoval(List<Card> removedCards) {

		for (TablePanel tablePanel : tablePanel) {
			tablePanel.removeCardsFromTable(cardsConverter(removedCards));
			tablePanel.repaint();
			tablePanel.validate();

		}
		for (TotalFrame totalFrame : playerView.values()) {

			totalFrame.repaint();
			totalFrame.validate();
		}

	}

	/**
	 * 
	 * @return team1Observers ArrayList of TeamObserve
	 */
	public ArrayList<TeamObserver> getTeam1Observer() {
		ArrayList<TeamObserver> team1Observers = new ArrayList<TeamObserver>();
		for (TeamPanel t : getTeam1Panels()) {
			team1Observers.add(t);
		}
		return team1Observers;
	}

	/**
	 * 
	 * @return team2Observers ArrayList of TeamObserve
	 */
	public ArrayList<TeamObserver> getTeam2Observer() {
		ArrayList<TeamObserver> team2Observers = new ArrayList<TeamObserver>();
		for (TeamPanel t : getTeam2Panels()) {
			team2Observers.add(t);
		}
		return team2Observers;
	}

	/**
	 * 
	 * @return team1Panels ArrayList of TeamPalen
	 */
	public ArrayList<TeamPanel> getTeam1Panels() {
		return team1Panels;
	}

	/**
	 * 
	 * @return team2Panels ArrayList of TeamPalen
	 */
	public ArrayList<TeamPanel> getTeam2Panels() {
		return team2Panels;
	}

	/**
	 * 
	 * @return players ArrayList of HumanPlayerHandler
	 */
	public ArrayList<HumanPlayerHandler> getPlayers() {
		return players;
	}

	/**
	 * Generates a window if the are multiple possibility to take a card in the
	 * table
	 * 
	 * @param humanPlayerHandler HumanPlayerHandler that has played the card
	 * @param choices            ArrayList of the different choice
	 */
	@Override
	public void multipleChoice(HumanPlayerHandler humanPlayerHandler, ArrayList<ArrayList<Card>> choices) {
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

	/**
	 * Set not enable the player frame who pass the turn
	 * 
	 * @param humanPlayerHandler HumanPlayerHandler who has to be disabled
	 */
	@Override
	public void lock(HumanPlayerHandler humanPlayerHandler) {
		playerPanels.get(humanPlayerHandler).lockPlayer();

	}

	/**
	 * Set enabled the player who has the turn
	 * 
	 * @param humanPlayerHandler HumanPlayerHandler who has to be enabled
	 */
	@Override
	public void unlock(HumanPlayerHandler humanPlayerHandler) {
		playerPanels.get(humanPlayerHandler).unlockPlayer();
	}

	/**
	 * Initializes the new frame and panels for the new hand
	 */
	@Override
	public void newHand() {

		initPlayerCards(this.players);
		for (TotalFrame totalFrame : playerView.values()) {
			totalFrame.getTeamsPanel().getTeam1().clear();
			totalFrame.getTeamsPanel().getTeam2().clear();

			totalFrame.repaint();
			totalFrame.validate();
		}
	}

	/**
	 * Ended the game and calculates the team winner
	 * 
	 * @param winnerTeam Team witch wins the game
	 */
	@Override
	public void gameFinished(Team winnerTeam) {
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
		FinishFrame finishFrame = new FinishFrame(winnerTeam);
		finishFrame.getOk().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
