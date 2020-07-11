package graphicInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import controller.GameStartSetup;
import controller.HumanPlayerHandler;
import graphicInterfaceController.GUIController;

/**
 * This listener instantiates the GameStartSetUp through the user's choices made
 * in the StartFrame
 *
 */
public class StartGameBottonListener implements ActionListener {

	private JComboBox[] comboBox;
	private JComboBox maxScore;
	private StartFrame frame;
	private JTextField[] textField;

	/**
	 * Initializes the listener with the StartFrame's elements
	 * 
	 * @param comboBox  array with the player type's comboBox
	 * @param maxScore  comboBox with the maximum score's choice
	 * @param textField array of textField with the names of the players
	 * @param frame     Start frame
	 */
	public StartGameBottonListener(JComboBox[] comboBox, JComboBox maxScore, JTextField[] textField, StartFrame frame) {
		this.comboBox = comboBox;
		this.maxScore = maxScore;
		this.textField = textField;
		this.frame = frame;
	}

	/**
	 * Instantiates GameStartSetUp with the type of players, their names and the
	 * maximum score
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] config = new String[4];
		ArrayList<String> playerNames = new ArrayList<String>();
		for (int i = 0; i < comboBox.length; i++) {
			if (comboBox[i].getSelectedItem().toString().equals("Human Player")) {
				frame.setHumanPlayers(frame.getHumanPlayers() + 1);
				config[i] = "Human";
				playerNames.add(textField[i].getText());
			} else {
				frame.setComputerPlayers(frame.getComputerPlayers() + 1);
				config[i] = "Computer";
				playerNames.add(null);
			}
		}
		frame.setVisible(false);
		GameStartSetup g;

		if (maxScore.getSelectedItem().toString().equals("Punteggio")) {
			g = new GameStartSetup(config, GUIController.getDefaultGUIController(), playerNames, 5);
			// g.getGameController().getGame().setMaxScore(5);
		} else {
			g = new GameStartSetup(config, GUIController.getDefaultGUIController(), playerNames,
					Integer.parseInt(maxScore.getSelectedItem().toString()));

		}
		for (HumanPlayerHandler humanPlayerHandler : GUIController.getDefaultGUIController().getPlayers()) {
			GUIController.getDefaultGUIController().getPlayerView().get(humanPlayerHandler)
					.setTitle(humanPlayerHandler.getPlayer().getPlayerName() + " "
							+ humanPlayerHandler.getPlayer().getTeam().getTeamName());
		}
	}

}
