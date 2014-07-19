package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MainFrame(GridDisplay gridDisplay) {
		setTitle("LCD Graphics Maker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel statusBar = new JPanel(new GridLayout());
		statusBar.setBackground(Color.WHITE);
		
		setResizable(false);

		add(statusBar, BorderLayout.SOUTH);
		add(gridDisplay);
	}
}
