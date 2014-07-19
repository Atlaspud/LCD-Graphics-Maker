package model;

import java.awt.Graphics;
import java.util.ArrayList;

public class DrawableRectangles {
	ArrayList<DrawableRectangle> arrayList;
	
	public DrawableRectangles() {
		arrayList = new ArrayList<DrawableRectangle>();
	}
	
	public void add() throws DrawableRectangleException {
		if (arrayList.size() >= 20) {
			throw new DrawableRectangleException("can't add any more; the list is full\n");
		} else {
			arrayList.add(new DrawableRectangle());
		}
	}
	
	public void remove() throws DrawableRectangleException {
		if (arrayList.isEmpty()) {
			throw new DrawableRectangleException("can't remove from empty list\n");
		} else {
			arrayList.remove(0);
		}
			
	}
	
	public void bounce(int x, int y) {
		for (DrawableRectangle rect : arrayList) {
			if (rect.position.x >= x-10 || rect.position.x <= 0) {
				rect.bounceNumX *= -1;
			}
			if (rect.position.y >= y-10 || rect.position.y <= 0) {
				rect.bounceNumY *= -1;
			}
		}
	}
	
	public void move() {
		for (DrawableRectangle rect : arrayList) {
			
			rect.position.x += rect.bounceNumX * rect.numX;
			rect.position.y += rect.bounceNumY * rect.numY;
		}
	}
	
	public void draw(Graphics g) {
		for (DrawableRectangle rect : arrayList) {
			g.setColor(rect.colour);
			g.drawRect(rect.position.x, rect.position.y, rect.size.width, rect.size.height);
		}
	}
}
