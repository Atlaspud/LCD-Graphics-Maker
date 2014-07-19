package controller;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class AppV1 {
	
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("Hello world");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Hello world!");
		mainFrame.add(label);
		mainFrame.pack();
		
		mainFrame.setVisible(true);
	}

}
