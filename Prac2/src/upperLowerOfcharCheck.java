import java.io.IOException;

public class upperLowerOfcharCheck {
	public static void main(String[] args) throws IOException {
		System.out.print("Enter Sentence: ");
		String sentence = "";
		int character = 0;
		do {
			character = System.in.read();
			if (character >= 97 && character <= 122){
				character -= 32;
			} else if(character >= 65 && character <= 90){
				character +=32;
			}
			sentence += (char) character;
		} while (!sentence.contains("."));
		System.out.println(sentence);
		System.out.println("The are " + (sentence.split(" ").length - 1) + " spaces.");
	}
}
