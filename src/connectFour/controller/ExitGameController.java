package connectFour.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connectFour.ConnectFourApplication;
import connectFour.model.BoardModel;
import connectFour.view.CloseWindowView;

public class ExitGameController implements ActionListener{

	private ConnectFourApplication app;
	private BoardModel model;
	public ExitGameController(ConnectFourApplication app, BoardModel model) {
		this.app = app;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		new CloseWindowView(model, app);
	}

}
