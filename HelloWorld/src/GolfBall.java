
public class GolfBall extends Ball {
	int height;
	final static float elasticity = 0.5f; 
	GolfBall(int height) {
		super(height);
	}
	void bounce() {
	super.height *= elasticity;
	}
	public static void main(String[] args) {
	Ball ball = new GolfBall(8); 
	ball.bounce(); 
	System.out.println(ball.position());
	}

}
