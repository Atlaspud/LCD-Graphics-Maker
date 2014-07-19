package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import model.GridField;
import view.GridDisplay;
import view.MainFrame;

public class App {
	
	static GridField gridField;
	static GridDisplay gridDisplay;
	static MainFrame mainFrame;
	static Timer animationTimer;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setup();
			}
		});
	}
	
	private static void setup() {
		gridField = new GridField(new Dimension(800,600));
		gridDisplay = new GridDisplay(gridField,new Dimension(800,600));
		mainFrame = new MainFrame(gridDisplay);
		
		gridDisplay.setFocusable(true);
		gridField.reset();
		
		gridDisplay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX());
				System.out.println(e.getY());
				gridField.update(e.getX(), e.getY());
			}
		});
		
		animationTimer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				// updates the position of all block objects
				gridDisplay.repaint();
				
			}
		});
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		gridField.generateFile();
		animationTimer.start();
	}

}
