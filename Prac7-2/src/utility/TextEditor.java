package utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.undo.UndoManager;

public class TextEditor {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Text Editor");
		
		final Document document = new PlainDocument();
		final UndoManager undoManager = new UndoManager();
		document.addUndoableEditListener(undoManager);
		JTextArea textArea = new JTextArea(document);
		textArea.setColumns(80);
		textArea.setRows(30);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu edit = new JMenu("Edit");
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem clearAll = new JMenuItem("Clear All");
		menuBar.add(edit);
		edit.add(undo);
		edit.add(redo);
		edit.add(clearAll);
		
		undo.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
		redo.setAccelerator(KeyStroke.getKeyStroke("ctrl shift Z"));
		
		frame.add(textArea);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Window closing... terminating program!");
				try {
					String text = document.getText(0, document.getLength());
					DocumentFile.save("document.txt", text);
				} catch (BadLocationException e1) {
				}
				System.exit(0);
			}
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					String text = DocumentFile.load("document.txt");
					document.insertString(0, text, null);
				} catch (FileNotFoundException | BadLocationException e1) {
				}
				
			}
		});
		
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				undoManager.undo();
			}
		});
		
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				undoManager.redo();
			}
		});
		
		clearAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				undoManager.die();
				try {
					document.remove(0, document.getLength());
				} catch (BadLocationException e1) {
				}
			}
		});
		
		
		frame.pack();
		frame.setVisible(true);
	}
}
