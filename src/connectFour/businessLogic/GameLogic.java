package connectFour.businessLogic;

import java.util.HashMap;
import java.util.Map;

import connectFour.model.BoardModel;

/*
 * This class contains all the logic/rules of the game 
 */
public class GameLogic {

	private BoardModel boardModel;
	private Map<String, Boolean> sequence;

	public GameLogic(BoardModel board) {
		this.boardModel = board;
		sequence = new HashMap<String, Boolean>();
	}

	private void initSequenceMap() {
		// Used to check if a sequence has been broken -> false == broken sequence
		sequence.put("HorizontalLeft", true);
		sequence.put("HorizontalRight", true);
		sequence.put("VerticalDown", true);
		sequence.put("DiagonalLeftDown", true);
		sequence.put("DiagonalRightUp", true);
		sequence.put("DiagonalLeftUp", true);
		sequence.put("DiagonalRightDown", true);
	}

	public boolean checkWin(int row, int column) {
		initSequenceMap();
		int horizontal = 0, vertical = 0, leftDiagonal = 0, rightDiagonal = 0;

		for (int i = 1; i < 4; i++) {
			horizontal += horizontalCount(row, column, i);
			vertical += verticalCount(row, column, i);
			leftDiagonal += leftDiagonalCount(row, column, i);
			rightDiagonal += rightDiagonalCount(row, column, i);
		}

		// 3 == 4-In-A-Row because you don't count the center chip(most recent entered
		// one)
		if (leftDiagonal >= 3 || rightDiagonal >= 3 || horizontal >= 3 || vertical >= 3) {
			return true;
		} else {
			return false;
		}
	}

	private int horizontalCount(int r, int c, int i) {
		int count = 0;
		if (boardModel.getCOL() > c + i && sequence.get("HorizontalLeft")) {
			if (boardModel.slotState(r, c + i) != null
					&& boardModel.isCurrentPlayer().equals(boardModel.slotState(r, c + i).getPlayer())) {
				count++;
			} else {
				sequence.replace("HorizontalLeft", false);
			}
		}
		if (0 <= c - i && sequence.get("HorizontalRight")) {
			if (boardModel.slotState(r, c - i) != null
					&& boardModel.isCurrentPlayer().equals(boardModel.slotState(r, c - i).getPlayer())) {
				count++;
			} else {
				sequence.replace("HorizontalRight", false);
			}
		}
		return count;
	}

	private int verticalCount(int r, int c, int i) {
		// There's no valid scenario for VerticalUp
		int count = 0;
		if (boardModel.getROW() > r + i && sequence.get("VerticalDown")) {
			if (boardModel.slotState(r + i, c) != null
					&& boardModel.isCurrentPlayer().equals(boardModel.slotState(r + i, c).getPlayer())) {
				count++;
			} else {
				sequence.replace("VerticalDown", false);
			}
		}
		return count;
	}

	// Diagonal Check, the X(cross) pattern around the chip
	// option 1: r++ c++  / right up
	// option 2: r-- c-- / left down
	// option 3: r++ c-- \ left up
	// option 4: r-- c++  \ right down
	private int rightDiagonalCount(int r, int c, int i) {
		int count = 0;
		if (0 <= r - i && boardModel.getCOL() > c + i && sequence.get("DiagonalRightDown")) {
			if (boardModel.slotState(r - i, c + i) != null
					&& boardModel.isCurrentPlayer().equals(boardModel.slotState(r - i, c + i).getPlayer())) {
				count++;
			} else {
				sequence.replace("DiagonalRightDown", false);
			}
		}

		if (boardModel.getROW() > r + i && 0 <= c - i && sequence.get("DiagonalRightUp")) {
			if (boardModel.slotState(r + i, c - i) != null
					&& boardModel.isCurrentPlayer().equals(boardModel.slotState(r + i, c - i).getPlayer())) {
				count++;
			} else {
				sequence.replace("DiagonalRightUp", false);
			}
		}
		return count;
	}

	private int leftDiagonalCount(int r, int c, int i) {
		int count = 0;
		if (0 <= r - i && 0 <= c - i && sequence.get("DiagonalLeftDown")) {
			if (boardModel.slotState(r - i, c - i) != null
					&& boardModel.isCurrentPlayer().equals(boardModel.slotState(r - i, c - i).getPlayer())) {
				count++;
			} else {
				sequence.replace("DiagonalLeftDown", false);
			}
		}

		if (boardModel.getROW() > r + i && boardModel.getCOL() > c + i && sequence.get("DiagonalLeftUp")) {
			if (boardModel.slotState(r + i, c + i) != null
					&& boardModel.isCurrentPlayer().equals(boardModel.slotState(r + i, c + i).getPlayer())) {
				count++;
			} else {
				sequence.replace("DiagonalLeftUp", false);
			}
		}
		return count;
	}

	public boolean checkDraw() {
		for (int column = 0; column < boardModel.getCOL(); column++) {
			if (boardModel.slotState(0, column) == null) {
				return false;
			}
		}
		return true;
	}

}
