package connectFour.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import connectFour.GameState;
import connectFour.model.BoardModel;

public class ResetController implements ActionListener {

	private BoardModel boardModel;
	private JButton resetBtn;

	public ResetController(BoardModel boardModel, JButton resetBtn) {
		this.boardModel = boardModel;
		this.resetBtn = resetBtn;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		boardModel.setGameState(GameState.Ongoing);
		boardModel.reset();
		resetBtn.setVisible(false);
	}
}
