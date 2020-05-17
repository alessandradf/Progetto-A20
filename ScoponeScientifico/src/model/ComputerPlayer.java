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

	public Card playCard() {
		int index = random.nextInt(getHand().size());
		Card card = getHand().get(index);
		getHand().remove(index);
		return card;
	}

}
