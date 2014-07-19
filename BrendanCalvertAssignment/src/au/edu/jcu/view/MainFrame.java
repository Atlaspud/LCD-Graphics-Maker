package au.edu.jcu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import au.edu.jcu.model.GameState;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel status;
	private JLabel level;
	private JLabel score;
	
	public MainFrame(GameDisplay gameDisplay) {
		setTitle("Block Invaders");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		status = new JLabel("Status: ");
		level = new JLabel("Level: ");
		score = new JLabel("Score: ");
		JPanel statusBar = new JPanel(new GridLayout());
		statusBar.setBackground(Color.WHITE);
		
		setResizable(false);

		add(statusBar, BorderLayout.SOUTH);
		add(gameDisplay);
		statusBar.add(status, BorderLayout.EAST);
		statusBar.add(level, BorderLayout.CENTER);
		statusBar.add(score,BorderLayout.WEST);
	}
	
	public void setStatus(String status) {
		this.status.setText("Status: " + status);
	}
	
	public void setGameState(GameState state) {
		this.level.setText("Level: " + state.getLevel());
		this.score.setText("Score: " + state.getScore());
	}
}