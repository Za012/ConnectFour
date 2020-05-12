package connectFour.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connectFour.GameState;
import connectFour.IllegalMoveException;
import connectFour.model.BoardModel;

/*
 * Each ColumnBtn knows it's position and when clicked it inserts a chip.
 */
public class ColumnController implements ActionListener {

	private int column;
	private BoardModel boardModel;

	public ColumnController(int column, BoardModel boardModel) {
		this.column = column;
		this.boardModel = boardModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Each chip saves the current player and is positioned by the first empty row of the column.
		try {
			if (boardModel.getGameState() == GameState.Ongoing) {
				boardModel.insertChip(column);
			}
		} catch (IllegalMoveException imex) {
			System.out.println(imex);
		}
	}
}
