package model;

import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

	Random random;

	public ComputerPlayer() {
		this.random = new Random();
	}

	@Override
	public Card playCard() {
		int index = random.nextInt(hand.size());
		Card card = hand.get(index);
		hand.remove(index);
		return card;
	}

}
