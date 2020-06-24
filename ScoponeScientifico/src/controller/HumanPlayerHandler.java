package controller;

import java.util.ArrayList;

import javax.swing.JPanel;

import CardTest.CardLabel;
import graphicInterface.PlayerPanel;
import model.Card;
import model.Player;
import utility.CardConverter;

public class HumanPlayerHandler extends AbstractPlayerHandler {
	
	//private PlayerPanel playerPanel;
	private HumanPlayerInterfaceController interfaceController = null;
	

	public HumanPlayerHandler(Player player, GameController controller) {
		super(player, controller);		
	}
	
	/*
	 * Magari può essere utile referenziare immediatamente l'interfaccia grafica/testuale
	 */
	public HumanPlayerHandler(Player player, GameController controller, HumanPlayerInterfaceController i) {
		this(player, controller);
		this.interfaceController = i;	
	}

	@Deprecated
	//va cancellato quando siamo sicuri che non serve più ad un accidenti
	public void cardPlayed(CardLabel cardLabel) {
		Card playedCard = CardConverter.toModelCard(cardLabel);
		setPlayedCard(playedCard);
		getController().hasPlayed(this);
	}
	
	public void cardPlayed(Card c) {
		super.cardPlayed(c);
		setResultFromFetch(getController().fetchCard(c));
		
		//faccio il controllo di quello che ho fetchato
		if(getResultFromFetch().size() > 1) {
			/*
			 * passa il controllo all'interfaccia
			 * l'interfaccia deve sapere da che giocatore arriva la scelta multipla, perchè quando il turno viene concluso
			 * bisogna sapere chi ha concluso il turno.
			 * - Andrea
			*/
		}
		else {
			getController().endTurn(this);
		}
		
	}
	
	@Override
	public boolean unlockPlayer() {
		interfaceController.unlock(this);
		return true;
	}
	
	
	@Override
	public boolean lockPlayer() {
		//this.playerPanel.lockPlayer();
		interfaceController.lock(this);
		return true;
	}
	

	@Override
	public void multipleChoice(ArrayList<ArrayList<Card>> choices) {
		interfaceController.multipleChoice(this,choices);
		
	}

	
	public void setInterfaceController(HumanPlayerInterfaceController i) {
		this.interfaceController = i;
	}


}
