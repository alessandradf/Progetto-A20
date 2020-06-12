package exception;

import java.util.ArrayList;

import model.Card;

/*
 * Serve a gestire la situazione in cui il giocatore può scegliere quale combinazione di carte prendere
 */
public class MultipleChoiceException extends Exception {
	
	private ArrayList<ArrayList<Card>> choices;		//contiene le combinazioni di carte che possono essere scelte
	
	public MultipleChoiceException(ArrayList<ArrayList<Card>> choices) {
		this.choices = choices;
	}
	
	public ArrayList<ArrayList<Card>> getChoices(){
		return this.choices;
	}
	
}
