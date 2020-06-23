package controller;

import java.util.ArrayList;

import model.Card;

public interface HumanPlayerInterfaceController {
	
	public void multipleChoice(ArrayList<ArrayList<Card>> choices);
	public void lock();
	public void unlock();
}
