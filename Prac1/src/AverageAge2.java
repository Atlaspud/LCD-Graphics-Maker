import java.util.Scanner;


public class AverageAge2 {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		float sum = 0;
		int count = 0;
		
		System.out.print("Enter: ");
		float number = input.nextFloat();
		while (number != -1){
			sum += number;
			count += 1;
			System.out.print("Enter: ");
			number = input.nextFloat();
		}
		if (sum != 0){
			System.out.println("The average is: " + String.format("%.2f",sum/count));
		}
		input.close();
	}

}
