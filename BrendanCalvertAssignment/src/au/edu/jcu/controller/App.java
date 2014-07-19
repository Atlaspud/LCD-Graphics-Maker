package au.edu.jcu.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import au.edu.jcu.model.BlockField;
import au.edu.jcu.model.GameState;
import au.edu.jcu.model.MovableBlock;
import au.edu.jcu.view.GameDisplay;
import au.edu.jcu.view.MainFrame;
import au.edu.jcu.view.ScoresView;

public class App {
	
	private static GameState game;
	static BlockField gameField;
    static GameDisplay gameDisplay;
	static MainFrame mainFrame;
	static Timer animationTimer;
	static ScoresView scoresView;
	static int enemySpawnDelay;
	static JMenuItem addMenuItem;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setup();
			}
		});
	}
	/* setup()
	 * 
	 * create BlockField, GameDisplay, MainFrame, and GameState objects
	 * set keyListener for GameDisplay object
	 * add menu to MainFrame object
	 * create animation timer
	 */
	
	private static void setup() {
		gameField = new BlockField(new Dimension(800,600));
		gameDisplay = new GameDisplay(gameField,new Dimension(800,600));
		mainFrame = new MainFrame(gameDisplay);
		game = new GameState();
		mainFrame.setGameState(game);
		mainFrame.setJMenuBar(setupMenuBar());
		mainFrame.setStatus("Ready...");
		
		gameDisplay.setFocusable(true);
			
		gameDisplay.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE:
					gameField.shipFired();
					break;
				case KeyEvent.VK_LEFT:
					gameField.pushShip(MovableBlock.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					gameField.pushShip(MovableBlock.RIGHT);
					break;
				}
			}
		});
		
		animationTimer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// decrease value of enemySpawnDelay by a facter of the current
				// game level, when it equals zero, spawn enemy and reset counter
				enemySpawnDelay -= game.getLevel();
				if (enemySpawnDelay <= 0) {
					gameField.spawnEnemies(game.getLevel());
					enemySpawnDelay = 100;
				}
				// updates the position of all block objects
				gameField.update(game);
				// Checks if the game is over, if game over, stop timer
				if (gameField.isGameOver()) {
					mainFrame.setStatus("game over!");
					addMenuItem.setEnabled(true);
					animationTimer.stop();
				}
				// updates score and level labels
				mainFrame.setGameState(game);
				gameDisplay.repaint();
				
			}
		});
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	/* setupMenuBar()
	 * 
	 * helper method which returns an object of type JMenuBar, the menubars options
	 * are configured and all actionlisteners for the menu are setup
	 * 
	 */
	private static JMenuBar setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenu scoreMenu = new JMenu("Scores");
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem playMenuItem = new JMenuItem("Play");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		JMenuItem viewMenuItem = new JMenuItem("View");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		
		addMenuItem = new JMenuItem("Add");
		addMenuItem.setEnabled(false);
	
		menuBar.add(gameMenu);
		menuBar.add(scoreMenu);
		menuBar.add(helpMenu);
		
		gameMenu.add(playMenuItem);
		gameMenu.add(exitMenuItem);
		scoreMenu.add(viewMenuItem);
		scoreMenu.add(addMenuItem);
		helpMenu.add(aboutMenuItem);
		
		ActionListener menuHandler = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
				case "Play":
					playGame();
					break;
				case "Exit":
					System.exit(0);
					break;
				case "View":
					viewScores();
					break;
				case "Add":
					addScores();
					break;
				case "About":
					JOptionPane.showMessageDialog(mainFrame, 
							"Brendan Calvert\n12459481", "About", JOptionPane.PLAIN_MESSAGE);
				}
			}
		};
		playMenuItem.addActionListener(menuHandler);
		exitMenuItem.addActionListener(menuHandler);
		viewMenuItem.addActionListener(menuHandler);
		addMenuItem.addActionListener(menuHandler);
		aboutMenuItem.addActionListener(menuHandler);
		return menuBar;
	}
	
	/* save()
	 * 
	 * helper method to save a string to a given filename, if filename exists text is added,
	 * if it does not exist, it is created and text is added.
	 */
	
	private static void save(String filename, String text) {
		try {
			FileWriter writer = new FileWriter(filename, true);
			writer.write(text + "\n");
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/* playGame()
	 * 
	 * helper method that initilizes gameField, game and starts the animation timer
	 */
	private static void playGame() {
		mainFrame.setStatus("good luck pilot!");
		gameField.reset();
		game.reset();
		mainFrame.setGameState(game);
		addMenuItem.setEnabled(false);
		animationTimer.start();
	}
	
	/* addScores()
	 * 
	 * checks if score is 0, if it is , no score can be added
	 * if it is not 0, the user is prompted with a input dialog to enter their name
	 * if no name is entered, or is more then one word, no score can be added
	 * if name is ok, name, level and score is saved to text file
	 */
	private static void addScores() {
		if (game.getScore() == 0) {
			JOptionPane.showMessageDialog(mainFrame, "Can't add 0 score", "Error", JOptionPane.ERROR_MESSAGE);
			addMenuItem.setEnabled(false);
		} else {
			try {
				String name = JOptionPane.showInputDialog("Enter Name");
				if (!name.isEmpty() && (name.split(" ").length == 1)) {
					save("scores.txt", String.format("Name: %s Level: %d Score: %d",
							name, game.getLevel(), game.getScore()));
					addMenuItem.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(mainFrame, "invalid player name", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				// do nothing
			}
		}
	}
	
	/* viewScores()
	 * 
	 * checks for scores text file, if not found an error dialog is displayed.
	 * if found each line of the text file is stored in a String list and a
	 * a ScoresView object is created
	 */
	private static void viewScores() {
		List<String> scores = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File("scores.txt"));
			while (scanner.hasNextLine()) {
				scores.add(scanner.nextLine());
			}
			scanner.close();
			scoresView = new ScoresView(scores);
			scoresView.setVisible(true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(mainFrame, "No scores saved yet", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
}
