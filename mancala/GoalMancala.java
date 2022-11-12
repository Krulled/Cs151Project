package mancala;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class GoalMancala implements Hole{

	private int x;
	private int y;
	private int width;
	private int height;
	private int totalStones;
	private Color color;
	private Shape shape;
	private final int STONE_SIZE = 10;
	private ArrayList<Ellipse2D.Double> stonesInPit;

	public GoalMancala(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		totalStones = 0;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	
	public void addStone() {
		totalStones += 1;
	}
	
	public int getStones() {
		return totalStones;
	}
	
	public void addStones(int amount) {
		totalStones += amount;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void draw(Graphics2D g2) {
		g2.draw(shape);
		g2.setColor(color);
		
		if(this.x == MancalaView.BOARD_WIDTH-50) {
			g2.drawString("Mancala B", x-2, y-5);
		}
		else {
			g2.drawString("Mancala A", x-2, y-5);
		}
		
		if(totalStones > 0) {
			for(int i = 0; i < totalStones; i++) {
				double xcoord = this.x + 2;
				double ycoord = this.y + 2;
				if(xcoord == this.x + STONE_SIZE || ycoord == this.y + STONE_SIZE) {
					xcoord = this.x + 2;
					ycoord = this.y + 5;
				}
				Ellipse2D.Double stone = new Ellipse2D.Double(xcoord, ycoord, STONE_SIZE, STONE_SIZE);
				stonesInPit.add(stone);
				g2.draw(stone);
				g2.setColor(Color.BLACK);
				g2.fill(stone);
			}
		}
	}
}
