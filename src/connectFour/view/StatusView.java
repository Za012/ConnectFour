package connectFour.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import connectFour.controller.GameTimeController;
import connectFour.model.BoardModel;
/*
 * Southern part of the Window, informs the Players about the current status
 */
public class StatusView extends JPanel implements Observer {

	private BoardModel boardModel;
	private JLabel statusLbl;
	private JLabel redWinLbl;
	private JLabel yellowWinLbl;
	private JLabel chipAmountLbl;
	private JLabel gameTimeLbl;
	private Timer gameTimer;
	private GameTimeController gameTimeController;

	public StatusView(BoardModel boardModel) {
		this.boardModel = boardModel;
		this.boardModel.addObserver(this);
		this.setLayout(new GridLayout(2,2));
		this.setPreferredSize(new Dimension(0,6 * this.boardModel.getROW()));
		createLabels();
		displayTimer();
		displayAmountOfChips();
		displayStatistic();
		displayGameStatus();
	}
	
	private void displayAmountOfChips() {
		chipAmountLbl.setText("Amount chips placed: "+ boardModel.getAmountChipsPlaced());	
	}

	private void displayTimer() {
		gameTimeController = new GameTimeController(gameTimeLbl);
        gameTimer = new Timer(1000, gameTimeController);
        gameTimer.start();
	}

	private void createLabels() {
		Font font = new Font("Arial", 1, 3 * boardModel.getROW());
		statusLbl = new JLabel(); redWinLbl = new JLabel(); yellowWinLbl = new JLabel(); 
		chipAmountLbl = new JLabel();  gameTimeLbl = new JLabel("Time Played: ");
		statusLbl.setFont(font); redWinLbl.setFont(font); yellowWinLbl.setFont(font); 
		chipAmountLbl.setFont(font); gameTimeLbl.setFont(font);

		redWinLbl.setHorizontalAlignment(SwingConstants.LEFT);
		yellowWinLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		statusLbl.setHorizontalAlignment(SwingConstants.CENTER);
		chipAmountLbl.setHorizontalAlignment(SwingConstants.LEFT);
		gameTimeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.add(redWinLbl);
		this.add(statusLbl);
		this.add(yellowWinLbl);
		this.add(chipAmountLbl);
        this.add(gameTimeLbl);
	}

	private void displayGameStatus() {
		switch (boardModel.getGameState()) {
		case Ongoing:
			statusLbl.setText(boardModel.isCurrentPlayer().getPlayerNrAsText() + " make a move!");
			gameTimer.start();
			break;
		case Win:
			statusLbl.setText(boardModel.isCurrentPlayer().getPlayerNrAsText() + " is the Winner!");
			displayStatistic();
			gameTimer.stop();
			gameTimeController.resetTime();		
			break;
		case Draw:
			statusLbl.setText("Game is a draw!");
			gameTimer.stop();
			gameTimeController.resetTime();	
			break;
		}
	}
	
	private void displayStatistic() {
		// Only updates on start and when a Win occurs
		redWinLbl.setText(boardModel.getPlayerOne().getPlayerNrAsText().toUpperCase() +" "+ boardModel.getPlayerOne().getWinCount());
		yellowWinLbl.setText(boardModel.getPlayerYellow().getWinCount()+" "+boardModel.getPlayerYellow().getPlayerNrAsText().toUpperCase());
	}

	@Override 
	public void update(Observable arg0, Object arg1) {
		displayGameStatus();
		displayAmountOfChips();
	}

}
