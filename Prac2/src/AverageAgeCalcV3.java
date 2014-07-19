import java.io.File;
import java.util.Scanner;


public class AverageAgeCalcV3 {

	private static float computeAverageAge(int repetitions, Scanner scanner){
		float total = 0;
		for (int i = 0; i < repetitions; i++) {
			int age = scanner.nextInt();
			total += age;
		}
		return total/repetitions;
	}
	
	public static void main(String[] args) {
		File file = null;
		Scanner scanner = null;
		try {
			file = new File("data.txt");
			scanner = new Scanner(file);
			float average = computeAverageAge(5, scanner);
			System.out.printf("Average age is %.2f", average);
			scanner.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
