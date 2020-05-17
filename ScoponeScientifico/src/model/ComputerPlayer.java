package model;

import java.util.Random;

public class ComputerPlayer extends Player {
	
	Random random;

public ComputerPlayer() {
		this("DefaultName");
	}

	public ComputerPlayer(String name) {
		super(name);
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
