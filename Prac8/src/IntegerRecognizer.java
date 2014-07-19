import au.edu.jcu.it.Recognizer;


public class IntegerRecognizer extends Recognizer {
	private int responseValue;
	private Boolean isInteger;
	
	@Override
	public void listen() {
		super.listen();
		if (super.understood()) {
			try {
				responseValue = Integer.parseInt(heard());
				isInteger = true;
			} catch (NumberFormatException e) {
				isInteger = false;
			}
		} else {
			isInteger = false;
		}
	}
	public int getInt() {
		return responseValue;
	}
	
	@Override
	public boolean understood() {
		return isInteger;
	}
}
