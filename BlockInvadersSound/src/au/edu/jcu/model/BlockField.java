package au.edu.jcu.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class BlockField {
	private MovableBlock ship;
	private ArrayList<Block> barriers;
	private ArrayList<MovableBlock> enemies;
	private ArrayList<MovableBlock> bullets;
	private Dimension fieldSize;
	private boolean gameOver;
	
	public BlockField(Dimension size) {
		fieldSize = size;
		ship = new MovableBlock();
		ship.size.setSize(0, 0);
		barriers = new ArrayList<Block>();
		enemies = new ArrayList<MovableBlock>();
		bullets = new ArrayList<MovableBlock>();
	}
	
	public void reset() {
		enemies.clear();
		bullets.clear();
		barriers.clear();
		ship.size.setSize(60, 20);
		ship.speed = 0;
		ship.position.setLocation(fieldSize.width/2 - 30, fieldSize.height - 20);
		
		// initialise barrier code//
		double divideField = (fieldSize.width-20*9*3)/4;
		
		for (int group = 0; group < 3; group++) {
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 9; j++) {
					Block barrier = new Block();
					barrier.size.setSize(20, 20);
					barrier.position.setLocation(divideField + 
							(20*9 + divideField) * group + 20 * j, fieldSize.height - 100 - 20 * i);
					barrier.colour = randomColour();
					barriers.add(barrier);
				}
			}
		}
		startGame();
	}
	
	public void startGame() {
		this.gameOver = false;
	}
	
	public boolean isGameOver() {
		return this.gameOver;
	}
	
	public void spawnEnemies(int speed) {
		MovableBlock newEnemy = new MovableBlock();
		newEnemy.speed = speed;
		newEnemy.setDuration(1000);
		newEnemy.position.setLocation(0, 30);
		newEnemy.size.setSize(30, 30);
		newEnemy.xDirection = MovableBlock.RIGHT;
		enemies.add(newEnemy);
	}
	
	public void draw(Graphics g) {
		if (ship.position.x < 0) {
			ship.position.x = 0;
		} else if (ship.position.x + ship.size.width > fieldSize.width) {
			ship.position.x = fieldSize.width - ship.size.width;
		}
		ship.draw(g);
		
		for (Block barrier : barriers) {
			barrier.draw(g);
		}
		
		for (MovableBlock bullet : bullets) {
			bullet.draw(g);
		}
		
		for (MovableBlock enemy : enemies) {
			enemy.draw(g);
		}
	}
	
	public void pushShip(int pushDirection) {
		if (ship.hasStopped()) {
			ship.setDuration(30);
			ship.speed = 1;
			ship.xDirection = pushDirection;
		} else if (ship.position.x + 60 > fieldSize.width - 1) {
			ship.stop();
		}else if (ship.position.x < 1) {
			ship.stop();
		} else {
			ship.setDuration(30);
			if (pushDirection == ship.xDirection) {
				ship.speed++;
			} else {
				ship.speed--;
			}
		}
	}
	
	public void shipFired() {
		MovableBlock newBullet = new MovableBlock();
		newBullet.size.setSize(3, 10);
		newBullet.colour = Color.RED;
		newBullet.position.setLocation(ship.position.x + 29,ship.position.y - 10);
		newBullet.setDuration(100);
		newBullet.speed = 10;
		newBullet.yDirection = MovableBlock.UP;
		bullets.add(newBullet);
	}
	
	public void update(GameState state) {

		// Bullet Impact and move
		ship.move();
		for (int bulletIndex = bullets.size() - 1; bulletIndex >= 0; bulletIndex--) {
			bullets.get(bulletIndex).move();
			if (bullets.get(bulletIndex).hasStopped()) {
				bullets.remove(bulletIndex);
				break;
			} else if (bullets.get(bulletIndex).hit(ship)) {
				gameOver = true;
				break;
			}
			boolean bulletRemoved = false;
			for (int barrierIndex = barriers.size() - 1; barrierIndex >= 0; barrierIndex--) {
				if (bullets.get(bulletIndex).hit(barriers.get(barrierIndex))) {
					barriers.remove(barrierIndex);
					bullets.remove(bulletIndex);
					bulletRemoved = true;
					break;
				}
			}
			if (bulletRemoved) break;
			for (int enemyIndex = enemies.size() - 1; enemyIndex >= 0; enemyIndex--) {
				if (bullets.get(bulletIndex).yDirection == -1 && 
						bullets.get(bulletIndex).hit(enemies.get(enemyIndex))) {
					enemies.remove(enemyIndex);
					bullets.remove(bulletIndex);
					state.increaseScore(1);
					bulletRemoved = true;
					break;
				}
			}
			if (bulletRemoved) break;
			if (bullets.size() < 2) break;
			for (int bulletSecondIndex = bulletIndex - 1; bulletSecondIndex >= 0; bulletSecondIndex--) {
				if (bullets.get(bulletSecondIndex).hit(bullets.get(bulletIndex))) {
					bullets.remove(bulletIndex);
					bullets.remove(bulletSecondIndex);
					bulletIndex--;
					break;
				}
			}
		}
	
		// enemy move and random fire
		for (MovableBlock enemy : enemies) {
			if (enemy.hit(ship)) {
				gameOver = true;
				break;
			} else if (enemy.position.x <=  -30 || enemy.position.x >= fieldSize.width) {
				enemy.position.y += 60;
				enemy.xDirection *= -1;
				enemy.setDuration(1000);
			}
			for (int barrierIndex = barriers.size() - 1; barrierIndex >= 0; barrierIndex--) {
				if (barriers.get(barrierIndex).hit(enemy)) {
					barriers.remove(barrierIndex);
					break;
				}
			}
			enemyRandomFire(enemy);
			enemy.move();
		}
	}
	
	private Color randomColour() {
		Random random = new Random();
		int randomChoice = random.nextInt(9);
		switch (randomChoice) {
		case 0:
			return Color.blue;
		case 1:
			return Color.green;
		case 2:
			return Color.gray;
		case 3:
			return Color.magenta;
		case 4:
			return Color.orange;
		case 5:
			return Color.pink;
		case 6:
			return Color.yellow;
		case 7:
			return Color.red;
		case 8:
			return Color.cyan;
		default:
			return Color.white;
		}
	}
	
	private void enemyRandomFire(MovableBlock enemy) {
		Random randomNum = new Random();
		if (randomNum.nextInt(200) == 20) {
			MovableBlock newBullet = new MovableBlock();
			newBullet.size.setSize(6, 10);
			newBullet.colour = Color.RED;
			newBullet.position.setLocation(enemy.position.x + 12,enemy.position.y + 30);
			newBullet.setDuration(150);
			newBullet.speed = 2;
			newBullet.yDirection = MovableBlock.DOWN;
			bullets.add(newBullet);
		}
	}

}
