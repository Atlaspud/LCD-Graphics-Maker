package shape;

import java.awt.Point;

public class Shape2D {
	private Point position = new Point();
	public Shape2D() {
		this.position.setLocation(0,0);
	}
	
	public String getPosition() {
		return position.getX() + " " + position.getY();
	}
}
