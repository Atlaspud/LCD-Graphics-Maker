import java.io.IOException;

public class charCheck {
	public static void main(String[] args) throws IOException {
		System.out.print("Enter Sentence: ");
		String sentence = "";
		do {
			sentence += (char) System.in.read();
		} while (!sentence.contains("."));
		System.out.println("The are " + (sentence.split(" ").length - 1) + " spaces.");
	}
}
