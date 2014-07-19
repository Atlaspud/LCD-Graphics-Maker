package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.DrawableRectangles;

public class DisplayPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	DrawableRectangles rect;

	public DisplayPanel(DrawableRectangles rect) {
		super();
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(400,300));
		this.rect = rect;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		rect.draw(g);
	}

}
