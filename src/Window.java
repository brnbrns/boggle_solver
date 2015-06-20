/***************
* Brian Burns
* Window.java
* Provides GUI functionality
***************/

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Window extends JFrame implements ActionListener {
	private JLabel enterDimensions;
	private JLabel by;
	private JLabel enterBoard;
	private JLabel selectDict;
	
	private JButton dictButton;

	private JTextField rowEntry;
	private JTextField colEntry;
	private JTextField boardEntry;

	JFileChooser fc;

	public Window() {
		super("Boggle Solver");

		Container c = getContentPane();
		c.setLayout(null);

		enterDimensions = new JLabel("Please enter the board dimensions:");
		enterDimensions.setSize(300, 50);
		enterDimensions.setLocation(10, 2);
		c.add(enterDimensions);

		rowEntry = new JTextField("4");
		rowEntry.setSize(30, 20);
		rowEntry.setLocation(10, 40);
		rowEntry.setHorizontalAlignment(SwingConstants.CENTER);
		c.add(rowEntry);

		by = new JLabel("x");
		by.setSize(10, 50);
		by.setLocation(43, 25);
		c.add(by);

		colEntry = new JTextField("4");
		colEntry.setSize(30, 20);
		colEntry.setLocation(55, 40);
		colEntry.setHorizontalAlignment(SwingConstants.CENTER);
		c.add(colEntry);

		enterBoard = new JLabel("Now the game board, with spaces between letters, from top to bottom:");
		enterBoard.setSize(450, 50);
		enterBoard.setLocation(10, 55);
		c.add(enterBoard);

		boardEntry = new JTextField();
		boardEntry.setSize(200, 20);
		boardEntry.setLocation(10, 95);
		c.add(boardEntry);

		dictButton = new JButton("Select Dictionary File");
		dictButton.addActionListener(this);
		dictButton.setSize(200, 50);
		dictButton.setLocation(10, 130);
		c.add(dictButton);

		this.setSize(800, 600);
		this.setLocation(100, 100);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// User pressed select dictionary button
		if (e.getSource() == dictButton) {
			fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File dict = fc.getSelectedFile();
				try {
					readDictionary(dict);
				} catch (FileNotFoundException error) {
					error.printStackTrace();
				}
			}
		}
	}

	private CharacterTree readDictionary(File toRead) throws FileNotFoundException {
		CharacterTree dict = new CharacterTree();
		Scanner s = new Scanner(toRead);
		while (s.hasNextLine()) {
			String word = s.nextLine();
			dict.add(word);
		}
		s.close();
		return dict;
	}
}