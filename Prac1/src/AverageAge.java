import java.util.Scanner;


public class AverageAge {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int sum = 0;
		
		for (int i = 0; i < 5; i++) {
			System.out.print("Enter: ");
			int number = input.nextInt();
			sum += number;
		}
		System.out.println("The average is: " + sum/5);
		input.close();
	}

}
