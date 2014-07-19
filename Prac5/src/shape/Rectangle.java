package shape;

public class Rectangle extends Shape2D {
	private int width;
	private int height;
	
	public Rectangle() {
		super();
		this.width = 10;
		this.height = 10;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}

}
