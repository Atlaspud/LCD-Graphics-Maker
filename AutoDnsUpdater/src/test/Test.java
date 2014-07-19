package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.HTTPRequest;
import view.MyFrame;

public class Test {
	private static String passWord = "Bjcalve1";
	private static String hostName = "Atlascraft.darktech.org";
	static Timer timer;
	static String IPAddress;
	static int min = 0;
	static int sec = 0;
	static JLabel responseLbl;
	static JLabel lastChangedLbl;
	static JLabel currentAddress;

	public static void main(String[] args) throws IOException, InterruptedException {
		MyFrame frame = new MyFrame("Auto Dns Updater");
		JPanel bttnPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		
		JButton updateBtn = new JButton("Update");
		
		JLabel hostNameLbl = new JLabel("Domain: atlascraft.darktech.org");
		currentAddress = new JLabel();
		lastChangedLbl = new JLabel();
		final JLabel timerView = new JLabel();
		responseLbl = new JLabel();
		responseLbl.setSize(3, 10);
		
		
		bttnPanel.add(updateBtn);
		infoPanel.add(hostNameLbl);
		infoPanel.add(currentAddress);
		infoPanel.add(timerView);
		infoPanel.add(lastChangedLbl);
		infoPanel.add(responseLbl);
		frame.add(infoPanel, BorderLayout.CENTER);
		frame.add(bttnPanel, BorderLayout.SOUTH);
		
		IPAddress = HTTPRequest.url("http://myip.dtdns.com");
		currentAddress.setText(String.format("Current IP: %s", IPAddress));
		update();
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timerView.setText(String.format("Next Update: %02d:%02d",min,sec ));
				if (sec < 60) {
					sec++;
				} else if (min <= 30) {
					sec = 0;
					min++;
				} else {
					min = 0;
					sec = 0;
					update();
				}
			}
		});
		timer.start();
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				sec = 0;
				min = 0;
				
			}
		});
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static void update() {
		try {
			String newIPAddress = HTTPRequest.url("http://myip.dtdns.com");
			if (IPAddress != newIPAddress) {
				IPAddress = newIPAddress;
				String response = HTTPRequest.url("http://www.dtdns.com/api/autodns.cfm?"
						+ "id=" + hostName +"&pw=" + passWord + "&ip=" + IPAddress).trim();
				responseLbl.setText(response);
				currentAddress.setText(String.format("Current IP: %s", IPAddress));
				lastChangedLbl.setText(String.format("Last Changed: %s", new Date(System.currentTimeMillis())));
			}
		}catch (IOException a) {
				
		}
	}

}
