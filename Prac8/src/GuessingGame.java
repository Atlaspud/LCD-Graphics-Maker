
public class GuessingGame {

	public static void main(String[] args) {
		Game game = new Game();
		IntegerRecognizer recognizer = new IntegerRecognizer();
		recognizer.setDuration(3);

		game.start(1, 20);
		System.out.println(game.stateChallenge());

		while (! game.over()) {
			System.out.print("what's your guess? ... ");
			recognizer.listen();

			if (recognizer.understood()) {
				//System.out.print(recognizer.heard());
				int value = recognizer.getInt();
				String response = game.guess(value);
				System.out.println(" " + response);
				if (game.won()) {
					break;
				}
			} else {
				System.out.println("what?");
			}
		}
		
		System.out.println(String.format("you made %d guesses", game.guesses()));
		System.out.println("the secret number was... " + game.getSecret());
	}
	
//	private static int getInt(String text) {
//		try {
//			return Integer.parseInt(text);
//		} catch (NumberFormatException e) {
//			return -1;
//		}
//	}
}
