package test;

import java.awt.Dimension;

import au.edu.jcu.model.BlockField;
import au.edu.jcu.view.GameDisplay;
import au.edu.jcu.view.MainFrame;

public class TestDisplay {
	public static void main(String[] args) {
		BlockField blockField = new BlockField(new Dimension(800,600));
		GameDisplay gameDisplay = new GameDisplay(blockField,new Dimension(800,600));
		MainFrame mainFrame = new MainFrame(gameDisplay);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}
