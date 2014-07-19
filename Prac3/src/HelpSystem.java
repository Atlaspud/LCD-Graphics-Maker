import java.util.Scanner;


public class HelpSystem {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Helper helper = new Helper();
		int choice;
		do {
			String answer;
			displayMenu();
			choice = getChoice(scanner);
			switch (choice) {
			case 1:
				answer = helper.getTopicAnswers(1);
				System.out.println(answer);
				break;
			case 2:
				answer = helper.getTopicAnswers(2);
				System.out.println(answer);
				break;
			case 3:
				answer = helper.getTopicAnswers(3);
				System.out.println(answer);
				break;
			case 4:
				answer = helper.getTopicAnswers(4);
				System.out.println(answer);
				break;
			}
		} while (choice != 4);
		scanner.close();
	}

	private static int getChoice(Scanner scanner) {
		System.out.print("Choice: ");
		int choice;
		do {
			while (!scanner.hasNextInt()) {
				scanner.next();
				System.out.print("Try again: ");
			}
			choice = scanner.nextInt();
		} while (choice < 1 | choice > 4);
		//scanner.close();
		return choice;
	}

	private static void displayMenu() {
		System.out.println("My Java Help System (Version: 1.0)\n"
				+ "Topic list:\n"
				+ "\t1. What is a reference Variable?\n"
				+ "\t2. What is the main() method for?\n"
				+ "\t3. What are the 8 primitive types in Java?\n"
				+ "\t4. Exit");
	}

}
