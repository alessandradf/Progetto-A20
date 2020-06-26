package controller;

import java.util.ArrayList;

import model.Card;
import utility.OutputInterface;

public class HistoryClass {
	private OutputInterface output;

	public HistoryClass() {
		// TODO Auto-generated constructor stub
	}

	public void setOutput(OutputInterface output) {

		this.output = output;

	}
	public void updateHistory(AbstractPlayerHandler playerHandler, Card cardPlayed) {

		output.writeOnOutput("<html><br>" + playerHandler.getPlayer().getPlayerName() + " ha giocato la carta: "
				+ cardPlayed.toString());
	}
	
	public void updateHistory(ArrayList<Card> cardsTaken) {

		output.writeOnOutput("<html><br>" 
				+ " sono state prese le seguenti carte: " + cardsTaken.toString());
	}
	public void updateHistoryScopa(Card cardPlayed) {
		
		
		output.writeOnOutput("<html><br>" + 
				 " nuova scopa con la carta: " + cardPlayed.toString());
	
	}


	
	

}
