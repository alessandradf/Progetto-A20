package controller;

import java.util.ArrayList;

import model.Card;
import utility.OutputInterface;

public class History {
	private OutputInterface output;

	public History() {
	}

	public void setOutput(OutputInterface output) {
		this.output = output;
	}

	public void updateHistory(AbstractPlayerHandler playerHandler, Card cardPlayed) {
		output.writeOnOutput(
				playerHandler.getPlayer().getPlayerName() + "\n ha giocato la carta: " + cardPlayed.toString());
	}

	public void updateHistory(ArrayList<Card> cardsTaken) {
		output.writeOnOutput("\n sono state prese le seguenti carte: " + cardsTaken.toString());
	}

	public void updateHistoryScopa(Card cardPlayed) {
		output.writeOnOutput("\n nuova scopa con la carta: " + cardPlayed.toString());
	}

}
