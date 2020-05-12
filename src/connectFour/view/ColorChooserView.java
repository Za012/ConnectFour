package connectFour.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import connectFour.ConnectFourApplication;
import connectFour.controller.ColorPickerController;
import connectFour.controller.ResetController;
import connectFour.model.Player;

public class ColorChooserView extends JPanel {

	private ConnectFourApplication app;
	private JColorChooser colorChooser;
	public ColorChooserView(ConnectFourApplication app) {
		this.app = app;
		this.setBackground(Color.white);
		createTitle();
	    colorChooser = new JColorChooser();
		add(colorChooser);
		createButton();
	}
	private void createTitle() {
		JLabel title = new JLabel("Select your color");
		title.setFont(new Font("Arial", 1, 20));
		add(title);		
	}
	private void createButton() {
		JButton button = new JButton();
		button.addActionListener(new ColorPickerController(app ,colorChooser, button));
		button.setFont(new Font("Arial", 1, 20));
		button.setBackground(new Color(67, 176, 71));

		button.setVisible(true);
		add(button);
	}
}
