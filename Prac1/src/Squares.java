
public class Squares {
	
	public final static int SQUARE_MAX = 20;
	
	public static void main(String[] args) {
		
		int sum = 0;
		int count = 0;
		
		for (int number = 1; number <= SQUARE_MAX; number++) {
			int square = number * number;
			sum += square;
			System.out.println("Square = " + square);
			count = number;
		}
		System.out.println("Sum = " + String.format("%2d",sum) + " Average = " + String.format("%2d",sum/count));
	}

}
