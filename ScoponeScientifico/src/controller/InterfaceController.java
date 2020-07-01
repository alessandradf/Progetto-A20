package controller;

import java.util.ArrayList;

import utility.TableObserver;
import utility.TeamObserver;

public interface InterfaceController extends TableObserver {

	//qui ci vanno tutti i metodi in comune tra GUIController e TUIController
	public void startGame();
	public void init(ArrayList<HumanPlayerHandler> playerHandlers, GameController gameController);
	
}
