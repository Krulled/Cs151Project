package mancala;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This MancalaModel provides the underlying data structures and holds subjects in which the event occurs.
 */

public class MancalaModel{

	private ArrayList<ChangeListener> listeners;
	private int initialStones;
	private MancalaFormatter format;
	static String winner = "";
	
	private int[] allPits;
	private int[] previousPits;
	private int numberOfUndos = 0;
	
	
	public MancalaModel() {
		listeners = new ArrayList<>();
		allPits = new int[14];
		previousPits = new int[14];
	}
	
	public int[] getViewPits() {
		return MancalaView.getTotalStonesPits();
	}
	
	public void startGame() {
		JDialog popup = new JDialog();
		
		JLabel theme = new JLabel("Choose a theme!");
		Object[] optionsForTheme = {"Blue Format", "Pink Format"};
		String initialThemeSelection = "Blue Format";
		Object askForTheme = JOptionPane.showInputDialog(null, theme, "Menu", JOptionPane.QUESTION_MESSAGE, null, optionsForTheme, initialThemeSelection); 		
		JOptionPane selectionTheme = new JOptionPane(askForTheme);
		String selectedTheme = askForTheme.toString();
		if(selectedTheme.equals("Blue Format")) {
			format = new BlueFormat();
		}
		else if (selectedTheme.equals("Pink Format")) {
			format = new PinkFormat();
		}

		JLabel stones = new JLabel("Choose the number of initial stones!");
		Object[] optionsForStones = {"1", "2", "3", "4"};
		String initialStoneSelection = "4";
		Object askForStones = JOptionPane.showInputDialog(null, stones, "Menu", JOptionPane.QUESTION_MESSAGE, null, optionsForStones, initialStoneSelection); 		
		JOptionPane selectionStones = new JOptionPane(askForTheme);
		String selectedStones = askForStones.toString();
		if(selectedStones.equals("4")) {
			initialStones = 4;
		}
		else if(selectedStones.equals("3")) {
			initialStones = 3;
		}
		else if(selectedStones.equals("2")) {
			initialStones = 2;
		}
		else if(selectedStones.equals("1")) {
			initialStones = 1;
		}

		popup.add(selectionTheme);
		popup.add(selectionStones);
		popup.setVisible(true);
	}
	
	/**
	 * Attaches change listener to listeners array list.
	 * Serves as ATTACHER.
	 * @param listener, listener to be added to ArrayList
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	public void updateListeners() {
		for(ChangeListener listener : listeners) {
			listener.stateChanged(new ChangeEvent(this));
		}
	}
	
	public void setFormat(MancalaFormatter f) {
		this.format = f;
	}

	public MancalaFormatter getFormat() {
		return format;
	}
	
	public int getInitialStones() {
		return initialStones;
	}

	public void setInitialStones(int initialStones) {
		this.initialStones = initialStones;
	}
	
	public int[] getAllPits(){
		return this.allPits;
	}
	
	public void moveStones(int index) {
		previousPits =  getViewPits().clone();
		allPits = getViewPits();
		int[] stonesInPits = MancalaView.getTotalStonesPits();
		numberOfUndos = 0;
		
		int numberStones = stonesInPits[index]; //number of stones in THIS pit
		stonesInPits[index] = 0; //reset this pit to 0 stones
		int temp = index;
		while(numberStones > 0) { //while number of stones > 0
			temp++;
			temp = temp % allPits.length;
			if(index <= 5 && temp == 13) { //Don't add in MancalaB if Player 1
				temp = 0;
			}
			if(index >= 7 && temp == 6) { //Don't add in MancalaA if Player 2
				temp = 7;
			}
			stonesInPits[temp] = stonesInPits[temp] + 1;
			numberStones--;
		}
		if(getEmptyRow() == "Row A") {
			giveAllToMancalaB();
		}
		else if(getEmptyRow() == "Row B") {
			giveAllToMancalaA();
		}
		this.updateListeners();

		if(checkIfGameEnds() == true) {
			displayWinner();
		}
	}
	
	public boolean checkIfGameEnds() {
		boolean allEmptyPits = true;
		int[] stonesInPits = MancalaView.getTotalStonesPits();
		for(int i = 0; i < stonesInPits.length; i++) {
			if(i != 6 && i != 13) {
				if(stonesInPits[i] != 0) {
					allEmptyPits = false;
				}
			}
		}
		return allEmptyPits;
	}
	
	public void displayWinner() {
		if(allPits[6] > allPits[13]) {
			winner = "Player A";
		}
		else if(allPits[6] < allPits[13]) {
			winner = "Player B";
		}
		else {
			winner = "It's a tie!";
		}
		MancalaView.signalGameEnd();
	}

	public void undoMove() { 
		numberOfUndos++;
		allPits = previousPits.clone();
		this.updateListeners();
	}
	
	public JButton getUndoButton() {
		JButton undoButton = new JButton("UNDO");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numberOfUndos > 3) {
					return;
				}
				undoMove();
			}
		});
		return undoButton;
	}

	public String getEmptyRow() {
		boolean rowBEmpty = true;
		boolean rowAEmpty = true;
		for(int i = 0; i < 6; i++) { //Check ROW A
			if(allPits[i] != 0) { 
				rowAEmpty = false;
			}
		}
		for(int i = 7; i < 13; i++) { //Check ROW B
			if(allPits[i] != 0) {
				rowBEmpty = false;
			}
		}
		if(rowBEmpty == true && rowAEmpty == false) { //Only Row B is empty
			return "Row B"; //all stones should go to Mancala A
		}
		if(rowBEmpty == false && rowAEmpty == true) { //Only Row A is empty
			return "Row A"; //all stones should go to Mancala B
		}
		return "Both aren't empty"; //Both rows are empty
	}
	
	public void giveAllToMancalaA() {
		int stonesLeftInRowA = 0;
		for(int i = 0; i < 6; i++) {
			stonesLeftInRowA += allPits[i];
			allPits[i] = 0;
		}
		allPits[6] += stonesLeftInRowA;
		this.updateListeners();
	}
	
	public void giveAllToMancalaB() {
		int stonesLeftInRowB = 0;
		for(int i = 7; i < 13; i++) {
			stonesLeftInRowB += allPits[i];
			allPits[i] = 0;
		}
		allPits[13] += stonesLeftInRowB; //add all REMAINING stones to Mancala B
		this.updateListeners();

	}

}
