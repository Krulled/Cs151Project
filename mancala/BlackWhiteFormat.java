package mancala;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * The BlackWhiteFormat class is a Strategy pattern that styles the board with a black and white theme. 
 */

public class BlackWhiteFormat implements MancalaFormatter{

	public Color formatStoneColor() {
		return Color.BLACK;
	}

	public Color formatOutlineColor() {
		return Color.BLACK;
	}
	
	public Color formatBoardBackground() {
		return Color.WHITE;
	}

	public Shape formatBoardShape() {
		return new Rectangle2D.Double(10, 10, MancalaView.BOARD_WIDTH, MancalaView.BOARD_HEIGHT);
	}

	public Shape formatPitShape(Pit pit) {
		return new Rectangle2D.Double(pit.getX(), pit.getY(), pit.getWidth(), pit.getHeight());
	}

}
