package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TestTimer {

	public static void main(String[] args) {
		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello world");
			}
		});
		timer.start();
//		Scanner input = new Scanner(System.in);
//		input.next();
//		timer.stop();
	}

}
