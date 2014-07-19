package test;

import au.edu.jcu.model.GameState;

public class TestGameState {

	public static void main(String[] args) {
		GameState game = new GameState();
		// Test 1
		System.out.printf("Test 1 initial state \n\t "
				+ "Expected: Level:1 Score:0 \n\t   Actual: %s\n", game);
		// Test 2
		game.increaseScore(3);
		System.out.printf("Test 2 increase score 3 times on level 1"
				+ "\n\t Expected: Level:1 Score:30 \n\t   Actual: %s\n", game);
		// Test 3
		game.increaseScore(25);
		System.out.printf("Test 3 increase score another 25 to reach level 2"
				+ " \n\t Expected: Level:2 Score:280 \n\t   Actual: %s\n", game);
	}

}
