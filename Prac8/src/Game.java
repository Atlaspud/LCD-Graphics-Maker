import java.util.Random;


public class Game {
	private Random random;
	private int secretNumber;
	private int guessesLeft;
	private int guessesMade;
	private boolean playerWon;
	private int minNumber, maxNumber;
	
	public Game() {
		random = new Random();
	}
	
	public void start(int min, int max) {
		minNumber = min;
		maxNumber = max;
		guessesMade = 0;
		guessesLeft = (max - min + 1)/3;
		secretNumber = min + (int) ((random.nextDouble() * (max - min)));
	}
	
	public String getSecret() {
		if (playerWon) {
			return "Do I really need to tell you?";
		}
		return String.valueOf(secretNumber);
	}
	
	public int guesses() {
		return guessesMade;
	}
	
	public Boolean over() {
		guessesLeft--;
		if (won()) {
			return true;
		} else if (lost()) {
			return true;
		}
		return false;
	}
	
	public Boolean lost() {
		if (guessesLeft <= 0) { 
			return true;
		}
		return false;
	}
	
	public boolean won() {
		return playerWon;
	}
	
	public String stateChallenge() {
		return String.format("Think of a number between %d and %d", maxNumber, minNumber);
	}
	
	public String guess(int guessValue) {
		guessesMade++;
		if (guessValue == secretNumber) {
			playerWon = true;
			return "You win!";
		} else if (guessValue < secretNumber) {
			return "No, try higher...";
		} else {
			return "No, try lower...";
		}
	}

}
