package connectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JFrame;

import connectFour.model.BoardModel;
import connectFour.model.Player;
import connectFour.view.ButtonView;
import connectFour.view.CloseWindowView;
import connectFour.view.ColorChooserView;
import connectFour.view.ConsoleView;
import connectFour.view.GraphicalView;
import connectFour.view.StatusView;

public class ConnectFourApplication extends JFrame {
// Question 3 unfinished, reason -> out of time...
	private ColorChooserView colorView;
	private BoardModel model;
	public ConnectFourApplication() {
		File f = new File("../SaveFile/BoardModel.bin");
		
		if (f.exists()) {
			try {
				model = initFromSaveFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Something went wrong");
			}
		}
		colorView = new ColorChooserView(this);
		add(colorView, BorderLayout.CENTER);
		display();
	}

	private BoardModel initFromSaveFile() throws IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("BoardModel.bin"));
		return (BoardModel) objectInputStream.readObject();
	}

	public void initGame(Color colorOne, Color colorTwo) {
		this.remove(colorView);
		BoardModel boardModel = new BoardModel(new Player(colorOne, 1), new Player(colorTwo, 2));
		new ConsoleView(boardModel);
		add(new ButtonView(boardModel, this), BorderLayout.NORTH);
		add(new GraphicalView(boardModel), BorderLayout.CENTER);
		add(new StatusView(boardModel), BorderLayout.SOUTH);
		display();
	}

	private void display() {
		setPreferredSize(new Dimension(700, 460));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Connect Four");
		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new ConnectFourApplication();
	}
}
