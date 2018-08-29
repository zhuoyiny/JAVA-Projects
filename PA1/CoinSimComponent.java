
//Name: Zhuoying Yi
//USC NetID: 8303418105
//CS 455 PA1
//Fall 2017

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Scanner;

import javax.swing.JComponent;

/**
This component draws the bar graph and uses the CoinTossSimulator and Bar class.
*/
public class CoinSimComponent extends JComponent {
	private final static int vb = 40;
	private final static int bw = 60;
	private CoinTossSimulator coinSimulator;
	private int total;
	
	public CoinSimComponent(int numTrials) {
		super();
		total = numTrials;
		coinSimulator = new CoinTossSimulator();
		coinSimulator.run(numTrials);
	}

	public void paintComponent(Graphics g)
	   {  	
	      Graphics2D g2 = (Graphics2D) g;
	        
	      int m = coinSimulator.getNumTrials();
		  int twoh = coinSimulator.getTwoHeads();
		  int twot = coinSimulator.getTwoTails();
		  int ht = coinSimulator.getHeadTails();
		  
		  int a = (int) Math.round((double)100*twoh/m);
		  int b = (int) Math.round((double)100*ht/m);
		  int c = (int) Math.round((double)100*twot/m);
		  
		  double scale = (getHeight() - 2*vb - 15)/(double)total;
			
	      Bar bar1 = new Bar(getHeight()-vb, getWidth()/4-bw/2, bw, twoh, scale, Color.RED, "Two Heads: "+twoh+"("+a+"%)");
	      Bar bar2 = new Bar(getHeight()-vb, getWidth()/2-bw/2, bw, ht, scale, Color.GREEN, "A Head and a Tail: "+ht+"("+b+"%)");
	      Bar bar3 = new Bar(getHeight()-vb, getWidth()/4*3-bw/2, bw, twot, scale, Color.BLUE,"Two Tails: "+twot+"("+c+"%)");
	      
	      bar1.draw(g2);
	      bar2.draw(g2);  
	      bar3.draw(g2); 
	   }
}
