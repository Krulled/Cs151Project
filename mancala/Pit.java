package mancala;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Pit implements Hole{

	private int x;
	private int y;
	private int width;
	private int height;
	private int totalStones;
	private Shape shape;
	private Color color;
	private final int STONE_SIZE = 10;
	private ArrayList<Ellipse2D.Double> stonesInPit;
	
	public Pit(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		stonesInPit = new ArrayList<>();
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
	
	public void removeStone() {
		totalStones -= 1;
	}
	
	public void setStones(int amount) {
		totalStones = amount;
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
		
		//Display pit label
		int temp = MancalaView.PIT_WIDTH*2 + MancalaView.PIT_WIDTH/5;
		if(this.y == MancalaView.TOP_Y) {
			if(this.x == temp) 	g2.drawString("B1", x+20, y-10);
			if(this.x == temp+55) 	g2.drawString("B2", x+20, y-10);
			if(this.x == temp+55*2) g2.drawString("B3", x+20, y-10);
			if(this.x == temp+55*3) g2.drawString("B4", x+20, y-10);
			if(this.x == temp+55*4) g2.drawString("B5", x+20, y-10);
			if(this.x == temp+55*5) g2.drawString("B6", x+20, y-10);
		}
		if(this.y == MancalaView.BOTTOM_Y) {
			if(this.x == temp) 	g2.drawString("A1", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55) 	g2.drawString("A2", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55*2) g2.drawString("A3", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55*3) g2.drawString("A4", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55*4) g2.drawString("A5", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55*5) g2.drawString("A6", x+20, y+MancalaView.PIT_HEIGHT+15);
		}
		
		//Print number of stones in pit
		if(this.y == MancalaView.TOP_Y) {
			g2.drawString(" " + totalStones, x+20, y+MancalaView.PIT_HEIGHT+15); 

		}
		if(this.y == MancalaView.BOTTOM_Y) {
			g2.drawString(" " + totalStones, x+20, y-10);
		}
			
		
		//Display stones in pit
		int counter = 0; //counter for number of stones already displayed in pit
		for(int i = 0; i < totalStones; i++) {
			if(counter > 10) { //stop displaying stones over 10
				return;
			}
			double xcoord = this.x + 2;
			double ycoord = this.y + 2;
			if(xcoord == this.x + STONE_SIZE || ycoord == this.y + STONE_SIZE) {
				xcoord = this.x + 2;
				ycoord = this.y + 5;
			}
			Ellipse2D.Double stone = new Ellipse2D.Double(xcoord, ycoord, STONE_SIZE, STONE_SIZE);
			stonesInPit.add(stone);
			counter++;
			g2.setColor(Color.BLACK);
			g2.fill(stone);
			g2.draw(stone);
		}
	}
	
}
