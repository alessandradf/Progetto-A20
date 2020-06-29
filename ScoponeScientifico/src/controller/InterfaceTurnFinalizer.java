package controller;

import model.Team;

public interface InterfaceTurnFinalizer {
	public void newHand();
	public void gameFinished(Team winnerTeam);
}
