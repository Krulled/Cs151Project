package mancala;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Pit implements Hole{

	private int x;
	private int y;
	private int width;
	private int height;
	private int totalStones;
	private final int STONE_SIZE = 10;
	private int id;
	
	private Shape shape;
	private Color color;
	private Color stoneColor;
	private ArrayList<Ellipse2D.Double> stonesInPit;
	
	public Pit(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		stonesInPit = new ArrayList<>();
		totalStones = 0;
		id = -1;
	}
	
	public String toString() {
		String letter = "";
		if(this.y == MancalaView.TOP_Y) {
			letter = "B"; 
		}
		else if(this.y == MancalaView.BOTTOM_Y){
			letter = "A"; 
		}
		return "Pit ID: " + this.getId() + "\n" + "Pit Label: " + letter + (id+1);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public void setStoneColor(Color stoneColor) {
		this.stoneColor = stoneColor;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.draw(shape);
		
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
		
		//Display number of stones in pit
		if(this.y == MancalaView.TOP_Y) {
			g2.drawString(" " + totalStones, x+20, y+MancalaView.PIT_HEIGHT+15); 
		}
		if(this.y == MancalaView.BOTTOM_Y) {
			g2.drawString(" " + totalStones, x+20, y-10);
		}
		
		//Display stones in pit 
		int counter = 1; //counter for number of stones already displayed in pit
		double xcoord = this.x;
		double ycoord = this.y;
		stonesInPit.clear();
		for(int i = 0; i < totalStones; i++) {
			if(counter > 8) { //stop displaying stones over 8
				return;
			}
			if(counter % 2 == 0) { 		//EVEN STONE
				xcoord = this.x + 30;
				ycoord = (this.y + 10) + 6 * counter/1.8;
			}
			else if(counter % 2 == 1) { //ODD STONE
				xcoord = this.x + 20;
				ycoord = (this.y + 10) + 6 * counter/1.8;
			}

			Ellipse2D.Double stone = new Ellipse2D.Double(xcoord, ycoord, STONE_SIZE, STONE_SIZE);
			
			stonesInPit.add(stone);
			g2.setColor(stoneColor);
			g2.fill(stone);
			g2.setColor(color);
			g2.draw(stone);
			counter++;
//			xcoord = this.x + 20;
//			ycoord = this.y + 20;
		}	
	}

	public boolean contains(Point p) {
		return shape.contains(p);
	}
	
}
