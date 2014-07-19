package test;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;
//
//import au.edu.jcu.model.Block;
//import au.edu.jcu.model.GameState;
//import au.edu.jcu.model.MovableBlock;
//
public class CopyOfBlockField {
//	private MovableBlock ship;
//	private ArrayList<Block> barriers;
//	private ArrayList<MovableBlock> enemies;
//	private ArrayList<MovableBlock> bullets;
//	private Dimension fieldSize;
//	private boolean gameOver;
//	
//	public CopyOfBlockField(Dimension size) {
//		fieldSize = size;
//		ship = new MovableBlock();
//		ship.size.setSize(0, 0);
//		barriers = new ArrayList<Block>();
//		enemies = new ArrayList<MovableBlock>();
//		bullets = new ArrayList<MovableBlock>();
//	}
//	
//	public void reset() {
//		enemies.clear();
//		bullets.clear();
//		barriers.clear();
//		ship.size.setSize(60, 20);
//		ship.speed = 0;
//		ship.position.setLocation(fieldSize.width/2 - 30, fieldSize.height - 20);
//		
//		// initialise barrier code//
//		double divideField = (fieldSize.width/20 - 9*3)/4*20;
//		for (int group = 0; group < 3; group++) {
//			for (int i = 0; i < 7; i++) {
//				for (int j = 0; j < 9; j++) {
//					Block barrier = new Block();
//					barrier.size.setSize(20, 20);
//					barrier.position.setLocation(divideField + (divideField + 9*20) * group  + 20 * j, fieldSize.height - 100 - 20 * i);
//					barrier.colour = randomColour();
//					barriers.add(barrier);
//				}
//			}
//		}
//		startGame();
//	}
//	
//	public void startGame() {
//		this.gameOver = false;
//	}
//	
//	public boolean isGameOver() {
//		return this.gameOver;
//	}
//	
//	public void spawnEnemies(int speed) {
//		MovableBlock newEnemy = new MovableBlock();
//		newEnemy.speed = speed;
//		newEnemy.setDuration(1000);
//		newEnemy.position.setLocation(0, 30);
//		newEnemy.size.setSize(30, 30);
//		newEnemy.xDirection = MovableBlock.RIGHT;
//		enemies.add(newEnemy);
//	}
//	
//	public void draw(Graphics g) {
//		if (ship.position.x < 0) {
//			ship.position.x = 0;
//		} else if (ship.position.x + ship.size.width > fieldSize.width) {
//			ship.position.x = fieldSize.width - ship.size.width;
//		}
//		ship.draw(g);
//		
//		for (Block barrier : barriers) {
//			barrier.draw(g);
//		}
//		
//		for (MovableBlock bullet : bullets) {
//			bullet.draw(g);
//		}
//		
//		for (MovableBlock enemy : enemies) {
//			enemy.draw(g);
//		}
//	}
//	
//	public void pushShip(int pushDirection) {
//		if (ship.position.x < 0) {
//			ship.stop();
//		} else if (ship.position.x + 60 > fieldSize.width) {
//			ship.stop();
//		}else if (ship.hasStopped()) {
//			ship.setDuration(30);
//			ship.speed = 1;
//			ship.xDirection = pushDirection;
//		} else {
//			ship.setDuration(30);
//			if (pushDirection == ship.xDirection) {
//				ship.speed++;
//			} else {
//				ship.speed--;
//			}
//		}
//	}
//	
//	public void shipFired() {
//		MovableBlock newBullet = new MovableBlock();
//		newBullet.size.setSize(3, 10);
//		newBullet.colour = Color.RED;
//		newBullet.position.setLocation(ship.position.x + 29,ship.position.y - 10);
//		newBullet.setDuration(100);
//		newBullet.speed = 10;
//		newBullet.yDirection = MovableBlock.UP;
//		bullets.add(newBullet);
//	}
//	
//	public void update(GameState state) {
//		ArrayList<Integer> bulletRemoveList = new ArrayList<Integer>();
//		ArrayList<Integer> barrierRemoveList = new ArrayList<Integer>();
//		ArrayList<Integer> enemyRemoveList = new ArrayList<Integer>();
//		// Bullet Impact and move
//		for (int bulletIndex = 0; bulletIndex < bullets.size(); bulletIndex++) {
//			if (bullets.get(bulletIndex).hasStopped()) {
//				bulletRemoveList.add(bulletIndex);
//				break;
//			}
//			boolean bulletRemoved = false;
//			for (int barrierIndex = 0; barrierIndex < barriers.size(); barrierIndex++) {
//				if (bullets.get(bulletIndex).hit(barriers.get(barrierIndex))) {
//					bulletRemoveList.add(bulletIndex);
//					barrierRemoveList.add(barrierIndex);
//					bulletRemoved = true;
//					break;
//				}
//			}
//			
//			if (bulletRemoved) {
//				break;
//			} else if (bullets.get(bulletIndex).yDirection == MovableBlock.UP) {
//				for (int enemyIndex = 0; enemyIndex < enemies.size(); enemyIndex++) {
//					if (bullets.get(bulletIndex).hit(enemies.get(enemyIndex))) {
//						bulletRemoveList.add(bulletIndex);
//						enemyRemoveList.add(enemyIndex);
//						bulletRemoved = true;
//						break;
//					}
//				}
//			}
//			if (bulletRemoved) {
//				break;
//			} else {
//				for (int bulletSecondIndex = bulletIndex + 1; bulletSecondIndex < bullets.size(); bulletSecondIndex++) {
//					if (bullets.get(bulletIndex).hit(bullets.get(bulletSecondIndex))) {
//						bulletRemoveList.add(bulletIndex);
//						bulletRemoveList.add(bulletSecondIndex);
//						bulletRemoved = true;
//						break;
//					}
//				}
//			}
//			if (bulletRemoved) {
//				break;
//			} else if (bullets.get(bulletIndex).hit(ship)) {
//				gameOver = true;
//				break;
//						
//			}
//			bullets.get(bulletIndex).move();
//		}
//		// Enemy ship and barrier impact
//		for (int enemyIndex = 0; enemyIndex < enemies.size(); enemyIndex++) {
//			if (enemies.get(enemyIndex).hit(ship)) {
//				gameOver = true;
//				break;
//			}
//			for (int barrierIndex = 0; barrierIndex < barriers.size(); barrierIndex++) {
//				if (enemies.get(enemyIndex).hit(barriers.get(barrierIndex))) {
//					barrierRemoveList.add(barrierIndex);
//					break;
//				} 
//			}
//		}
//			
//		// bullet removal update
//		removeArrayItems(bullets, bulletRemoveList);
//		
//		// barrier removal update
//		removeArrayItems(barriers, barrierRemoveList);
//		
//		// enemy removal update and score update
//		int enemiesDestoryed = removeArrayItems(enemies, enemyRemoveList);
//		state.increaseScore(enemiesDestoryed);
//
//		// enemy move and random fire
//		for (MovableBlock enemy : enemies) {
//			if (enemy.position.x < 0 || enemy.position.x + 30 > fieldSize.width) {
//				enemy.position.y += 60;
//				enemy.xDirection *= -1;
//				enemy.setDuration(1000);
//			}
//			enemyRandomFire(enemy);
//			enemy.move();
//		}
//		ship.move();
//	}
//	
//	private Color randomColour() {
//		Random random = new Random();
//		int randomChoice = random.nextInt(9);
//		switch (randomChoice) {
//		case 0:
//			return Color.blue;
//		case 1:
//			return Color.green;
//		case 2:
//			return Color.gray;
//		case 3:
//			return Color.magenta;
//		case 4:
//			return Color.orange;
//		case 5:
//			return Color.pink;
//		case 6:
//			return Color.yellow;
//		case 7:
//			return Color.red;
//		case 8:
//			return Color.cyan;
//		default:
//			return Color.white;
//		}
//	}
//	
//	@SuppressWarnings("rawtypes")
//	private int removeArrayItems(ArrayList array,ArrayList<Integer> index) {
//		int removed = 0;
//		if (index.size() != 0) {
//			Collections.sort(index);
//			for (int i = index.size() - 1; i >= 0; i--) {
//				array.remove((int) index.get(i));
//				removed++;
//			}
//		}
//		return removed;
//	}
//	
//	private void enemyRandomFire(MovableBlock enemy) {
//		Random randomNum = new Random();
//		if (randomNum.nextInt(200) == 20) {
//			MovableBlock newBullet = new MovableBlock();
//			newBullet.size.setSize(6, 10);
//			newBullet.colour = Color.RED;
//			newBullet.position.setLocation(enemy.position.getX() + 12,enemy.position.getY() + 30);
//			newBullet.setDuration(150);
//			newBullet.speed = 2;
//			newBullet.yDirection = MovableBlock.DOWN;
//			bullets.add(newBullet);
//		}
//	}
//
}
