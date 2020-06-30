package utility;

import model.Card;
import model.Player;

public interface PlayerObserver {

	public void updateOnPlayedCard(Player p, Card playedCard);
	
}
