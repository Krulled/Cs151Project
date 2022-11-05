package mancala;

public class Pit implements Hole{

	private int x;
	private int y;
	private int width;
	private int height;
	private int totalStones;
	private final int PIT_SIZE = 10;
	
	public Pit(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
//		totalStones = initialStones;
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

	
}
