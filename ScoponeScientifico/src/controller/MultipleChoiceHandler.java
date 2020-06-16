package controller;

import java.util.ArrayList;

import model.Card;
import model.Game;
import model.Table;
import graphicInterfaceController.GUIController;

/*
* Implementa tre interfacce, di modo che i tre differenti oggetti che usano il MultipleChoiceHandler lo vedano differentemente
* HumanPlayerHandler vede solo il metodo HumanMultipleChoice
* ComputerPlayerHandler solo ComputerMultipleChoice
* GUIController solo choiceMade
*/
public class MultipleChoiceHandler implements HumanMultipleChoiceHandler, ComputerMultipleChoiceHandler, ChoiceReceiver{
	
	
	private GameController controller;
	private GUIController defaultGUIController;
	private AbstractPlayerHandler player;
	private ArrayList<ArrayList<Card>> choices;
	private Table table;
	
	public MultipleChoiceHandler(GameController controller, AbstractPlayerHandler player) {
		this.controller = controller;
		this.player = player;
		defaultGUIController = GUIController.getDefaultGUIController();
		table = Game.getDefaultGame().getDefaultTable();
		//createInterface();
		
	}
	
	@Override
	public void humanMultipleChoice(ArrayList<ArrayList<Card>> choices) {
		this.choices = choices;
		createInterface();
	}
	
	@Override
	public void computerMultipleChoice(ArrayList<ArrayList<Card>> choices) {
		this.choices = choices;
		choiceMade(choices.get(0));
	}
	
	
	private void createInterface() {
		//defaultGUIController.qualcosa;
	}
	
	@Override
	public void choiceMade(ArrayList<Card> choice) {
		table.removeCardsFromTable(choice);
		player.getPlayer().getTeam().addCards(choice);
		controller.multipleChoiceperformed(player);
	}

	/**
	 * @return the choices
	 */
	public ArrayList<ArrayList<Card>> getChoices() {
		return choices;
	}

	
	
}
