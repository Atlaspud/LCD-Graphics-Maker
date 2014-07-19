package test;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class JPanelTest {
	private static final Random num = new Random();

	public static void main(String[] args) {
		final Point point = new Point(0,0);
		JFrame mainFrame = new JFrame("TestJPanel");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final MyPanel panel = new MyPanel(point);
		mainFrame.add(panel);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		final Timer timer = new Timer(1, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						point.x = num.nextInt(500);
						point.y = num.nextInt(300);
						panel.repaint();
					}
				});
		panel.setFocusable(true);
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (!timer.isRunning()) {
						timer.start();
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_D) {
					if (timer.isRunning()) {
						timer.stop();
					}
				}
				
			}
		});
		
	}

}
