package utility;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.Player;

/**
 * This class generates entries, which are description of what happens in the model, and writes them on the {@link OutputInterface}.
 * It's notified everytime a change occurs in {@link Table}, {@link Team} or {@link Player}
 */
public class History implements TeamObserver, TableObserver, PlayerObserver {
	
	private OutputInterface output;		// the generic output where the entries are written

	private StringBuilder entry;	// contains the String to be written
	
	/**
	 * Initialize the History. An empty {@link StringBuilder} is created
	 */
	public History() {
		this.entry = new StringBuilder();
	}

	/**
	 * Sets the {@link OutputInterface} where the entries will be written
	 * @param output the {@link OutputInterface}
	 */
	public void setOutput(OutputInterface output) {
		this.output = output;
	}
	
	/**
	 * Writes the entry on the {@link OutputInterface} and creates a new empty {@link StringBuilder}
	 */
	public void writeOnOutput() {
		this.output.writeOnOutput(this.entry.toString());
		this.entry = new StringBuilder();
	}

	
	/**
	 * Generates a description when a {@link Player} playes a {@link Card}.
	 * The description is added to the entry
	 */
	@Override
	public void updateOnPlayedCard(Player p, Card playedCard) {
		String s = p.getPlayerName() + " ha giocato la carta " + playedCard.toString();
		this.entry.append(s);
	}

	/**
	 * This method is empty. The History is not updated when a {@link Card} is put on the {@link Table}
	 */
	@Override
	public void updateOnAddition(Card c) {
	}

	/**
	 * Generates a description when a {@link List} of {@link Card} is removed from the {@link Table}
	 * The description is added to the entry
	 */
	@Override
	public void updateOnRemoval(List<Card> toRemove) {
		String s = "\ne ha preso le seguenti carte : \n[ ";
		for (Card c : toRemove) {
			s += c.toString() + " ";
		}
		s += "]";
		
		this.entry.append(s);
	}

	
	/**
	 * This method is empty. The history is not updated when the score is modified
	 */
	@Override
	public void updateScore(int score, int lastHandScore) {
	}

	
	/**
	 * Generates a description when a scopa is made.
	 * The description is added to the entry
	 */
	@Override
	public void scopa(Card scopaCard) {
		String s = "\nE' stata fatta la seguente scopa: " + scopaCard.toString();
		this.entry.append(s);
	}
	
	/**
	 * Generates a description when the hand is terminated
	 * @param lastToTake the last player who took cards from the table
	 * @param lastCards last cards remaining on the table
	 */
	public void finalizeHand(Player lastToTake, ArrayList<Card> lastCards) {
		this.entry = new StringBuilder();		// creates a new StringBuilder so the entry is not incomplete
		String s = "Le carte rimanenti sul tavolo sono\n state prese da " + lastToTake.getPlayerName();
		
		s += "\n[ ";
		
		for(Card c : lastCards) {
			s += c.toString() + " ";
		}
		s += " ]";
		
		this.entry.append(s);
	}

}
