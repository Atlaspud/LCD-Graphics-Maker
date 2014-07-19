package au.edu.jcu.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import au.edu.jcu.model.BlockField;

public class GameDisplay extends JPanel {
	
	private static final long serialVersionUID = 1L;
	BlockField gameField;

	public GameDisplay(BlockField gameField, Dimension dimension) {
		setPreferredSize(dimension);
		this.gameField = gameField;
		setBackground(Color.BLACK);
	}
	public GameDisplay(Dimension dimension) {
		setPreferredSize(dimension);
		setBackground(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gameField.draw(g);
	}

}
