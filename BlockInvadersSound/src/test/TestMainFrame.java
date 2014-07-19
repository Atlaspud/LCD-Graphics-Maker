package test;

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
import au.edu.jcu.view.GameDisplay;
import au.edu.jcu.view.MainFrame;
import au.edu.jcu.view.ScoresView;

public class TestMainFrame {
	final static ArrayList<Integer> spawnCount = new ArrayList<>();
	static GameState game = new GameState();
	static BlockField gameField = new BlockField(new Dimension(800,600));
    static GameDisplay gameDisplay = new GameDisplay(gameField,new Dimension(800,600));
	static MainFrame mainFrame = new MainFrame(gameDisplay);
	static Timer timer;
	static ScoresView scoresView;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setup();
			}
		});
	}
	
	private static void setup() {
		spawnCount.add(100);
		mainFrame.setScore(String.valueOf(game.getScore()));
		mainFrame.setLevel(String.valueOf(game.getLevel()));
		mainFrame.setJMenuBar(setupMenuBar());
		mainFrame.setStatus("Ready...");
		
		gameDisplay.setFocusable(true);
			
		gameDisplay.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					gameField.shipFired();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					gameField.pushShip(-1);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					gameField.pushShip(1);
				}
			}
		});
		
		timer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (spawnCount.get(0) <= ((game.getLevel() - 1) * 5)) {
					gameField.spawnEnemies(game.getLevel());
					spawnCount.set(0, 100);
				} else {
					spawnCount.set(0, spawnCount.get(0) - 1);
				}
				gameField.update(game);
				if (gameField.isGameOver()) {
					mainFrame.setStatus("game over!");
					timer.stop();
				}
				mainFrame.setScore(String.valueOf(game.getScore()));
				mainFrame.setLevel(String.valueOf(game.getLevel()));
				gameDisplay.repaint();
				
			}
		});
		mainFrame.pack();
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
		final JMenuItem addMenuItem = new JMenuItem("Add");
		JMenuItem aboutMenuItem = new JMenuItem("About");
	
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
					scoresView = new ScoresView(getScoresText());
					scoresView.setVisible(true);
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
	
	private static List<String> getScoresText(){
		List<String> scores = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File("Scores.txt"));
			while (scanner.hasNextLine()) {
				scores.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			scores.add("No scores saved yet");
		}
		return scores;
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
		gameField.reset();
		game.reset();
		mainFrame.setScore(String.valueOf(game.getScore()));
		mainFrame.setLevel(String.valueOf(game.getLevel()));
		timer.start();
	}
	
	private static void addScores() {
		if (game.getScore() == 0) {
			JOptionPane.showMessageDialog(mainFrame, "Can't add 0 score", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String name = JOptionPane.showInputDialog("Enter Name");
			if (!name.isEmpty() && (name.split(" ").length == 1)) {
				save("Scores.txt", String.format("Name: %s Level: %d Score: %d",
						name, game.getLevel(), game.getScore()));
			} else {
				JOptionPane.showMessageDialog(mainFrame, "invalid player name");
			}
		}
	}
}
