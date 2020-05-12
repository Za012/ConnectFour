package connectFour.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class GameTimeController implements ActionListener {

	JLabel gameTimeLbl;
	private int minutes;
	private int seconds;
	
	public void resetTime() {
		minutes = 0;
		seconds = 0;
	}
	
	public GameTimeController(JLabel gameTimeLbl) {
		this.gameTimeLbl = gameTimeLbl;
		minutes = 0;
		seconds = 0;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		seconds++;
		if(seconds > 59) {
			minutes++;
			seconds = 0;
		}
		gameTimeLbl.setText("Time Played: "+ minutes+"."+seconds);
		
	}
}
