package lab05.whackamole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * A model (in the MVC sense) for a whack-a-mole game. The model keeps track of
 * the location of the mole. It has a method for whacking a location and handles
 * scoring points and moving the mole if it was there and deducting points if
 * the mole was not there.
 * 
 * @author Grant Braught
 * 
 * @author Khanh Le & Rayan
 * @version October 14th, 2019
 */
public class WhackAMoleModel extends Observable {
	
	private int totalRows;
	private int totalCols;
	private int score;
	private int rowMole;
	private int colMole;
	
	//private
    /**
     * Construct a new 4x4 Whack-a-Mole board. The mole is initially at a
     * randomly selected location. The score is initially 0.
     */
    public WhackAMoleModel() {
    	totalRows = 4;
    	totalCols = 4;
    	score = 0;
    	Random rnd = new Random();
    	rowMole = rnd.nextInt(4);
    	colMole = rnd.nextInt(4);
    }

    /**
     * Get the number of rows on the board.
     * 
     * @return the number of rows.
     */
    public int getRows() {
        return totalRows;
    }

    /**
     * Get the number of columns on the board.
     * 
     * @return the number of columns.
     */
    public int getCols() {
        return totalCols;
    }
    
    /**
     * Get the row containing the mole.
     * 
     * @return the row containing the mole.
     */
    public int getMoleRow() {
        return rowMole;
    }

    /**
     * Get the column containing the mole.
     * 
     * @return the column containing the mole.
     */
    public int getMoleCol() {
        return colMole;
    }

    /**
     * Get the current score.
     * 
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Whack the hole at the specified row and column. When a hole is whacked
     * the score is increased if there was a mole at that location and decreased
     * if there was not. Either way the location of the mole is changed and any
     * observers are notified of the change.
     * 
     * @param row the row to whack
     * @param col the column to whack
     */
    public void whack(int row, int col) { 
    	//System.out.println(row + " " + col);
    	if ((row >= 0 && row < 4) && (col >= 0 && col < 4)) {
    		if (row == rowMole && col == colMole) {
    			//System.out.println("Here");
    			score = score + 10;
    			//change the mole's location
    			//System.out.println(score);
			}
    		else {
    			//System.out.println("Here1");
    			score = score - 5; 
    			//System.out.println(score);
    		}
		}
    	
		int initRow = rowMole;
		int initCol = colMole;
		Random rnd = new Random();
		while(rowMole == initRow && colMole == initCol) {
			rowMole = rnd.nextInt(4);
			colMole = rnd.nextInt(4);
			System.out.println(rowMole); 
			System.out.println(colMole);
		setChanged();
	    notifyObservers();
    		
    	}
    }   
}
