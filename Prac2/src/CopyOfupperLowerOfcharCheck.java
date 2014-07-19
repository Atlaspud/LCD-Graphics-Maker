import java.io.IOException;

public class CopyOfupperLowerOfcharCheck {
	public static void main(String[] args) throws IOException {
		System.out.print("Enter Sentence: ");
		String sentence = "";
		String character = "";
		do {
			character += (char) System.in.read();
			if (character == character.toLowerCase()){
				character = character.toUpperCase();
			} else{
				character = character.toLowerCase();
			}
			sentence += character;
			character = "";
		} while (!sentence.contains("."));
		System.out.println(sentence);
		System.out.println("The are " + (sentence.split(" ").length - 1) + " spaces.");
	}
}
