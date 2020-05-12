package connectFour.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import connectFour.ConnectFourApplication;
import connectFour.controller.CancelExitController;
import connectFour.controller.DisposeGameController;
import connectFour.controller.ExitGameController;
import connectFour.controller.ResetController;
import connectFour.controller.SaveGameController;
import connectFour.model.BoardModel;

public class CloseWindowView extends JDialog{
	private BoardModel model;
	private ConnectFourApplication frame;
	public CloseWindowView(BoardModel model, ConnectFourApplication frame ) {
		this.model = model;
		this.frame = frame;
	    setPreferredSize(new Dimension(250, 250));
	    setResizable(false);
	    pack();
	    setLocationRelativeTo(null);
		setTitle("Exit Game?");
		setVisible(true);
		drawSaveBtn();
		drawExitBtn();
		drawCancelBtn();
	}
	
	private void drawSaveBtn() {
		JButton save = new JButton("Save game Session!");
		save.addActionListener(new SaveGameController(model));
		save.setPreferredSize(new Dimension(36, 7));

		// resetBtn turn visible when GameState != Ongoing
		save.setVisible(true);
		add(save);
	}
	private void drawExitBtn() {
		JButton save = new JButton("Exit Game");
		save.addActionListener(new DisposeGameController(frame));
		save.setPreferredSize(new Dimension(36, 7));


		save.setVisible(true);
		add(save);
	}
	private void drawCancelBtn() {
		JButton save = new JButton("Save game Session!");
		save.addActionListener(new CancelExitController(this));
		save.setPreferredSize(new Dimension(36, 7));

		save.setVisible(true);
		add(save);
	}

}
