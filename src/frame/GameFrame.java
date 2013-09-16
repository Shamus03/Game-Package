package frame;

import game.*;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	String title;
	Game game;
	
	public GameFrame(String title, Game game) {	
		this.title = title;
		this.game = game;
		game.setFrame(this);
		
		setTitle(title);
		
		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(true);

		setContentPane(game);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		
		game.refreshSize();
	}
	
	public String getTitle() { return title; }
}
