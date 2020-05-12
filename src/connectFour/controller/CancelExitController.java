package connectFour.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connectFour.view.CloseWindowView;

public class CancelExitController implements ActionListener {

	private CloseWindowView view;
	public CancelExitController(CloseWindowView closeWindowView) {
		view = closeWindowView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setVisible(false);
		
	}

}
