package connectFour.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import connectFour.GameState;
import connectFour.controller.ColumnController;
import connectFour.controller.ResetController;
import connectFour.model.BoardModel;
import connectFour.model.Chip;
/*
 * Center part of the window, the slots, columns and reset button are drawn from here
 * This app uses paintComponent to draw the slots.
 * */
public class GraphicalView extends JPanel implements Observer {

	private BoardModel boardModel;
	private JButton resetBtn;

	public GraphicalView(BoardModel boardModel) {
		this.boardModel = boardModel;
		this.boardModel.addObserver(this);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, this.boardModel.getCOL(), 0));

		initBoard();
	}

	private void initBoard() {
		drawColumns();
		this.setBackground(new Color(4, 156, 219));
		drawResetBtn();
	}

	private void drawResetBtn() {
		resetBtn = new JButton("Play Again!");
		resetBtn.addActionListener(new ResetController(boardModel, resetBtn));
		resetBtn.setPreferredSize(new Dimension(36 * boardModel.getCOL(), 7 * boardModel.getROW()));
		resetBtn.setFont(new Font("Arial", 1, 20));
		resetBtn.setBackground(new Color(67, 176, 71));

		// resetBtn turn visible when GameState != Ongoing
		resetBtn.setVisible(false);
		add(resetBtn, FlowLayout.RIGHT);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Gives graphics smooth edges (Antialiasing)
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (int row = 0; row < boardModel.getROW(); row++) {
			for (int column = 0; column < boardModel.getCOL(); column++) {
				displaySlot(row, column, g);
			}
		}
		if (boardModel.getGameState() != GameState.Ongoing) {
			resetBtn.setVisible(true);
		}
	}

	private void displaySlot(int row, int column, Graphics g) {
		Chip slot = boardModel.slotState(row, column);
		if (slot == null) {
			g.setColor(Color.white);
		} else {
			g.setColor(slot.getPlayer().getColor());
		}
		g.fillOval((95 * column) + (5 * boardModel.getCOL()), (60 * row) + (1 * boardModel.getROW()), 50, 50);
	}

	private void drawColumns() {
		// These buttons will represent each column, they are invisible by default
		for (int x = 0; x < boardModel.getCOL(); x++) {
			JButton columnBtn = new JButton();
			columnBtn.addActionListener(new ColumnController(x, boardModel));
			columnBtn.setPreferredSize(new Dimension(87, 61 * boardModel.getROW()));
			columnBtn.setOpaque(false);
			columnBtn.setContentAreaFilled(false);
			columnBtn.setBorderPainted(false);

			// Hover effect, highlighting the whole column
			columnBtn.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent evt) {
					columnBtn.setBorderPainted(true);
				}

				public void mouseExited(MouseEvent evt) {
					columnBtn.setBorderPainted(false);
				}
			});
			add(columnBtn);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
