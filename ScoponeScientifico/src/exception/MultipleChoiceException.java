package exception;

import java.util.ArrayList;

import model.Card;

/*
 * Serve a gestire la situazione in cui il giocatore pu� scegliere quale combinazione di carte prendere
 */

/*
 * La scelta multipla non va gestita con l'eccezione. La classe sarà rimossa quando la scelta multipla sarà gestita corretaamente
 * dalle classi Game, GameController e AbstractPlayerHandler
 */
@Deprecated
public class MultipleChoiceException extends Exception {
	
	private ArrayList<ArrayList<Card>> choices;		//contiene le combinazioni di carte che possono essere scelte
	
	public MultipleChoiceException(ArrayList<ArrayList<Card>> choices) {
		this.choices = choices;
	}
	
	public ArrayList<ArrayList<Card>> getChoices(){
		return this.choices;
	}
	
}
