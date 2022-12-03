package mancala;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Fall 2022: CS 151 Team Project - Mancala Game GUI
 * @author Jasmine Lao, Zachary Hobbs
 * @version 1.0 October 25, 2022
 */

/**
 * GoalMancala class is a Hole that represents goal mancalas.
 */
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

	/**
	 * creates the goal for the mancala game
	 * @param x horizontal value
	 * @param y vertical value
	 * @param w how wide it is
	 * @param h how tall it is
	 */
	public GoalMancala(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		totalStones = 0;
		stonesInPit = new ArrayList<>();
		id = -1;
	}
	
	/**
	 * gets the id
	 * @return the id of the goal mancala
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the goal mancala
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**Returns the x coord
	 * @return int
	 */
	public int getX() {
		return this.x;
	}

	/**Returns the y coord
	 * @return int
	 */
	public int getY() {
		return this.y;
	}

	/**Returns the width of goal mancala
	 * @return int
	 */
	public int getWidth() {
		return this.width;
	}

	/**Returns the height of goal mancala
	 * @return int
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Adds one stone to the number of total stones
	 */
	public void addStone() {
		totalStones += 1;
	}
	
	/**Returns the number stones in goal mancala
	 * @return int
	 */
	public int getStones() {
		return totalStones;
	}
	
	/**
	 * Sets the number of stones in goal mancala
	 * @param num
	 */
	public void setStones(int num) {
		totalStones = num;
	}
	
	/**
	 * Adds an amount of stones in goal mancala
	 * @param amount
	 */
	public void addStones(int amount) {
		totalStones += amount;
	}

	/**
	 * Returns the shape of goal mancala
	 * @return Shape
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * Sets the shape of the goal mancala
	 * @param shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
		this.setColor(color);
	}
	
	/**
	 * Sets the color of the goal mancala
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Sets the color of stone in the goal mancala
	 * @param stoneColor
	 */
	public void setStoneColor(Color stoneColor) {
		this.stoneColor = stoneColor;
	}

	/**
	 * Displays the goal mancala in GUI
	 * @param g2
	 */
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

	/**
	 * Returns false always because goal mancala is uneditable
	 * @param p
	 * @return false
	 */
	public boolean contains(Point p) {
		return false;
	}

}
