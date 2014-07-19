import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import model.DrawableRectangleException;
import model.DrawableRectangles;
import view.DisplayPanel;


public class App {
	static DisplayPanel dispPanel;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("RectangleWorld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		final DrawableRectangles rectangle = new DrawableRectangles();
		dispPanel = new DisplayPanel(rectangle);
		JButton addBtn = new JButton("add");
		JButton removeBtn = new JButton("remove");
		final JTextArea textArea = new JTextArea(50,20);
		JScrollPane scroller = new JScrollPane(textArea);
		
		
		
		panel.add(addBtn);
		panel.add(removeBtn);
		frame.add(dispPanel);
		frame.add(scroller, BorderLayout.WEST);
		frame.add(panel, BorderLayout.SOUTH);
		
		ActionListener bttnAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand()) {
				case "add":
					try {
						rectangle.add();
					} catch (DrawableRectangleException e2) {
						textArea.insert(e2.getMessage(), 0);
					}
					break;
				case "remove":
					try {
						rectangle.remove();
					} catch (DrawableRectangleException e1) {
						textArea.insert(e1.getMessage(), 0);
					}
					break;
				}
				
			}
		};
		
		addBtn.addActionListener(bttnAction);
		removeBtn.addActionListener(bttnAction);
		
		Timer timer = new Timer(10,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispPanel.repaint();
				rectangle.move();
				rectangle.bounce(dispPanel.getWidth(), dispPanel.getHeight());
			}
		});
		timer.start();
		
		
		
		
		frame.setVisible(true);
		frame.setSize(new Dimension(600,400));
	}

}
