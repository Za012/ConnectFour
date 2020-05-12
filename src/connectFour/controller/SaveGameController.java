package connectFour.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import connectFour.model.BoardModel;
import connectFour.view.CloseWindowView;

public class SaveGameController implements ActionListener{

	private BoardModel model;
	public SaveGameController(BoardModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			writeToFile();
		} catch (IOException e1) {
			System.out.println("Print failed");
		}
		
	}
	
	private void writeToFile() throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("BoardModel.bin"));
		
		objectOutputStream.writeObject(model);
	}

}
