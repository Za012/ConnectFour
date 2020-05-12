package connectFour.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import connectFour.ConnectFourApplication;

public class ColorPickerController implements ActionListener {
	private Color colorOne;
	private Color colorTwo;
	private JColorChooser colorChooser;
	private ConnectFourApplication app;
	private JButton submitBtn;
	
	public ColorPickerController(ConnectFourApplication app, JColorChooser colorChooser, JButton submitBtn) {
		this.colorChooser = colorChooser;
		this.app = app;
		this.submitBtn = submitBtn;
		this.submitBtn.setText("Chooser Color -> Player One!");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(colorOne == null) {
			colorOne = colorChooser.getColor();
			submitBtn.setText("Choose Color -> Player Two!");
		}else {
			colorTwo = colorChooser.getColor();
			app.initGame(colorOne, colorTwo);
		}
	}
}
