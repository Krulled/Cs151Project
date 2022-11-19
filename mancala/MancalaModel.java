package mancala;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;

/**
 * This MancalaModel provides the underlying data structures and holds subjects in which the event occurs.
 */

public class MancalaModel{

	private ArrayList<ChangeListener> listeners;
	private int initialStones;
	private MancalaFormatter format;

	
	public MancalaModel() {
		listeners = new ArrayList<>();
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


}
