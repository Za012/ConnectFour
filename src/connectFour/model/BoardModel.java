package connectFour.model;

import java.io.Serializable;
import java.util.Observable;

import connectFour.GameState;
import connectFour.IllegalMoveException;
import connectFour.businessLogic.GameLogic;

/*
 * All the Data of the application is stored and used in here,
 * The logic checks are done in a separate class, GameLogic.
 */

public class BoardModel extends Observable implements Serializable{
	private final int ROW = 6;
	private final int COL = 7;
	private Chip[][] board;
	private GameState gameState;
	private Player currentPlayer;
	private Player playerOne;
	private Player playerTwo;
	private GameLogic gameLogic;
	private int amountChipsPlaced;

	public int getAmountChipsPlaced() {
		return amountChipsPlaced;
	}

	public void incrementAmountChipsPlaced() {
		this.amountChipsPlaced++;
	}
	public Player getPlayerOne() {
		return playerOne;
	}

	public Player getPlayerYellow() {
		return playerTwo;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public int getROW() {
		return ROW;
	}

	public int getCOL() {
		return COL;
	}

	public Player isCurrentPlayer() {
		return currentPlayer;
	}

	/////
	public BoardModel(Player one, Player two) {
		this.playerOne = one;
		this.playerTwo = two;
		this.currentPlayer = playerOne;
		this.gameState = GameState.Ongoing;
		this.gameLogic = new GameLogic(this);
		initBoard();
	}

	private void initBoard() {
		amountChipsPlaced = 0;
		board = new Chip[ROW][COL];
		setChanged();
		notifyObservers();
	}

	public void reset() {
		initBoard();
	}

	public void insertChip(int column) throws IllegalMoveException {
		int row = 0;

		try {
			row = getEmptySlot(column);
		} catch (IllegalMoveException imex) {
			throw imex;
		}

		board[row][column] = new Chip(isCurrentPlayer());
		incrementAmountChipsPlaced();
		if (gameLogic.checkWin(row, column)) {
			gameState = GameState.Win;
			currentPlayer.incrementWinCount();
		} else if (gameLogic.checkDraw()) {
			gameState = GameState.Draw;
		} else {
			switchPlayer();
		}
		setChanged();
		notifyObservers();
	}

	private void switchPlayer() {
		if (currentPlayer.equals(playerOne)) {
			currentPlayer = playerTwo;
		} else {
			currentPlayer = playerOne;
		}
	}

	private int getEmptySlot(int column) throws IllegalMoveException {
		for (int row = ROW - 1; row >= 0; row--) {
			if (board[row][column] == null) {
				return row;
			}
		}
		// Triggered if there's no empty slot
		throw new IllegalMoveException("Column is full!");
	}

	public Chip slotState(int row, int column) {
		if (board[row][column] != null) {
			return board[row][column];
		} else {
			return null;
		}
	}

}
