package connectFour.model;

import java.awt.Color;

/*
 * This is the player of the game, Game rules allow for 2 players during a match
 */
public class Player {

	private Color color;
	private int winCount;
	private int playerNr;

	public int getPlayerNr() {
		return playerNr;
	}

	public void setPlayerNr(int playerNr) {
		this.playerNr = playerNr;
	}

	public Player(Color color, int playerNr) {
		this.setColor(color);
		this.winCount = 0;
		this.playerNr = playerNr;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getPlayerNrAsText() {
		if (playerNr == 1) {
			return "Player One";
		} else {
			return "Player Two";
		}
	}
	
	public int getWinCount() {
		return winCount;
	}

	public void incrementWinCount() {
		this.winCount++;
	}
}
