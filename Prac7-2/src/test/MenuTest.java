package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuTest {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Menu Test");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu settings = new JMenu("Settings");
		JMenu colour = new JMenu("Colour");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem reset = new JMenuItem("Reset");
		JCheckBoxMenuItem red = new JCheckBoxMenuItem("Red");
		JCheckBoxMenuItem blue = new JCheckBoxMenuItem("Blue");
		ButtonGroup group = new ButtonGroup();
		group.add(red);
		group.add(blue);
		menuBar.add(settings);
		settings.add(colour);
		colour.add(red);
		colour.add(blue);
		settings.add(load);
		settings.add(reset);
		reset.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		
		ActionListener menuHandler = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You selected " + e.getActionCommand());
				
			}
		};
			
		load.addActionListener(menuHandler);
		reset.addActionListener(menuHandler);
		blue.addActionListener(menuHandler);
		red.addActionListener(menuHandler);

		frame.setVisible(true);
	}
}
