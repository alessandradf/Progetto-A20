package controller;

import java.util.ArrayList;

import model.Card;

public interface HumanPlayerInterfaceController {
	
	public void multipleChoice(HumanPlayerHandler humanPlayerHandler, ArrayList<ArrayList<Card>> choices);
	public void lock(HumanPlayerHandler humanPlayerHandler);
	public void unlock(HumanPlayerHandler humanPlayerHandler);
}
