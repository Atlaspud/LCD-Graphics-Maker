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

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import au.edu.jcu.model.BlockField;
import au.edu.jcu.model.GameState;
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
	static int enemySpawnDelay = 200;
	static boolean backGroundOn = false;
	
	static JMenuItem addMenuItem;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setup();
			}
		});
	}
	
	private static void setup() {
		game = new GameState();
		gameField = new BlockField(new Dimension(800,600));
		gameDisplay = new GameDisplay(gameField,new Dimension(800,600));
		mainFrame = new MainFrame(gameDisplay);
		mainFrame.setScore(String.valueOf(game.getScore()));
		mainFrame.setLevel(String.valueOf(game.getLevel()));
		mainFrame.setJMenuBar(setupMenuBar());
		mainFrame.setStatus("Ready...");
		
		
			
		gameDisplay.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					gameField.shipFired();
					playSound("fireball.wav", false);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					gameField.pushShip(-1);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					gameField.pushShip(1);
				}
			}
		});
		
		animationTimer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				enemySpawnDelay -= game.getLevel();
				if (enemySpawnDelay <= 0) {
					gameField.spawnEnemies(game.getLevel());
					enemySpawnDelay = 100;
				}
				
				gameField.update(game);
				if (gameField.isGameOver()) {
					mainFrame.setStatus("game over!");
					addMenuItem.setEnabled(true);
					playSound("toad.wav", false);
					animationTimer.stop();
				}
				mainFrame.setScore(String.valueOf(game.getScore()));
				mainFrame.setLevel(String.valueOf(game.getLevel()));
				gameDisplay.repaint();
				
			}
		});
		mainFrame.pack();
		gameDisplay.setFocusable(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
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
	
	private static void save(String filename, String text) {
		try {
			FileWriter writer = new FileWriter(filename, true);
			writer.write(text + "\n");
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static void playGame() {
		mainFrame.setStatus("good luck pilot!");
		playSound("letsAGo.wav",false);
		if (!backGroundOn) {
			playSound("greenHills.mid", true);
			backGroundOn = true;
		}
		gameField.reset();
		game.reset();
		mainFrame.setScore(String.valueOf(game.getScore()));
		mainFrame.setLevel(String.valueOf(game.getLevel()));
		addMenuItem.setEnabled(false);
		animationTimer.start();
	}
	
	private static void addScores() {
		if (game.getScore() == 0) {
			JOptionPane.showMessageDialog(mainFrame, "Can't add 0 score", "Error", JOptionPane.ERROR_MESSAGE);
			addMenuItem.setEnabled(false);
		} else {
			String name = JOptionPane.showInputDialog("Enter Name");
			if (!name.isEmpty() && (name.split(" ").length == 1)) {
				save("scores.txt", String.format("Name: %s Level: %d Score: %d",
						name, game.getLevel(), game.getScore()));
				addMenuItem.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(mainFrame, "invalid player name", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
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
	
	private static void playSound(final String url, final boolean repeat) {
		
	    Thread sound = new Thread(new Runnable() { 
	    	
	                public void run() {
	                	boolean running = true;
	                    try {
	                    	AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	                    			getClass().getResource("/au/edu/jcu/resources/" + url));
	                    	Clip clip = AudioSystem.getClip();
	                        clip.open(inputStream);
	                        int length = clip.getFrameLength();
	                        if (repeat) {
	                        	FloatControl gainControl = 
		                        	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		                        	gainControl.setValue(-15.0f);
	                        	clip.loop(Clip.LOOP_CONTINUOUSLY);
	                        }
	                        clip.start();
	                        
	                        while (running) {
	                        	if (length == clip.getFramePosition()) {
	                        		clip.stop();
	                        		clip.flush();
	                        		inputStream.close();
	                        		running = false;
	                        	}
	                        }
	                        
	                        
	                    } catch (Exception fail) {
	                        
	                    }
	                }
	    });
	    sound.start();
	}
}
