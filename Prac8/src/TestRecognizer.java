import au.edu.jcu.it.Recognizer;

public class TestRecognizer {
	
	public static void main(String[] args) {
		Recognizer recognizer = new Recognizer();
		recognizer.setDuration(3);
		recognizer.listen();
		System.out.println("recognized: " + recognizer.heard());
	}
}
