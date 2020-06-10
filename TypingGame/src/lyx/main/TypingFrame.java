package lyx.main;

import javax.swing.JFrame;

public class TypingFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	TypingFrame(){
		setBounds(300, 300, 400, 500);
		setTitle("TypingGame");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		TypePanel panel = new TypePanel();
		add(panel);
		addKeyListener(panel);
		Thread t = new Thread(panel);
		t.start();
	}
}
