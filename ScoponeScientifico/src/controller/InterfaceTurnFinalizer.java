package controller;

import model.Team;

public interface InterfaceTurnFinalizer {
	// viene chiamato quando deve esser finita una mano
	public void newHand();
	
	//viene chiamato quando finisce la partita
	public void gameFinished(Team winnerTeam);
}
