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
			if(this.x == temp) 		g2.drawString("B1", x+20, y-10);
			if(this.x == temp+55) 	g2.drawString("B2", x+20, y-10);
			if(this.x == temp+55*2) g2.drawString("B3", x+20, y-10);
			if(this.x == temp+55*3) g2.drawString("B4", x+20, y-10);
			if(this.x == temp+55*4) g2.drawString("B5", x+20, y-10);
			if(this.x == temp+55*5) g2.drawString("B6", x+20, y-10);
		}
		if(this.y == MancalaView.BOTTOM_Y) {
			if(this.x == temp) 		g2.drawString("A1", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55) 	g2.drawString("A2", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55*2) g2.drawString("A3", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55*3) g2.drawString("A4", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55*4) g2.drawString("A5", x+20, y+MancalaView.PIT_HEIGHT+15);
			if(this.x == temp+55*5) g2.drawString("A6", x+20, y+MancalaView.PIT_HEIGHT+15);
		}
		
		//Print number of stones in pit 
		if(this.y == MancalaView.TOP_Y) {
			g2.drawString(" " + totalStones, x+20, y+MancalaView.PIT_HEIGHT+15); 
			g2.setColor(color);
		}
		if(this.y == MancalaView.BOTTOM_Y) {
			g2.drawString(" " + totalStones, x+20, y-10);
			g2.setColor(color);
		}
		
		//Display stones in pit (NOT FINISHED)
		int counter = 1; //counter for number of stones already displayed in pit
		double xcoord = this.x + 10;
		double ycoord = this.y + 10;
		for(int i = 0; i < totalStones; i++) {
			System.out.println(i);
			if(counter > 10) { //stop displaying stones over 10
				return;
			}
			if(counter % 2 == 0) {
				xcoord = this.x + 10;
				ycoord = this.y + 25;
			}
			else if(counter % 2 == 1) {
				xcoord = this.x + 30;
				ycoord = this.y + 20;
			}
			Ellipse2D.Double stone = new Ellipse2D.Double(xcoord, ycoord, STONE_SIZE, STONE_SIZE);
			System.out.println("xcoord: " + xcoord);
			System.out.println("ycoord: " + ycoord);

			stonesInPit.add(stone);
			g2.setColor(Color.CYAN);
			g2.fill(stone);
			g2.draw(stone);
			g2.setColor(color);
			counter++;
			xcoord = this.x + 25;
			ycoord = this.y + 15;
		}
		
	}	
}
