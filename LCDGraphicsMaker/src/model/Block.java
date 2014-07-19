package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Block {
	protected Point position;
	protected Dimension size;
	protected Color colour;
	protected boolean backBlock;
	
	public Block(Boolean backBlock) {
		position = new Point(0,0);
		size = new Dimension(10,10);
		colour = Color.WHITE;
		this.backBlock = backBlock;
	}
	
	public boolean hit(int x, int y) {
		Rectangle thisBlock = new Rectangle(position.x, position.y, size.width, size.height);
		Rectangle otherBlock = new Rectangle(x, y, 1, 1);
		
		if (thisBlock.intersects(otherBlock)) {
			if (colour == Color.WHITE) {
				colour = Color.BLUE;
			} else {
				colour = Color.WHITE;
			}
		}
		System.out.println(thisBlock.intersects(otherBlock));
		return thisBlock.intersects(otherBlock);
	}
	
	public void draw(Graphics g) {
		g.setColor(this.colour);
		if (backBlock) {
			g.drawRect(this.position.x, this.position.y, 
					this.size.width, this.size.height);
		} else {
			g.fillRect(this.position.x, this.position.y, 
					this.size.width, this.size.height);
		}
	}
	
	public boolean isSelected() {
		return colour == Color.BLUE;
	}
}
