package mancala;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This MancalaView contains graphical user interface (GUI) components for
 * MancalaModel and ChangeListener for event handling mechanism.
 * Notes:
 * 		Initial board
 *  		(<<<Row B<<<)
 * 	       4  4  4  4  4  4
 * 	(B) 0  a  b  c  d  e  f  0 (A)
 *   	       4  4  4  4  4  4
 *  		(>>>Row A>>>) 
 */

public class MancalaView extends JComponent implements ChangeListener{

	static final int BOARD_WIDTH = 500;
	static final int BOARD_HEIGHT = 200;
	static final int GOAL_WIDTH = 50;
	static final int GOAL_HEIGHT = 150;
	static final int PIT_WIDTH = 50;
	static final int PIT_HEIGHT = 50;
	static final int TOP_Y= 50;
	static final int BOTTOM_Y = 140;
	
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
		Rectangle2D.Double border = new Rectangle2D.Double(20,20,BOARD_WIDTH,BOARD_HEIGHT);
		g2.draw(border);
		
		drawView();
		A.draw(g2);
		B.draw(g2);
		for(Pit pit : rowA) {
			pit.draw(g2);
		}
		for(Pit pit : rowB) {
			pit.draw(g2);
		}
	}
	
	public void drawView() {
//		A = new GoalMancala(400, 45, GOAL_WIDTH, GOAL_HEIGHT);
		A = new GoalMancala(BOARD_WIDTH-50, BOARD_WIDTH/10-5, GOAL_WIDTH, GOAL_HEIGHT);
		A.setShape(mancalaFormat.formatGoalMancalaShape(A));
		
//		B = new GoalMancala(40, 45, GOAL_WIDTH, GOAL_HEIGHT);
		B = new GoalMancala(BOARD_WIDTH/10-10, BOARD_WIDTH/10-5, GOAL_WIDTH, GOAL_HEIGHT);
		B.setShape(mancalaFormat.formatGoalMancalaShape(B));
		
		Pit B0 = new Pit(PIT_WIDTH*2 + PIT_WIDTH/5, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B1 = new Pit(B0.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B2 = new Pit(B1.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B3 = new Pit(B2.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B4 = new Pit(B3.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B5 = new Pit(B4.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		rowB.add(B0); rowB.add(B1); rowB.add(B2); rowB.add(B3); rowB.add(B4); rowB.add(B5);
		B0.setStones(totalStonesPits[0]); B0.setShape(mancalaFormat.formatPitShape(B0));
		B1.setStones(totalStonesPits[1]); B1.setShape(mancalaFormat.formatPitShape(B1));
		B2.setStones(totalStonesPits[2]); B2.setShape(mancalaFormat.formatPitShape(B2));
		B3.setStones(totalStonesPits[3]); B3.setShape(mancalaFormat.formatPitShape(B3));
		B4.setStones(totalStonesPits[4]); B4.setShape(mancalaFormat.formatPitShape(B4));
		B5.setStones(totalStonesPits[5]); B5.setShape(mancalaFormat.formatPitShape(B5));
		
		Pit A0 = new Pit(PIT_WIDTH*2 + PIT_WIDTH/5, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A1 = new Pit(A0.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A2 = new Pit(A1.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A3 = new Pit(A2.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A4 = new Pit(A3.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A5 = new Pit(A4.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		rowA.add(A0); rowA.add(A1); rowA.add(A2); rowA.add(A3); rowA.add(A4); rowA.add(A5);
		A0.setStones(totalStonesPits[0]); A0.setShape(mancalaFormat.formatPitShape(A0));
		A1.setStones(totalStonesPits[1]); A1.setShape(mancalaFormat.formatPitShape(A1));
		A2.setStones(totalStonesPits[2]); A2.setShape(mancalaFormat.formatPitShape(A2));
		A3.setStones(totalStonesPits[3]); A3.setShape(mancalaFormat.formatPitShape(A3));
		A4.setStones(totalStonesPits[4]); A4.setShape(mancalaFormat.formatPitShape(A4));
		A5.setStones(totalStonesPits[5]); A5.setShape(mancalaFormat.formatPitShape(A5));
		
	}

	public int[] getTotalStonesPits() {
		return totalStonesPits;
	}
	
	public int[] getTotalStonesGoals() {
		return totalStonesGoals;
	}
	
	public MancalaModel getMancalaModel() {
		return mancalaModel;
	}
	
	public void stateChanged(ChangeEvent e) {
		
	}
}
