package mancala;

import javax.swing.JFrame;

public class MancalaTest {
	
	public static void main(String[] args) {
		
		MancalaGame game = new MancalaGame();
				
		game.setTitle("Mancala Game");
		game.setSize(MancalaGame.FRAME_HEIGHT, MancalaGame.FRAME_WIDTH);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
	}
}
