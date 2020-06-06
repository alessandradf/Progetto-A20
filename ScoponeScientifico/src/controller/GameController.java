package controller;

import model.Card;
import model.Game;
import utility.CircularArrayList;

public class GameController {

	private Game game;
	private CircularArrayList<AbstractPlayerHandler> players;
	
	public GameController(CircularArrayList<AbstractPlayerHandler> players) {
		this.game = Game.getDefaultGame();
		this.players = players;
	}

	private void nextPlayer() {
		AbstractPlayerHandler next_player;
		next_player = players.getNext();
		next_player.unlockPlayer();

	}

	public void hasPlayed(AbstractPlayerHandler p) {
		Card card = p.getPlayedCard();
		game.playRound(p.getPlayer(), card);
		// o comunque qualcosa del genere //
		//p.lockPlayer();	// questa serve a fare in modo che i giocatori umani non si mettano a schiacciare carte a caso 
						    // quando non è il loro turno
		
		nextPlayer();
	}

}
