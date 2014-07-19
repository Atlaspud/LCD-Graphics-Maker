import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Helper {
	File file = null;
	Scanner scanner = null;
	String[] topicAnswers = new String[5];
	Helper() {
		try {
			file = new File("helper.txt");
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find text file.");
		}
		//From File
		for (int i = 1; scanner.hasNextLine(); i++) {
			topicAnswers[i] = scanner.nextLine() + "\n";
		}
		// Without File
//		topicAnswers[1] = "A reference variable points to information "
//				+ "regarding an object in the memory table.\n";
//		topicAnswers[2] = "The main method is what is executed in the class.\n";
//		topicAnswers[3] = "The 8 primitive types are: "
//				+ "byte, char, boolean, short, int, long, float, double.\n";
//		topicAnswers[4] = "Exit";
	}
	String getTopicAnswers(int topic) {
		return topicAnswers[topic];
	}
	

}
