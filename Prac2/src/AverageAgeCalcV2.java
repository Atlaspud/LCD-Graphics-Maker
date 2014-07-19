import java.util.Scanner;


public class AverageAgeCalcV2 {

	static float computeAverageAge(int repetitions, Scanner keyboardInput){
		float total = 0;
		for (int i = 0; i < repetitions; i++) {
			System.out.print("Enter Age: ");
			int age = keyboardInput.nextInt();
			total += age;
		}
		return total/repetitions;
	}
	
	
	
	public static void main(String[] args) {
		Scanner keyboardInput = new Scanner(System.in);
		float average = computeAverageAge(5, keyboardInput);
		System.out.printf("Average age is %.2f", average);
		keyboardInput.close();
	}

}
