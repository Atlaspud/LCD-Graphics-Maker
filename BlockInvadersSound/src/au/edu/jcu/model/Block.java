package au.edu.jcu.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Block {
	protected Point position;
	protected Dimension size;
	protected Color colour;
	
	public Block() {
		position = new Point(0,0);
		size = new Dimension(10,10);
		colour = Color.WHITE;
	}
	
	public boolean hit(Block other) {	
		Rectangle thisBlock = new Rectangle(position.x, position.y, size.width, size.height);
		Rectangle otherBlock = new Rectangle(other.position.x, other.position.y, other.size.width, other.size.height);
		
		if (thisBlock.intersects(otherBlock)) {
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.drawRect(this.position.x, this.position.y, 
				this.size.width, this.size.height);
	}

}
