package mancala;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MancalaGame extends JFrame{

	static final int FRAME_WIDTH = 550;
	static final int FRAME_HEIGHT = 350;
	
	private MancalaModel model;
	private MancalaView view;
/**
	 * Constructor that initializes instance variables and creates the buttons and panels
	 */
	public MancalaGame() {
		model = new MancalaModel();
		model.startGame();

		view = new MancalaView(model);
		model.addChangeListener(view);
		setLayout(new BorderLayout());
		add(view, BorderLayout.CENTER); //add view to CENTER of frame
		
		JPanel menuPanel = new JPanel();
		JButton undoButton = model.getUndoButton();
		menuPanel.add(undoButton);		//add undo button to menu panel
		add(menuPanel, BorderLayout.NORTH);	//add menu panel to NORTH of frame
		
		JPanel themes = new JPanel();
		JButton blueFormat = model.getBlueFormat();
		JButton pinkFormat = model.getPinkFormat();
		themes.add(pinkFormat);
		themes.add(blueFormat);
		add(themes, BorderLayout.SOUTH);
	}
	
	/**
	 * Returns this MancalaGame object
	 * @return MancalaGame
	 */
	public MancalaGame getGame() {
		return this;
	}

}
