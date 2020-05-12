package connectFour.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connectFour.ConnectFourApplication;

public class DisposeGameController implements ActionListener{

	private ConnectFourApplication app;
	public DisposeGameController(ConnectFourApplication app) {
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// close app
		
	}

}
