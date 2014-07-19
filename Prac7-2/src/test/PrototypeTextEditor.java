package test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.undo.UndoManager;

public class PrototypeTextEditor {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Text Editor");
		
		final Document document = new PlainDocument();
		final UndoManager undoManager = new UndoManager();
		document.addUndoableEditListener(undoManager);
		JTextArea textArea = new JTextArea(document);
		textArea.setColumns(80);
		textArea.setRows(30);
		
		frame.add(textArea);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Window closing... terminating program!");
				try {
					String text = document.getText(0, document.getLength());
					System.out.println("Text: " + text);
				} catch (BadLocationException e1) {
				}
				System.exit(0);
			}
		});
		
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					try {
						undoManager.undo();
					} catch (Exception e2) {
					}
				}
			}
		});
		
		document.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				System.out.println("removeUpdate");
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				System.out.println("insertUpdate");
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("changedUpdate");
			}
		});
		
		frame.pack();
		frame.setVisible(true);
		
	}
}
