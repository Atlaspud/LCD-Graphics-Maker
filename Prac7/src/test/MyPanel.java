package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Point myPoint;
	
	public MyPanel(Point point) {
		setPreferredSize(new Dimension(600,400));
		setBackground(Color.BLACK);
		myPoint = point;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.CYAN);
		g.fillOval((int) myPoint.getX(), (int) myPoint.getY(), 100, 100);
	}
}
