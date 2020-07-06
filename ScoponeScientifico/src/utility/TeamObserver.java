package utility;

import model.Card;

public interface TeamObserver {
	public void updateScore(int score, int lastHandScore);
	public void scopa(Card scopaCard);
}
