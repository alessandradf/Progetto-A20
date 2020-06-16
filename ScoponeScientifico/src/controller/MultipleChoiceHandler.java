package controller;

import java.util.ArrayList;

import model.Card;
import model.Table;
import graphicInterfaceController.GUIController;

public class MultipleChoiceHandler {
	
	
	private GameController _controller;
	private GUIController defaultGUIController;
	private AbstractPlayerHandler _player;
	private ArrayList<ArrayList<Card>> _result;
	private Table _table;
	
	public MultipleChoiceHandler(GameController controller, AbstractPlayerHandler player, ArrayList<ArrayList<Card>> result, Table table) {
		_controller = controller;
		_player = player;
		_result = result;
		_table = table;
		defaultGUIController = GUIController.getDefaultGUIController();
		//createInterface();
		
	}
	
	
	private void createInterface() {
		//defaultGUIController.qualcosa;
	}
	
	public void choiceMade(ArrayList<Card> choice) {
		_table.removeCardsFromTable(choice);
		_player.getPlayer().getTeam().addCards(choice);
		_controller.multipleChoiceperformed(_player);
	}

}
