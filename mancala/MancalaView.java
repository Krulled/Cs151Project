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
	private int initialStones;
	

	public MancalaView(MancalaModel model) {
		mancalaModel = model;
		mancalaFormat = model.getFormat();
		rowA = new ArrayList<>();
		rowB = new ArrayList<>();
		initialStones = model.getInitialStones();
		totalStonesPits = new int[12];
		totalStonesGoals = new int[2];
		for(int i = 0; i < totalStonesPits.length; i++) {
			totalStonesPits[i] = mancalaModel.getInitialStones();
//			System.out.println("totalStonesPits[i]:" + totalStonesPits[i]);
		}
		for(int i = 0; i < totalStonesPits.length; i++) {
			totalStonesPits[i] = 0;
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double border = new Rectangle2D.Double(20,20,BOARD_WIDTH,BOARD_HEIGHT);
//		g2.draw(border);
		g2.setColor(mancalaFormat.formatBoardBackground());
		g2.fill(border);
		
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
		//Set initial stones in each pit
//		for(int i = 0; i < totalStonesPits.length; i++) {
//			totalStonesPits[i] = mancalaModel.getInitialStones();
//			System.out.println("totalStonesPits[i]:" + totalStonesPits[i]);
//		}
//		for(int i = 0; i < totalStonesPits.length; i++) {
//			totalStonesPits[i] = 0;
//		}
		
		A = new GoalMancala(BOARD_WIDTH-50, BOARD_WIDTH/10-5, GOAL_WIDTH, GOAL_HEIGHT);
		A.setColor(mancalaFormat.formatOutlineColor());
		A.setShape(mancalaFormat.formatGoalMancalaShape(A)); 
		
		B = new GoalMancala(40, 45, GOAL_WIDTH, GOAL_HEIGHT);
		B = new GoalMancala(BOARD_WIDTH/10-10, BOARD_WIDTH/10-5, GOAL_WIDTH, GOAL_HEIGHT);
		B.setColor(mancalaFormat.formatOutlineColor());
		B.setShape(mancalaFormat.formatGoalMancalaShape(B)); 
		
		Pit B0 = new Pit(PIT_WIDTH*2 + PIT_WIDTH/5, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B1 = new Pit(B0.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B2 = new Pit(B1.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B3 = new Pit(B2.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B4 = new Pit(B3.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B5 = new Pit(B4.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		B0.setStones(mancalaModel.getInitialStones()); B0.setShape(mancalaFormat.formatPitShape(B0)); B0.setColor(mancalaFormat.formatOutlineColor()); 		 
		B1.setStones(mancalaModel.getInitialStones()); B1.setShape(mancalaFormat.formatPitShape(B1)); B1.setColor(mancalaFormat.formatOutlineColor());
		B2.setStones(mancalaModel.getInitialStones()); B2.setShape(mancalaFormat.formatPitShape(B2)); B2.setColor(mancalaFormat.formatOutlineColor());
		B3.setStones(mancalaModel.getInitialStones()); B3.setShape(mancalaFormat.formatPitShape(B3)); B3.setColor(mancalaFormat.formatOutlineColor());
		B4.setStones(mancalaModel.getInitialStones()); B4.setShape(mancalaFormat.formatPitShape(B4)); B4.setColor(mancalaFormat.formatOutlineColor());
		B5.setStones(mancalaModel.getInitialStones()); B5.setShape(mancalaFormat.formatPitShape(B5)); B5.setColor(mancalaFormat.formatOutlineColor());
		rowB.add(B0); rowB.add(B1); rowB.add(B2); rowB.add(B3); rowB.add(B4); rowB.add(B5);

		
		Pit A0 = new Pit(PIT_WIDTH*2 + PIT_WIDTH/5, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A1 = new Pit(A0.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT); 
		Pit A2 = new Pit(A1.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A3 = new Pit(A2.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A4 = new Pit(A3.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A5 = new Pit(A4.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		rowA.add(A0); rowA.add(A1); rowA.add(A2); rowA.add(A3); rowA.add(A4); rowA.add(A5);
		A0.setStones(mancalaModel.getInitialStones()); A0.setShape(mancalaFormat.formatPitShape(A0)); A0.setColor(mancalaFormat.formatOutlineColor());
		A1.setStones(mancalaModel.getInitialStones()); A1.setShape(mancalaFormat.formatPitShape(A1)); A1.setColor(mancalaFormat.formatOutlineColor());
		A2.setStones(mancalaModel.getInitialStones()); A2.setShape(mancalaFormat.formatPitShape(A2)); A2.setColor(mancalaFormat.formatOutlineColor());
		A3.setStones(mancalaModel.getInitialStones()); A3.setShape(mancalaFormat.formatPitShape(A3)); A3.setColor(mancalaFormat.formatOutlineColor());
		A4.setStones(mancalaModel.getInitialStones()); A4.setShape(mancalaFormat.formatPitShape(A4)); A4.setColor(mancalaFormat.formatOutlineColor());
		A5.setStones(mancalaModel.getInitialStones()); A5.setShape(mancalaFormat.formatPitShape(A5)); A5.setColor(mancalaFormat.formatOutlineColor());
		
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
	
	public int getInitialStones() {
		return initialStones;
	}

	public void setInitialStones(int initialStones) {
		this.initialStones = initialStones;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
