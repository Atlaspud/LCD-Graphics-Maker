import java.util.Scanner;


public class AverageAgeCalc {

	public static void main(String[] args) {
		float total = 0;
		Scanner keyboardInput = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			System.out.print("Enter Age: ");
			int age = keyboardInput.nextInt();
			total += age;
		}
		System.out.printf("Average age is %.2f", total/5);
		keyboardInput.close();
	}

}
