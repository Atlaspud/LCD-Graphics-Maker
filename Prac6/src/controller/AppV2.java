package controller;

import javax.swing.JLabel;

import view.MyFrame;

public class AppV2 {

	public static void main(String[] args) {
		MyFrame mainFrame = new MyFrame("Hello world");
		
		JLabel label = new JLabel("Hello world!");
		mainFrame.add(label);
		mainFrame.pack();
	}

}
