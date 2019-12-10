package lab05.whackamole;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

/**
 * A GUI for the Whack-a-Mole game.
 *
 * @author Grant Braught
 * 
 * @author Khanh Le & Rayan Zia
 * @version October 14th, 2019
 */
public class WhackAMoleGUI extends JFrame implements Observer {

	 private WhackAMoleModel wamModel;
	 
	 private JButton[][] butList; 
	 private ImageIcon mole;
	 private ImageIcon hole;
	 private JLabel scoreLabel;
	/**
     * Construct a new WhackAMoleGUI for the specified model.
     * 
     * @param myModel the model for this GUI.
     */
  
	public WhackAMoleGUI(WhackAMoleModel myModel) {     
		
		super("Whack-A-Mole");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		wamModel = new WhackAMoleModel();
		wamModel.addObserver(this);
		
		JPanel mainPanel = new JPanel();
        this.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(getScoreDisplay());
        
        mole = new ImageIcon(WhackAMoleGUI.class.getResource("icons/gopher.jpg"));
		hole = new ImageIcon(WhackAMoleGUI.class.getResource("icons/hole.jpg"));
		
		butList = new JButton[4][4]; 
		for (int row = 0; row < 4; row++) {
			mainPanel.add(getRows(row));
		}
		butList[wamModel.getMoleRow()][wamModel.getMoleCol()].setIcon(mole);
		
		
	    this.pack();  
    	
    }
	/**
	 * Create a JPanel holding the display of the player's
	 * score. Rigid area is used to keep the score label
	 * separated.
	 * 
	 * @return the panel of score display.
	 */
	
	public JPanel getScoreDisplay () {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		scoreLabel = new JLabel("Current scores: 0");
	    panel.add(scoreLabel);
	    
	    panel.add(Box.createRigidArea(new Dimension(0,10)));
	    
	    return panel;
		
	}
	/**
	 * Create a JPanel holding four buttons. Horizontal
     * glue is used to expand each panel's left and right space
     * with the increase/decrease of the window size.
	 * 
	 * @param row the row of the panel
	 * @return the horizontal panel of buttons
	 */
	
	public JPanel getRows(int row) {
	
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		panel.add(Box.createHorizontalGlue());
		//set Button to holes
		for (int j=0; j<4; j++) {
			JButton button = new JButton(hole);
			panel.add(button);
			ButtonListener bList = new ButtonListener(row,j);
			button.addActionListener(bList);
			butList[row][j]=button;
		} 
		
		panel.add(Box.createHorizontalGlue());	 
		return panel;  
	}
	
    /**
     * 
     * Update the GUI to reflect the state of the model. This method
     * repaints all of the buttons with a hole and then repaints the button
     * with the mole on it.
     * 
     * @param o the object that made the notification (i.e. the WhackAMoleModel)
     * @param arg unused in this example. In general it could be a signal from
     *            the model indicating what has changed.
     */
    public void update(Observable o, Object arg) { 
    	int scores = wamModel.getScore();
    	
    	for (int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			butList[i][j].setIcon(hole);
    		}
    	}
    	butList[wamModel.getMoleRow()][wamModel.getMoleCol()].setIcon(mole);
    	
      	scoreLabel.setText("Current scores: " + scores);
    	
   }
    
    /*
     * This listener listens for clicks on the each button
     * of the button list and makes the appropriate call (the 
     * whack() method) to update the model.
     */
    
    private class ButtonListener implements ActionListener {
        private int row;
        private int col;
    	
        /**
         * Construct a new ButtonListener that listens for
         * the specific button that is clicked.
         * 
         * @param row the row of the button
         * @param col the column of the button
         */
        public ButtonListener(int row, int col) {
        	this.row = row;
        	this.col = col; 
        }
        public void actionPerformed(ActionEvent e) {
        	wamModel.whack(row, col);
        	
            System.out.println("You just clicked the button on row "+ row +" and column " + col); 
        }
    }
    /**
     * Run the WhackAMole game.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        WhackAMoleModel wamm = new WhackAMoleModel();
        WhackAMoleGUI gui = new WhackAMoleGUI(wamm);
        
        gui.setVisible(true);
    }
}
