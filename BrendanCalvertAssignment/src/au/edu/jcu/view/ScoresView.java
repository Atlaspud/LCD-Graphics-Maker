package au.edu.jcu.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScoresView extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextArea text;
	private JButton okay;
	private JScrollPane scrollPane;
	
	public ScoresView(List<String> text) {
		setSize(300,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(rootPane);
		
		this.text = new JTextArea();
		this.scrollPane = new JScrollPane(this.text);
		this.okay = new JButton();
		this.okay.setText("okay");
		this.text.setEditable(false);
		
		StringBuilder stringBuild = new StringBuilder();
		for (String element : text) {
			stringBuild.append(element + "\n");
		}
		
		this.text.setText(stringBuild.toString());
		this.text.setCaretPosition(0);
		
		add(this.scrollPane);
		add(this.okay, BorderLayout.SOUTH);
		
		okay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	

}
