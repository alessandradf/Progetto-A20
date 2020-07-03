package controller;

import java.util.ArrayList;

import exception.CardNotFoundException;
import model.Card;
import model.Player;

public class HumanPlayerHandler extends AbstractPlayerHandler {

	// private PlayerPanel playerPanel;
	private HumanPlayerInterfaceController interfaceController = null;

	public HumanPlayerHandler(Player player, GameController controller) {
		super(player, controller);
	}

	/*
	 * Magari può essere utile referenziare immediatamente l'interfaccia
	 * grafica/testuale
	 */
	public HumanPlayerHandler(Player player, GameController controller, HumanPlayerInterfaceController i) {
		this(player, controller);
		this.interfaceController = i;
	}

	public void cardPlayed(Card c) throws CardNotFoundException{
		boolean isValid = false;
		if(super.getPlayer().getHand().contains(c)) {
			
			isValid = true;
		}
		if(!isValid ) {
			throw new CardNotFoundException();
		}
		super.cardPlayed(c);
		if (getResultFromFetch().size() > 1) {
			//passa il controllo all'interfaccia, per scegliere le carte da prendere
			multipleChoice(getResultFromFetch());
		} 
	}

	@Override
	public boolean unlockPlayer() {
		interfaceController.unlock(this);
		return true;
	}

	@Override
	public boolean lockPlayer() {
		// this.playerPanel.lockPlayer();
		interfaceController.lock(this);
		return true;
	}

	@Override
	public void multipleChoice(ArrayList<ArrayList<Card>> choices) {
		interfaceController.multipleChoice(this, choices);

	}

	public void setInterfaceController(HumanPlayerInterfaceController i) {
		this.interfaceController = i;
	}

}
