package connectFour.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import connectFour.ConnectFourApplication;
import connectFour.controller.ExitGameController;
import connectFour.model.BoardModel;
/*
 * Northern part of the window - > To be implemented <
 */
public class ButtonView extends JPanel {
	
	private BoardModel boardModel;
	private ConnectFourApplication app;
	public ButtonView(BoardModel boardModel, ConnectFourApplication app) {
		this.setLayout(new GridLayout(0,3));
		this.boardModel = boardModel;
		this.app = app;;
		initButtons();
	}
	
	public void initButtons() {
		button1();
		button2();
		button3();
	}
	
	public void button1() {
		JButton button = new JButton("Button1");
		button.setVisible(true);
		add(button);
	}
	
	public void button2() {
		JButton button = new JButton("Button2");
		button.setVisible(true);
		add(button);
	}
	
	public void button3() {
		JButton button = new JButton("Exit Game");
		button.setVisible(true);
		button.addActionListener(new ExitGameController(app, boardModel));
		add(button);
	}

}
