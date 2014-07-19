package view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(380,200));
	}

}
