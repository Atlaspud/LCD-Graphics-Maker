package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

public class DrawableRectangle {
	protected Point position;
	protected Dimension size;
	protected Color colour;
	int bounceNumX = 1, bounceNumY = 2;
	public int numX;
	public int numY;
	
	public DrawableRectangle() {
		position = new Point(0,0);
		size = new Dimension(10,10);
		colour = Color.blue;
		Random random = new Random();
		numX = random.nextInt(5) + 1;
		numY = random.nextInt(5) + 1;
	}

}
