package mancala;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MancalaGame extends JFrame{

	static final int FRAME_WIDTH = 550;
	static final int FRAME_HEIGHT = 450;
	
	private MancalaModel model;
	private MancalaView view;
	private MancalaFormatter format;
	
	public MancalaGame() {
		model = new MancalaModel();
		view = new MancalaView(model, new BlueFormat());
		setLayout(new BorderLayout());
		add(view, BorderLayout.CENTER); //add view to CENTER of frame
		
		JPanel menuPanel = new JPanel();
		JButton undoButton = new JButton("UNDO");
		menuPanel.add(undoButton);		//add undo button to menu panel
		add(menuPanel, BorderLayout.NORTH);	//add menu panel to NORTH of frame
	}
	
	public void setFormat(MancalaFormatter f) {
		this.format = f;
	}
	
	public MancalaFormatter getFormat() {
		return format;
	}
	
}
