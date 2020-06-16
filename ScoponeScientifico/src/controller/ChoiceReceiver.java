package controller;

import java.util.ArrayList;

import model.Card;

public interface ChoiceReceiver {
	public void choiceMade(ArrayList<Card> choice);
}
