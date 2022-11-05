package mancala;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * This MancalaView contains graphical user interface (GUI) components for
 * MancalaModel and ChangeListener for event handling mechanism.
 * Notes:
 * 		     Initial board
 * 		     	(<<<Row B<<<)
 * 			    4  4  4  4  4  4
 * 	(B)	 0  a  b  c  d  e  f  0 (A)
 *   	      4  4  4  4  4  4
 *  		    (>>>Row A>>>) 
 */

public class MancalaView extends JComponent{

	static final int BOARD_WIDTH = 200;
	static final int BOARD_HEIGHT = 80;
	static final int GOAL_WIDTH = 20;
	static final int GOAL_HEIGHT = 50;
	static final int PIT_WIDTH = 20;
	static final int PIT_HEIGHT = 20;
	static final int TOP_Y= 10;
	static final int BOTTOM_Y = 50;
	
	private GoalMancala A;
	private GoalMancala B;
	private ArrayList<Pit> rowA;
	private ArrayList<Pit> rowB;
	private int[] totalStonesPits;  //total stones in each pit
	private int[] totalStonesGoals; //total stones in each goal pit

	private MancalaModel mancalaModel;
	private MancalaFormatter mancalaFormat;
	

	public MancalaView(MancalaModel m, MancalaFormatter f) {
		this.mancalaModel = m;
		this.mancalaFormat = f;
		rowA = new ArrayList<>();
		rowB = new ArrayList<>();
		totalStonesPits = new int[12];
		totalStonesGoals = new int[2];
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double test = new Rectangle2D.Double(10,10,50,50);
		g2.draw(test);
		g2.drawString("TEST", 0, 0);
	}
	
	public void updateView() {
		A = new GoalMancala(170, 10, GOAL_WIDTH, GOAL_HEIGHT);
		B = new GoalMancala(10, 10, GOAL_WIDTH, GOAL_HEIGHT);
		
		Pit B0 = new Pit(PIT_WIDTH*2 + PIT_WIDTH/3, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B1 = new Pit(PIT_WIDTH*3 + PIT_WIDTH/2, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B2 = new Pit(PIT_WIDTH*5, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B3 = new Pit(PIT_WIDTH*6 + PIT_WIDTH/5, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B4 = new Pit(PIT_WIDTH*7 + PIT_WIDTH/2, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B5 = new Pit(PIT_WIDTH*8 + PIT_WIDTH/2, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		rowB.add(B0); rowB.add(B1); rowB.add(B2); rowB.add(B3); rowB.add(B4); rowB.add(B5);
		
		Pit A0 = new Pit(PIT_WIDTH*2 + PIT_WIDTH/3, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A1 = new Pit(PIT_WIDTH*3 + PIT_WIDTH/2, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A2 = new Pit(PIT_WIDTH*5, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A3 = new Pit(PIT_WIDTH*6 + PIT_WIDTH/5, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A4 = new Pit(PIT_WIDTH*7 + PIT_WIDTH/2, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A5 = new Pit(PIT_WIDTH*8 + PIT_WIDTH/2, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		rowA.add(A0); rowA.add(A1); rowA.add(A2); rowA.add(A3); rowA.add(A4); rowA.add(A5);

		
		
	}
}
