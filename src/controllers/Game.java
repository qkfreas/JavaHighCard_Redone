/**
 * Quinn Freas
 * https://github.com/qkfreas
 * Project: High Card
 * @author Quinn Freas
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Deck;
import models.Player;

public class Game {
	private Deck deck;
	private static int[] dealerCard = null;
	private static int[] currentCard = null;
	private static ImageView currentCardImg;
	private static ImageView dealerCardImg;
	private static int currentID = 0;
	private static int roundsRemaining;
	public static Player player;
	private static boolean imageError = false;
	private int MAX_ROUNDS = 26;
	private final String absolutePath = (new File("")).getAbsolutePath();
	private final String imgPath = "file:" + absolutePath + "/lib/imgs/deck/";
//	private final String imgPath = "file:" + "/";

// constructor for Game
	public Game() {
		deck = new Deck();
		player = new Player();
		deck.setCardsRemaining(52);
		roundsRemaining = MAX_ROUNDS;
		deck.shuffle();
// sets default card image
		dealerCardImg = getCardImage();
		currentCardImg = getCardImage();
	}

// establishes a card
	public void setCard() {
		currentCard = new int[3];
	}

// counts win
	public void countWin() {
		player.setWinCount();
	}

// sets the dealer and player cards
// recursion
	public void deal() {
		deck.subtractCard();
		currentID = deck.getCardID(deck.getCardsRemaining());
		currentCard = deck.getCard(deck.getCardsRemaining());
		currentCardImg = getCardImage();

		if (dealerCard == null) {
			subtractRound();
			dealerCard = currentCard;
			dealerCardImg = currentCardImg;
			deal();
		}
	}

// returns the card image
// sets imageError to true if no images can be found
	private ImageView getCardImage() {
		Image img = null;
		try {
			img = new Image(this.getClass().getResourceAsStream("/" + currentID + ".png"));
			if (img.isError() == true) {
				imageError = true;
			}
		} catch (NullPointerException npe) {
			try {
				img = new Image(imgPath + (currentID) + ".png");

				if (img.isError() == true) {
					imageError = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
		}
		ImageView iv = new ImageView();
		iv.setImage(img);
		iv.setFitHeight(150);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);

		return iv;
	}

// returns a Stack Pane with images in the form of a deck
	public ImageView getDeckImage() {
		Image img = null;

		String path = new File("").getAbsolutePath();
		String imgDir = "/lib/imgs/deck";
		try {
			img = new Image(new FileInputStream(path.concat(imgDir).concat("/" + 0 +".png")));

			if (img.isError() == true) {
				imageError = true;
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		ImageView iv = new ImageView();
		iv.setImage(img);
		iv.setFitHeight(150);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		return iv;
	}

// returns whether there was an image error
	public boolean getImageError() {
		return imageError;
	}

// returns player card image
	public ImageView getPlayerCardImage() {
		return currentCardImg;
	}

// returns dealer card image
	public ImageView getDealerCardImage() {
		return dealerCardImg;
	}

// determines the winner of the round
	public void getWinner() {

		if (Integer.valueOf(dealerCard[2]) < Integer.valueOf(currentCard[2])) {
			setWinCount();
		} else if (Integer.valueOf(dealerCard[2]) == Integer.valueOf(currentCard[2])) {
			if (Integer.valueOf(dealerCard[1]) < Integer.valueOf(currentCard[1])) {
				setWinCount();
			} else
				setLoseCount();
		} else
			setLoseCount();
// resets dealer card for next round
		dealerCard = null;
	}

// returns cards remaining
	public int getCardsRemaining() {
		return deck.getCardsRemaining();
	}

// returns rounds remaining
	public int getRoundsRemaining() {
		return roundsRemaining;
	}

// resets rounds remaining
	public void resetRounds() {
		roundsRemaining = MAX_ROUNDS;
	}

// sets win count
	private void setWinCount() {
		player.setWinCount();
	}

// set loss count
	private void setLoseCount() {
		player.setLoseCount();
	}

// subtracts one from rounds remaining
	public void subtractRound() {
		roundsRemaining--;
	}

// resets current ID
	public void resetCurrentID() {
		currentID = 0;
	}
}
