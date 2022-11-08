package mancala;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.event.ChangeListener;

/**
 * This MancalaModel provides the underlying data structures and holds subjects in which the event occurs.
 */

public class MancalaModel extends JFrame{

	
	private ArrayList<ChangeListener> listeners;
	
	public MancalaModel(int initialStones) {
		listeners = new ArrayList<>();
	}
	
	/**
	 * Attaches change listener to listeners array list.
	 * Serves as ATTACHER.
	 * @param listener, listener to be added to ArrayList
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}


}
