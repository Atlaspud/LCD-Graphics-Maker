package au.edu.jcu.model;

public class GameState {
	private  int currentScore;
	private int currentLevel;
	
	public GameState() {
		this.currentScore = 0;
		this.currentLevel = 1;
	}
	
	@Override
	public String toString() {
		return String.format("Level:%d Score:%d", this.currentLevel, this.currentScore);
	}
	
	public void reset() {
		this.currentScore = 0;
		this.currentLevel = 1;
	}
	
	public void increaseScore(int amount) {
		this.currentScore += amount * 10;
		if (this.currentScore > this.currentLevel*250) {
			this.currentLevel = this.currentScore/250 + 1;
		}
	}
	
	public int getScore() {
		return this.currentScore;
	}
	
	public int getLevel() {
		return this.currentLevel;
	}
	
}
