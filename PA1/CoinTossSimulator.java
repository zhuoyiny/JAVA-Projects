
import java.util.Random;
import java.util.Scanner;

// Name: Zhuoying Yi
// USC NetID: 8303418105
// CS 455 PA1
// Fall 2017

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {
	
	private static Random generator;
	private static int heads=0;
	private static int tails=0;
	private static int headtail=0;
	private static int n=0;
    int sumn = 0;
	int sumheads = 0;
	int sumtails = 0;
	int sumheadtail = 0;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   
	   generator = new Random();
	   
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
	  
	   for (int i=1; i<=numTrials; i++) {
		   int c1 = generator.nextInt(2);
		   int c2 = generator.nextInt(2);
		   if (c1*c2 == 1) {
			   heads++;
		   }
		   else if (c1+c2==0) {
			   tails++;
		   }
		   else {
			   headtail++;
		   }
		   n++;
	   }

    }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {

       sumn = n + sumn;
	   return n; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
	   
	   sumheads = sumheads + heads;
       return heads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
	   
	   sumtails = sumtails + tails;
       return tails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
	   
	   sumheadtail = sumheadtail + headtail;
       return headtail; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
		heads=0;
		tails=0;
		headtail=0;
		n=0;
		sumn = 0;
		sumheads = 0;
		sumtails = 0;
		sumheadtail = 0;
	   
   }  

}

