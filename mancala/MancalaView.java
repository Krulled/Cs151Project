package mancala;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This MancalaView contains graphical user interface (GUI) components for
 * MancalaModel and ChangeListener for event handling mechanism.
 * Notes:
 * 		     Initial board
 * 			(<<<Row B<<<)
 * 			4  4  4  4  4  4
 * 	(B)	 0  a  b  c  d  e  f  0 (A)
 *   	    4  4  4  4  4  4
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
	private static ArrayList<Hole> allPits;
//	private ArrayList<Pit> rowB;
	private static int[] totalStonesInPits;  //total stones in each pit
//	private int[] totalStonesGoals; //total stones in each goal pit
	
	private MancalaModel mancalaModel;
	private MancalaFormatter mancalaFormat;
	private int initialStones;
	

	public MancalaView(MancalaModel model) {
		mancalaModel = model;
		mancalaModel.addChangeListener(this);
		mancalaFormat = model.getFormat();
		allPits = new ArrayList<Hole>();
//		rowB = new ArrayList<>();
		initialStones = model.getInitialStones();
		totalStonesInPits = new int[14]; //includes pits and goal pits
//		totalStonesGoals = new int[2];
		for(int i = 0; i < totalStonesInPits.length; i++) {
			if(i == 6 || i == 13) { //GOAL PITS have 0 stones initially
				totalStonesInPits[i] = 0;
			}
			else { 					//PITS have intialStones initially
				totalStonesInPits[i] = model.getInitialStones();
			}
//			System.out.println("totalStonesPits[i]:" + totalStonesPits[i]);
		}
//		for(int i = 0; i < totalStonesGoals.length; i++) {
//			totalStonesPits[i] = 0;
//		}
		MouseListeners mouseListener = new MouseListeners();
		this.addMouseListener(mouseListener);
	}

	private class MouseListeners extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			for(int i = 0; i < totalStonesInPits.length; i++) {
				if(allPits.get(i).contains(e.getPoint())) {
					mancalaModel.moveStones(i);
					redraw();
				}
			}
		}
	}
	
	//redraw -clear allPits, create new objs
	public void redraw() {
		allPits.clear();
		drawView();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double border = new Rectangle2D.Double(20,20,BOARD_WIDTH,BOARD_HEIGHT);
		g2.setColor(mancalaFormat.formatBoardBackground());
		g2.fill(border);
		
		drawView();
		A.draw(g2);
		B.draw(g2);
		for(Hole pit : allPits) {
			pit.draw(g2);
		}
	}
	
	public void drawView() {
		A = new GoalMancala(BOARD_WIDTH-50, BOARD_WIDTH/10-5, GOAL_WIDTH, GOAL_HEIGHT);
		A.setColor(mancalaFormat.formatOutlineColor());
		A.setStoneColor(mancalaFormat.formatStoneColor());
		A.setShape(mancalaFormat.formatGoalMancalaShape(A)); 
		A.setStones(totalStonesInPits[6]);
		
		B = new GoalMancala(40, 45, GOAL_WIDTH, GOAL_HEIGHT);
		B = new GoalMancala(BOARD_WIDTH/10-10, BOARD_WIDTH/10-5, GOAL_WIDTH, GOAL_HEIGHT);
		B.setColor(mancalaFormat.formatOutlineColor());
		B.setStoneColor(mancalaFormat.formatStoneColor());
		B.setShape(mancalaFormat.formatGoalMancalaShape(B)); 
		B.setStones(totalStonesInPits[13]);
		
		Pit A0 = new Pit(PIT_WIDTH*2 + PIT_WIDTH/5, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A1 = new Pit(A0.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT); 
		Pit A2 = new Pit(A1.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A3 = new Pit(A2.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A4 = new Pit(A3.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit A5 = new Pit(A4.getX() + 55, BOTTOM_Y, PIT_WIDTH, PIT_HEIGHT);
		A0.setStones(totalStonesInPits[0]); A0.setShape(mancalaFormat.formatPitShape(A0)); A0.setColor(mancalaFormat.formatOutlineColor()); A0.setStoneColor(mancalaFormat.formatStoneColor());
		A1.setStones(totalStonesInPits[1]); A1.setShape(mancalaFormat.formatPitShape(A1)); A1.setColor(mancalaFormat.formatOutlineColor()); A1.setStoneColor(mancalaFormat.formatStoneColor());
		A2.setStones(totalStonesInPits[2]); A2.setShape(mancalaFormat.formatPitShape(A2)); A2.setColor(mancalaFormat.formatOutlineColor()); A2.setStoneColor(mancalaFormat.formatStoneColor());
		A3.setStones(totalStonesInPits[3]); A3.setShape(mancalaFormat.formatPitShape(A3)); A3.setColor(mancalaFormat.formatOutlineColor()); A3.setStoneColor(mancalaFormat.formatStoneColor());
		A4.setStones(totalStonesInPits[4]); A4.setShape(mancalaFormat.formatPitShape(A4)); A4.setColor(mancalaFormat.formatOutlineColor()); A4.setStoneColor(mancalaFormat.formatStoneColor());
		A5.setStones(totalStonesInPits[5]); A5.setShape(mancalaFormat.formatPitShape(A5)); A5.setColor(mancalaFormat.formatOutlineColor()); A5.setStoneColor(mancalaFormat.formatStoneColor());
//		rowA.add(A0); rowA.add(A1); rowA.add(A2); rowA.add(A3); rowA.add(A4); rowA.add(A5);

		Pit B0 = new Pit(PIT_WIDTH*2 + PIT_WIDTH/5, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B1 = new Pit(B0.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B2 = new Pit(B1.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B3 = new Pit(B2.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B4 = new Pit(B3.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		Pit B5 = new Pit(B4.getX() + 55, TOP_Y, PIT_WIDTH, PIT_HEIGHT);
		B0.setStones(totalStonesInPits[12]); B0.setShape(mancalaFormat.formatPitShape(B0)); B0.setColor(mancalaFormat.formatOutlineColor()); B0.setStoneColor(mancalaFormat.formatStoneColor());	 
		B1.setStones(totalStonesInPits[11]); B1.setShape(mancalaFormat.formatPitShape(B1)); B1.setColor(mancalaFormat.formatOutlineColor()); B1.setStoneColor(mancalaFormat.formatStoneColor());
		B2.setStones(totalStonesInPits[10]); B2.setShape(mancalaFormat.formatPitShape(B2)); B2.setColor(mancalaFormat.formatOutlineColor()); B2.setStoneColor(mancalaFormat.formatStoneColor());
		B3.setStones(totalStonesInPits[9]); B3.setShape(mancalaFormat.formatPitShape(B3)); B3.setColor(mancalaFormat.formatOutlineColor()); B3.setStoneColor(mancalaFormat.formatStoneColor());
		B4.setStones(totalStonesInPits[8]); B4.setShape(mancalaFormat.formatPitShape(B4)); B4.setColor(mancalaFormat.formatOutlineColor()); B4.setStoneColor(mancalaFormat.formatStoneColor());
		B5.setStones(totalStonesInPits[7]); B5.setShape(mancalaFormat.formatPitShape(B5)); B5.setColor(mancalaFormat.formatOutlineColor()); B5.setStoneColor(mancalaFormat.formatStoneColor());
//		rowB.add(B0); rowB.add(B1); rowB.add(B2); rowB.add(B3); rowB.add(B4); rowB.add(B5);
		
		allPits.add(A0); //A0 = 0
		A0.setId(0);
		
		allPits.add(A1); //A1 = 1
		A1.setId(1);

		allPits.add(A2); //A2 = 2
		A2.setId(2);

		allPits.add(A3); //A3 = 3
		A3.setId(3);

		allPits.add(A4); //A4 = 4
		A4.setId(4);

		allPits.add(A5); //A5 = 5
		A5.setId(5);

		allPits.add(A); //Goal Mancala = 6
		A.setId(6);

		allPits.add(B5); //B5 = 7
		B5.setId(7);

		allPits.add(B4); //B4 = 8
		B4.setId(8);

		allPits.add(B3); //B3 = 9
		B3.setId(9);

		allPits.add(B2); //B2 = 10
		B2.setId(10);

		allPits.add(B1); //B1 = 11
		B1.setId(11);

		allPits.add(B0); //B0 = 12
		B0.setId(12);

		allPits.add(B); //Goal Mancala = 13
		B.setId(13);

		displayScores();
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
	
	public static int[] getTotalStonesPits() {
		return totalStonesInPits;
	}
	
	public int[] getTotalStonesGoals() {
		return totalStonesInPits;
	}
	
	/**
	 * 
	 * @param array
	 */
	public static void setTotalStonesPits(int[] array) {
		totalStonesInPits = array;
	}
	
	@Override
	/**
	 * changes the amount of stones in each pit when clicked on a pit
	 * postcondition: changes the arraylists of the pits
	 */
	public void stateChanged(ChangeEvent e) {
		redraw();
		totalStonesInPits = mancalaModel.getAllPits();
		for(int i = 0; i < totalStonesInPits.length; i++) {
			allPits.get(i).setStones(totalStonesInPits[i]);
		}
		repaint();
		revalidate();
	}
	
	/**
	 * signals for the end of the game
	 * postcondition: decides the winner
	 */
	public static void signalGameEnd() {
		JOptionPane.showMessageDialog(null, "Game is finished! The Winner is: " + MancalaModel.winner);
	}
	
	/**
	 * Displays the scores right below the goal mancalas 
	 * precondition: needs to have the goals set up
	 * postcondition: prints the score
	 */
	public void displayScores() {
		int scoreA = totalStonesInPits[6];
		int scoreB = totalStonesInPits[13];

		JLabel scorePlayerA = new JLabel("Current Score for Player A: " + scoreA);
//		scorePlayerA.setBounds(GOAL_HEIGHT, GOAL_HEIGHT, 30, 30);
		scorePlayerA.setLocation(150, 500);
		JLabel scorePlayerB = new JLabel("Current Score for Player B: " + scoreB);
		scorePlayerB.setLocation(150, 750);

		scorePlayerA.setVisible(true);
		scorePlayerB.setVisible(true);
		this.add(scorePlayerA);
		this.add(scorePlayerB);

	}
	

}
