package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.GridField;

public class GridDisplay extends JPanel {
	
	private static final long serialVersionUID = 1L;
	GridField gridField;

	public GridDisplay(GridField gridField, Dimension dimension) {
		setPreferredSize(dimension);
		this.gridField = gridField;
		setBackground(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gridField.draw(g);
	}

}
