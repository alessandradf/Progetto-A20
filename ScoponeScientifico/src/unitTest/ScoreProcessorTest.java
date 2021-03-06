package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Card;
import model.SeedType;
import model.Team;
import utility.ScoreProcessor;

/**
 * Test class of {@link ScoreProcessor}.
 *
 */
class ScoreProcessorTest {

	private static Team t1, t2;
	private static ScoreProcessor s;

	@BeforeEach
	public void setUp() {
		t1 = new Team("teamUno");
		t2 = new Team("teamDue");
		s = new ScoreProcessor(t1, t2);
	}

	/**
	 * A {@code Team} takes all the cards. The score must be 4 for this team
	 * (primiera, settebello, denari, carte) and 0 for the other team.
	 */
	@Test
	void shutoutTest() {
		ArrayList<Card> deck;
		deck = createCompleteDeck();
		Collections.sort(deck);
		t1.addCards(deck);
		s.giveScore();
		int result1 = t1.getScore();
		int result2 = t2.getScore();

		assertEquals(4, result1);
		assertEquals(0, result2);

	}

	/**
	 * A {@code Team} took all the cards of {@code SeedType.DENARI} and
	 * {@code SeedType.CUORI}, and a team the rest of the cards, in this way both
	 * teams take the same number of cards. The score must be 2 to 0, because for
	 * the point of cards there is a tie.
	 */
	@Test
	void fithySeedTest() {

		ArrayList<Card> deck = createCompleteDeck();
		ArrayList<Card> cards1 = new ArrayList<Card>();
		ArrayList<Card> cards2 = new ArrayList<Card>();

		for (Card c : deck) {
			if (c.getSeed() == SeedType.DENARI || c.getSeed() == SeedType.CUORI) {
				cards1.add(c);
			} else {
				cards2.add(c);
			}
		}

		t1.addCards(cards1);
		t2.addCards(cards2);

		s.giveScore();
		int result1 = t1.getScore();
		int result2 = t2.getScore();

		assertEquals(2, result1); // denari e settebello
		assertEquals(0, result2);

	}

	/*
	 * A {@code Team} took all the cards of {@code SeedType.DENARI}, In this way it
	 * makes two point. The other team takes all the other cards so it makes a
	 * point. For the primiera there is a tie.
	 */
	@Test
	void onlyDenariTest() throws Exception {

		ArrayList<Card> deck = createCompleteDeck();
		ArrayList<Card> cards1 = new ArrayList<Card>();
		ArrayList<Card> cards2 = new ArrayList<Card>();

		for (Card c : deck) {
			if (c.getSeed() == SeedType.DENARI) {
				cards1.add(c);
			} else {
				cards2.add(c);
			}
		}

		t1.addCards(cards1);
		t2.addCards(cards2);

		s.giveScore();

		int res1 = t1.getScore();
		int res2 = t2.getScore();

		// nessuna primiera
		assertEquals(res1, 2); // denari e settebello
		assertEquals(res2, 1); // carte

	}

	/*
	 * A team took all seven of the deck, so it makes two point. The other two
	 * points go to the other team. They tied 2 - 2.
	 */
	@Test
	void allSeven() throws Exception {

		ArrayList<Card> deck = createCompleteDeck();
		ArrayList<Card> cards1 = new ArrayList<Card>();
		ArrayList<Card> cards2 = new ArrayList<Card>();

		for (Card c : deck) {
			if (c.getValue() == 7) {
				cards1.add(c);
			} else {
				cards2.add(c);
			}
		}

		t1.addCards(cards1);
		t2.addCards(cards2);

		s.giveScore();

		int res1 = t1.getScore();
		int res2 = t2.getScore();

		assertEquals(res1, 2); // settebello and primiera
		assertEquals(res2, 2); // denari and carte

	}

	/**
	 * Create a complete deck.
	 * 
	 * @return the deck
	 */
	private ArrayList<Card> createCompleteDeck() {
		ArrayList<Card> deck = new ArrayList<Card>();
		SeedType seeds[] = SeedType.values();
		for (int i = 1; i <= Card.CARD_VALUES.length; i++) {
			for (SeedType type : seeds) {
				Card temp = new Card(i, type);
				deck.add(temp);
			}
		}
		return deck;
	}

}
