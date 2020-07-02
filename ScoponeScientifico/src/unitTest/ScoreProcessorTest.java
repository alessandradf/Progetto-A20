package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Card;
import model.SeedType;
import model.Team;
import utility.ScoreProcessor;

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
	 * Test in cui un team ha preso tutte le carte
	 */
	@Test
	void cappottoTest() {
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

		s.giveScore();
		int result1 = t1.getScore();
		int result2 = t2.getScore();

		assertEquals(2, result1); // denari e settebello
		assertEquals(0, result2);

	}

	
	/*
	 * test in cui un team ha prese tutte le carte di denari
	 */
	@Test
	void soloDenariTest() throws Exception {
		
		ArrayList<Card> deck = createCompleteDeck();
		ArrayList<Card> cards1 = new ArrayList<Card>();
		ArrayList<Card> cards2 = new ArrayList<Card>();
		
		for (Card c : deck) {
			if(c.getSeed() == SeedType.DENARI) {
				cards1.add(c);
			}
			else {
				cards2.add(c);
			}
		}
		
		t1.addCards(cards1);
		t2.addCards(cards2);

		s.giveScore();
		
		int res1 = t1.getScore();
		int res2 = t2.getScore();
		
		//nessuna primiera
		assertEquals(res1,  2);		//denari e settebello
		assertEquals(res2, 1);		//carte
		
	}
	
	
	/*
	 * il team1 ha preso tutti e soli i 7 del mazzo
	 */
	@Test
	void tuttiSette() throws Exception {
		
		ArrayList<Card> deck = createCompleteDeck();
		ArrayList<Card> cards1 = new ArrayList<Card>();
		ArrayList<Card> cards2 = new ArrayList<Card>();
		
		for (Card c : deck) {
			if(c.getValue() == 7) {
				cards1.add(c);
			}
			else {
				cards2.add(c);
			}
		}
		
		t1.addCards(cards1);
		t2.addCards(cards2);
		
		s.giveScore();
		
		int res1 = t1.getScore();
		int res2 = t2.getScore();
		
		assertEquals(res1, 2);		//settebello e primiera
		assertEquals(res2, 2);		//denari e carte
		
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
