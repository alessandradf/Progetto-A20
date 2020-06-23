package controller;

import java.util.ArrayList;

import exception.MultipleChoiceException;
import graphicInterfaceController.GUIController;
import model.Card;
import model.Game;
import model.Team;
import utility.CircularArrayList;

public class GameController {

	private Game game;
	private CircularArrayList<AbstractPlayerHandler> players;
	
	public GameController(CircularArrayList<AbstractPlayerHandler> players) {
		this.game = Game.getDefaultGame();
		this.players = players;
	}

	public void init() {
		ArrayList<Team> teams = game.getTeams();
		for(AbstractPlayerHandler p : players) {
			p.lockPlayer();	
		}
		/*
		 *	Questo pezzo associa il team al giocatore e viceversa.
		 * 	Fatto così fa schifo, ma mi serve per evitare dei NullPointerException.
		 *	Considerare di spostarlo dentro al Game nella versione finale
		 *	Ad essere onesti non è nemmeno necessario che i team conoscano i giocatori. -Andrea
		 */
		teams.get(0).addPlayer(players.get(0).getPlayer());
		players.get(0).getPlayer().setTeam(teams.get(0));
		
		teams.get(0).addPlayer(players.get(2).getPlayer());
		players.get(2).getPlayer().setTeam(teams.get(0));
		
		teams.get(1).addPlayer(players.get(1).getPlayer());
		players.get(1).getPlayer().setTeam(teams.get(1));
		
		teams.get(1).addPlayer(players.get(3).getPlayer());
		players.get(3).getPlayer().setTeam(teams.get(1));
		
		players.get(0).unlockPlayer();
	}
	
	private void nextPlayer() {
		AbstractPlayerHandler next_player;
		next_player = players.getNext();
		next_player.unlockPlayer();

	}

	public void hasPlayed(AbstractPlayerHandler p) {

		Card card = p.getPlayedCard();			
		try {
			GUIController.getDefaultGUIController().updateHistory(p, card);
			game.playRound(p.getPlayer(), card);			
			p.lockPlayer();
			nextPlayer();
			
		} catch (MultipleChoiceException e) {
			p.multipleChoice(e.getChoices());			
		}			
	}
	
	/*
	 * Deve controllare che cosa succede se viene giocata la carta che viene passata, e restituire le eventuali prese
	 * null se non viene preso nulla 
	 */
	public ArrayList<ArrayList<Card>> fetchCard(Card c) {
		return null;
	}
	/*
	 * Idea su come finalizzare il turno...
	 */
	public void endTurn(AbstractPlayerHandler p, ArrayList<Card> choiceMade) {
		//game.endTurn(choiceMade);
		p.lockPlayer();
		nextPlayer();
	}
	
	/*
	 * finalizza il turno MA senza modificare nulla del dominio, utile quando non si prendono delle carte dal tavolo
	 */
	//è protetto perchè va chiamato solo dagli AbstractPlayerHandler
	protected void endTurn(AbstractPlayerHandler player) {
		player.lockPlayer();
		nextPlayer();
	}	
	
	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @return the players
	 */
	public CircularArrayList<AbstractPlayerHandler> getPlayers() {
		return players;
	}
	
	
	
	
}
