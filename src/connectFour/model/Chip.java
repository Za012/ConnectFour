package connectFour.model;

/*
 * The chip is the object that the player's use as reference
 */
public class Chip {

	private Player player;

	public Player getPlayer() {
		return player;
	}

	public Chip(Player player) {
		this.player = player;
	}
}
