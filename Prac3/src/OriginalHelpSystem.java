import java.util.Scanner;


public class OriginalHelpSystem {
	
	public static void main(String[] args) {
		displayMenu();
		int choice = getChoice();
		switch (choice) {
		case 1:
			System.out.println("A reference variable points to information "
					+ "regarding an object in the memory table.");
			break;
		case 2:
			System.out.println("The main method is what is excecuted in the class.");
			break;
		case 3:
			System.out.println("The 8 primitive types are: "
					+ "byte, char, boolean, short, int, long, float, double.");
			break;
		case 4:
			System.out.println("Exit");
			break;
		}
	}

	private static int getChoice() {
		System.out.print("Choice: ");
		int choice;
		Scanner scanner = new Scanner(System.in);
		do {
			while (!scanner.hasNextInt()) {
				scanner.next();
				System.out.print("Try again: ");
			}
			choice = scanner.nextInt();
		} while (choice < 1 | choice > 4);
		scanner.close();
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
