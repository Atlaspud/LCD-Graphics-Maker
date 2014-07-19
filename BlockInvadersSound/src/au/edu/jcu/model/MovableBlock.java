package au.edu.jcu.model;

public class MovableBlock extends Block {
	final public static int LEFT = -1;
	final public static int RIGHT = 1;
	final public static int UP = -1;
	final public static int DOWN = 1;
	
	protected int speed;
	protected int xDirection;
	protected int yDirection;
	private int duration;
	
	public boolean hasStopped() {
		return duration == 0 ? true : false;
	}
	
	public void move() {
		
		if (this.duration > 0) {
			position.x += (speed*xDirection);
			position.y += (speed*yDirection);
			this.duration--;
		}
	}
	
	public void setDuration(int amount) {
		this.duration = amount;
	}
	
	public void stop() {
		this.duration = 0;	
	}

}
