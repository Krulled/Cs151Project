package mancala;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public interface Hole {
	
	int getX();
	int getY();
	int getWidth();
	int getHeight();
	void addStone();
	int getStones();
	void setShape(Shape s);
	void setColor(Color s);
	Shape getShape();
	void draw(Graphics2D g2);
	
}
