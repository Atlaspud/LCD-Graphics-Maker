package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GridField;
import view.GridDisplay;
import view.MainFrame;

public class App {
	
	static GridField gridField;
	static GridDisplay gridDisplay;
	static MainFrame mainFrame;
	static Timer animationTimer;
	static MyListener myListener;
	static JMenuItem saveMenuItem;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setup();
			}
		});
	}
	
	private static void setup() {
		gridField = new GridField(new Dimension(672,384));
		gridDisplay = new GridDisplay(gridField,new Dimension(672,384));
		mainFrame = new MainFrame(gridDisplay);
		mainFrame.setJMenuBar(setupMenuBar());
		
		gridDisplay.setFocusable(true);
		
		myListener = new MyListener();
	    gridDisplay.addMouseListener(myListener);
	    gridDisplay.addMouseMotionListener(myListener);
		
		animationTimer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				// updates the position of all block objects
				gridDisplay.repaint();
				if (gridField.gridHasChanged()) {
					saveMenuItem.setEnabled(true);
				} else {
					saveMenuItem.setEnabled(false);
				}
				
			}
		});
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		animationTimer.start();
	}
	
	private static JMenuBar setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem newMenuItem = new JMenuItem("New");
		saveMenuItem = new JMenuItem("Save");
		JMenuItem loadMenuItem = new JMenuItem("Load");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
	
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		fileMenu.add(exitMenuItem);
		helpMenu.add(aboutMenuItem);
		
		saveMenuItem.setEnabled(false);
		
		ActionListener menuHandler = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
				case "New":
					startNew();
					break;
				case "Save":
					saveImage();
					break;
				case "Load":
					String data = loadFile();
					gridField.loadBlocks(data);
					break;
				case "About":
					JOptionPane.showMessageDialog(mainFrame, 
							"LCD Graphics Maker for use with AdaFruit Nokia 5110\n"
							+ "LCD Screens.\n\n\nCreated and distributed by,\n"
							+ "Brendan Calvert July 2014", "About", JOptionPane.PLAIN_MESSAGE);
					break;
				case "Exit":
					System.exit(0);
					break;
				}
			}
		};
		newMenuItem.addActionListener(menuHandler);
		saveMenuItem.addActionListener(menuHandler);
		loadMenuItem.addActionListener(menuHandler);
		aboutMenuItem.addActionListener(menuHandler);
		exitMenuItem.addActionListener(menuHandler);
		return menuBar;
	}
	
	/* save()
	 * 
	 * helper method to save a string to a given filename, if filename exists text is added,
	 * if it does not exist, it is created and text is added.
	 */
	private static void startNew() {
		gridField.startNew();
	}
	
	private static String loadFile() {
		StringBuilder data = new StringBuilder();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file","txt");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(null);
		if (result != JFileChooser.APPROVE_OPTION) return null;
		String path = chooser.getSelectedFile().getAbsolutePath();
		System.out.println(path);
		
		try {
			Scanner scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!line.isEmpty()) {
					line = line.replaceAll("\n", "");
					data.append(line);
				}
			}
			scanner.close();
		} catch (Exception e) {
			
		}
		return data.toString();
	}
	
	private static void saveImage() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("select folder");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		int result = chooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getAbsolutePath();
			System.out.println(path);
			gridField.save(path);
		}
	}
	
	private static class MyListener extends MouseInputAdapter {
	    public void mousePressed(MouseEvent e) {
	        gridField.update(e.getX(), e.getY());
	    }

	    public void mouseDragged(MouseEvent e) {
	    	gridField.updateDrag(e.getX(), e.getY());
	    }

	}
}
