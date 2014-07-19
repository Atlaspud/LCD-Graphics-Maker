
public class mathCheck {

	public static void main(String[] args) {
		int count = 0;
		
		for (int i = 0; i < 1000; i++) {
			double x = Math.random();
			if (x > 0.5){
				count++;
			}
		}
		System.out.println("There are " + count + " guesses over 0.5");
	}

}
