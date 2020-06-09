package utility;

import model.Card;

public interface TeamObserver {
	public void updateScore(int score);
	public void scopa(Card scopaCard);
}
