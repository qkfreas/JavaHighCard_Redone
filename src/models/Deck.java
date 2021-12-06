/**
 * Quinn Freas
 * https://github.com/qkfreas
 * Project: High Card
 * @author Quinn Freas
 */
package models;

public class Deck extends Card {
	public static int cardsRemaining;
	private static String[][] deck;

// returns cards remaining
	public int getCardsRemaining() {
		return cardsRemaining;
	}

// subtracts one from cards remaining
	public void subtractCard() {
		cardsRemaining--;
	}

// shuffles the deck
	public void shuffle() {
		createDeck();
		cardsRemaining = 52;
		String[][] temp = getDeck();

		for (int i = 0; i < 51; i++) {
			int j = (int) (Math.random() * 51);

			String id = temp[0][j];
			String suit = temp[1][j];
			String value = temp[2][j];

			temp[0][j] = temp[0][i];
			temp[1][j] = temp[1][i];
			temp[2][j] = temp[2][i];

			temp[0][i] = id;
			temp[1][i] = suit;
			temp[2][i] = value;

		}
		setDeck(temp);
	}

// returns one card from the deck
	public int[] getCard(int i) {
		int[] temp = new int[3];

		temp[0] = getCardID(i);
		temp[1] = convertToInt(getCardSuit(i));
		temp[2] = convertToInt(getCardValue(i));

		return temp;
	}

// returns the value of a card
	String getCardValue(int i) {
		return deck[2][i];
	}

// returns the ID of a card
	public int getCardID(int i) {
		return Integer.valueOf(deck[0][i]);
	}

// returns the suit of a card
	public String getCardSuit(int i) {
		return deck[1][i];
	}

// returns the deck
	public String[][] getDeck() {
		return deck;
	}

// sets the deck
	public void setDeck(String[][] deck) {
		Deck.deck = deck;
	}

// set cards remaining
	public void setCardsRemaining(int newCardsRemaining) {
		cardsRemaining = newCardsRemaining;
	}

// creates a deck
	private void createDeck() {
		deck = new String[3][52];

		int k = 0, j = 0;
		for (int i = 0; i < 52; i++) {
			// id
			deck[0][i] = "" + (i + 1);

			// suit
			deck[1][i] = super.setSuit(k);

			// value
			deck[2][i] = super.setValue(j);

			j++;

			if (j >= 13) {
				j = 0;
				k++;
				if (k >= 4) {
					k = 0;
				}
			}
		}
	}
}
