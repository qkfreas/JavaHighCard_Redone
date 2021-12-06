/**
 * Quinn Freas
 * https://github.com/qkfreas
 * Project: High Card
 * @author Quinn Freas
 */
package models;

public class Player {
	private static int win = 0, lose = 0;

	// constructor
	public Player() {
		win = 0;
		lose = 0;
	}

	// adds one to win
	public void setWinCount() {
		win++;
	}

	// adds one to lose
	public void setLoseCount() {
		lose++;
	}

	// returns win
	public int getWin() {
		return win;
	}

	// returns lose
	public int getLoss() {
		return lose;
	}
}
