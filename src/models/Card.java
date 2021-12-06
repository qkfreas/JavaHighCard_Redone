/**
 * Quinn Freas
 * https://github.com/qkfreas
 * Project: High Card
 * @author Quinn Freas
 */
package models;

public abstract class Card {
	public abstract int[] getCard(int i);

//	 converts string to int
	public int convertToInt(String item) {
		switch (item) {
		case "Ace":
			return 14;
		case "King":
			return 13;
		case "Queen":
			return 12;
		case "Jack":
			return 11;
		case "Spades":
			return 4;
		case "Diamonds":
			return 3;
		case "Clubs":
			return 2;
		case "Hearts":
			return 1;
		default:
			return Integer.valueOf(item);
		}
	}

	/*
	 * converts int to value
	 * 
	 * @param setValue
	 */
	public String setValue(int i) {
		switch (i) {
		case 0:
			return "2";
		case 1:
			return "3";
		case 2:
			return "4";
		case 3:
			return "5";
		case 4:
			return "6";
		case 5:
			return "7";
		case 6:
			return "8";
		case 7:
			return "9";
		case 8:
			return "10";
		case 9:
			return "Jack";
		case 10:
			return "Queen";
		case 11:
			return "King";
		case 12:
			return "Ace";
		default:
			return i + "";
		}
	}

	// sets suit
	public String setSuit(int i) {
		switch (i) {
		case 0:
			return "Spades";
		case 1:
			return "Diamonds";
		case 2:
			return "Clubs";
		case 3:
			return "Hearts";
		default:
			return "Card suiting error";
		}
	}
}
