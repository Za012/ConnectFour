package connectFour.view;

import java.util.Observable;
import java.util.Observer;

import connectFour.model.BoardModel;
import connectFour.model.Chip;
 
public class ConsoleView implements Observer {

	private BoardModel boardModel;

	public ConsoleView(BoardModel boardModel) {
		this.boardModel = boardModel;
		this.boardModel.addObserver(this);
		renderGame();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		renderGame();
		switch (boardModel.getGameState()) {
		case Ongoing:
			System.out.println(boardModel.isCurrentPlayer().getPlayerNrAsText() + " make a move!");
			break;
		case Win:
			System.out.println(boardModel.isCurrentPlayer().getPlayerNrAsText() + " is the Winner!");	
			break;
		case Draw:
			System.out.println("Game is a draw");
			break;
		}
	}

	private void renderGame() {
		for (int row = 0; row < boardModel.getROW(); row++) {
			for (int column = 0; column < boardModel.getCOL(); column++) {
				displaySlot(boardModel.slotState(row, column));
			}
			System.out.println();
		}
		System.out.println("_______\n");
	}

	private void displaySlot(Chip slot) {
		if (slot == null) {
			System.out.print(".");
		} else{
			System.out.print(slot.getPlayer().getPlayerNr());
		}
	}
}
