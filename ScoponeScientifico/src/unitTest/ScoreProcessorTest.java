package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Card;
import model.SeedType;
import model.Team;
import utility.ScoreProcessor;

class ScoreProcessorTest {

	private static Team t1, t2;
	
	@BeforeAll
	static void setUp() {
		t1 = new Team("teamUno");
		t2 = new Team("teamDue");
	}

	/**
	 * Test in cui un team ha preso tutte le carte
	 */
	@Test
	void cappottoTest() {
		ArrayList<Card> deck;
		deck = createCompleteDeck();
		Collections.sort(deck);
		t1.addCards(deck);
		ScoreProcessor s = new ScoreProcessor(t1, t2);
		s.giveScore();
		int result1 = t1.getScore();
		int result2 = t2.getScore();

		assertEquals(4, result1);
		assertEquals(0, result2);

	}

	/**
	 * Test in cui ciascuna squadra ha preso le carte relative a solo due semi 
	 */
	@Test
	void fithySeedTest() {

		ArrayList<Card> onlyCuoriDeck, onlyDenariDeck, onlyFioriDeck, onlyPiccheDeck;
		onlyCuoriDeck = createOnlySeedDeck(SeedType.CUORI);
		onlyDenariDeck = createOnlySeedDeck(SeedType.DENARI);
		onlyFioriDeck = createOnlySeedDeck(SeedType.FIORI);
		onlyPiccheDeck = createOnlySeedDeck(SeedType.PICCHE);
		t1.addCards(onlyCuoriDeck);
		t1.addCards(onlyDenariDeck);
		t2.addCards(onlyFioriDeck);
		t2.addCards(onlyPiccheDeck);

		ScoreProcessor s = new ScoreProcessor(t1, t2);
		s.giveScore();
		int result1 = t1.getScore();
		int result2 = t2.getScore();

		assertEquals(2, result1); // denari e settebello
		assertEquals(0, result2);

	}

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

	private ArrayList<Card> createOnlySeedDeck(SeedType seed) {
		ArrayList<Card> deck = new ArrayList<Card>();
		for (int i = 1; i <= Card.CARD_VALUES.length; i++) {
			Card temp = new Card(i, seed);
			deck.add(temp);
		}
		return deck;
	}

}
