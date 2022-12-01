package mancala;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class GoalMancala implements Hole{

	private int x;
	private int y;
	private int width;
	private int height;
	private int totalStones;
	private final int STONE_SIZE = 10;
	private int id;

	private Color color;
	private Color stoneColor;
	private Shape shape;
	private ArrayList<Ellipse2D.Double> stonesInPit;

	public GoalMancala(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		totalStones = 0;
		stonesInPit = new ArrayList<>();
		id = -1;
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
	
	public void setStones(int num) {
		totalStones = num;
	}
	
	public void addStones(int amount) {
		totalStones += amount;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
		this.setColor(color);
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
		
		//Display goal mancala label
		if(this.x == MancalaView.BOARD_WIDTH-50) {
			g2.drawString("Mancala A", x-2, y-5);
			g2.setColor(color);
		}
		else{
			g2.drawString("Mancala B", x-2, y-5);
			g2.setColor(color);
		}
		
		//Display number of stones in pit
		if(this.x == MancalaView.BOARD_WIDTH-50) {
			g2.drawString(" " + totalStones, x+20, y+MancalaView.GOAL_HEIGHT+15); 
		}
		if(this.x == 40) {
			g2.drawString(" " + totalStones, x+20, y+MancalaView.GOAL_HEIGHT+15);
		}
		
		//Display stones in pit 
		double xcoord = this.x + 20;
		double ycoord = this.y + 20;
		int counter = 1;
		stonesInPit.clear();
			for(int i = 0; i < totalStones; i++) {
				if(counter > 11) {
					return;
				}
				if(ycoord >= this.y + MancalaView.GOAL_HEIGHT) {
					ycoord = this.y;
				}
				if(counter % 2 == 0) {
					xcoord = this.x + 20;
					ycoord = this.y + 20 * counter/1.5;
				}
				else if(counter % 2 == 1) {
					xcoord = this.x + 10;
					ycoord = this.y + 10 * counter/1.5;
				}
				Ellipse2D.Double stone = new Ellipse2D.Double(xcoord, ycoord, STONE_SIZE, STONE_SIZE);
				stonesInPit.add(stone);
				g2.setColor(stoneColor);
				g2.fill(stone);
				g2.setColor(color);
				g2.draw(stone);
				counter++;
			}
	}

	public boolean contains(Point p) {
		return false;
	}

}
