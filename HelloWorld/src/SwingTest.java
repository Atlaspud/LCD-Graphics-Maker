import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class SwingTest {
	
	public static void main(String[] args) {
		final JFrame main = new JFrame("Hello");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		JButton button = new JButton("Press");
		main.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent argO) {
				main.setTitle(main.getTitle().equals("Hello") ? "World" : "Hello");
			}
		});
		
	}
}
