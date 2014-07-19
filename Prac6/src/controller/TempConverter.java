package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.ConvertUtility;
import view.MyFrame;

public class TempConverter {

	public static void main(String[] args) {
		String[] choices = new String[] {"F to C", "C to F", "in to m", "m to in"};
		final MyFrame mainFrame = new MyFrame("Unit Converter");
		final JButton bttn = new JButton("Calculate");
		final JTextField txtField = new JTextField(20);
		final JComboBox<String> comBox = new JComboBox<String>(choices);
		mainFrame.add(bttn, BorderLayout.SOUTH);
		mainFrame.add(txtField);
		mainFrame.add(comBox, BorderLayout.NORTH);
		mainFrame.pack();
		
		bttn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double unit;
				String result = "";
				try {
					String[] string = txtField.getText().split(" ");
					unit = Double.parseDouble(string[0]);
					if (!Double.isNaN(unit)) {
						int selected = comBox.getSelectedIndex();
						
						switch (selected) {
						case 0:
							unit = ConvertUtility.fahrenheitToCelsius(unit);
							result = String.format("%.2f \u00B0C", unit);
							break;
						case 1:
							unit = ConvertUtility.celciusToFahrenheit(unit);
							result = String.format("%.2f \u00B0F", unit);
							break;
						case 2:
							unit = ConvertUtility.inchesToMetres(unit);
							result = String.format("%.2f m", unit);
							break;
						case 3:
							unit = ConvertUtility.metresToInches(unit);
							result = String.format("%.2f inches", unit);
						}
						txtField.setText(result);
					}
					
				} catch (Exception e2) {
					txtField.setText("NaN");
				}
			}
		});
	}

}
