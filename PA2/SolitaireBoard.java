

// Name: Zhuoying Yi
// USC NetID: zhuoyiny
// CSCI455 PA2
// Fall 2017

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
 */


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

    // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   
   /**
      Representation invariant:
          
      	  numPiles is the number of piles
      	  0 < numPiles <= PilesArr.length
      	  the piles are in pilesArr[0] - pilesArr[numPiles-1] and they should be larger than 0
   */  
   
   private int[] PilesArr;    // The piles array which store the number of cards in each pile
   private int numPiles;      // The number of piles
  
 
   /**
     Creates a solitaire board with the configuration specified in piles.
     piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
     PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(String str) {
	   PilesArr = new int[CARD_TOTAL];	   
       int i = 0;
       Scanner in = new Scanner(str);
       while(in.hasNextInt()) {
           int a = in.nextInt();  
           PilesArr[i]= a;
           i++;
       }
       numPiles = i;
       assert isValidSolitaireBoard();
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
       PilesArr = new int[CARD_TOTAL];
       int numCards = CARD_TOTAL;
       int i=0;
       while(numCards > 0) {
           Random rand = new Random();
           int randNum = rand.nextInt(numCards)+1;
           PilesArr[i] = randNum;
           numCards = numCards - randNum;
           i++;		   
       }
       numPiles = i;
       for (int j = 0; j < numPiles; j++) {
           System.out.print(PilesArr[j]);
           if (j != numPiles) {
           System.out.print(" ");
           }
       }
       System.out.println();
       assert isValidSolitaireBoard();
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
    */
   public void playRound() {
       int[] tempArr = new int[CARD_TOTAL];
       int i = 0;
       int j = 0;
       for (; i<numPiles; i++) {
           if (PilesArr[i]!=1) {
               tempArr[j]=PilesArr[i]-1;
               j++;
           }
       }
       tempArr[j]=i;
       Arrays.fill(this.PilesArr,0);	   
       for(int m=0;m<j+1;m++) {
           PilesArr[m]=tempArr[m];
           numPiles = j+1;
       }
       assert isValidSolitaireBoard();
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
    */   
   public boolean isDone() {
      boolean done = true;
      if(numPiles == NUM_FINAL_PILES) {
          OUT:
              for(int i=1;i<=NUM_FINAL_PILES;i++) {
            	  for(int j=0; j<numPiles; j++) {
                      if (PilesArr[j]==i) {
                          break;
                      }
                      else if(j==numPiles-1) {
                          done = false;
                          break OUT;
                      }
                  }    		      		  
    		  }
      }
      else {
          done = false;
      }
      return done;
   }
   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
    */
   public String configString() {
       String str = new String(Integer.toString(PilesArr[0]));
       if(numPiles > 1) {
           for(int i = 1; i < numPiles; i++ ) {
               str = new String(str + " " + Integer.toString(PilesArr[i]));
           }
       }
       return str;
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
    */
   private boolean isValidSolitaireBoard() {
       String str=configString();   // get the string from configString()
       return errorChecking(str);   
   }
   


    // <add any additional private methods here>
   /**
    * Generate initial words 
    */
   public static void initialize() {
       System.out.println("Number of total cards is "+ CARD_TOTAL);
       System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
       System.out.println("Please enter a space-separated list of positive integers followed by newline:");
}   
   
   /**
    * Do error checking
    * Check whether the list of numbers are actually all numbers (integers) and represents a valid configuration.
    * @param str
    * @return
    */
   public boolean errorChecking(String str) {
	   
       Scanner in = new Scanner(str);
       int sum = 0;   
       while(in.hasNext()) {
           if(in.hasNextInt()) {     // Determines whether the input is integer
               int a= in.nextInt();
               if( a>0 && a<=CARD_TOTAL) {   // Determine whether the input is larger than 0, smaller than total of cards
                   sum =sum+ a;				   
               }
               else {
                   return false;
               }
           }
           else {
               return false;
           }
       }
       if(sum==CARD_TOTAL) {
           return true;
       }
       else {
           return false;
       }
   }
   
   /**
    * Print error message
    */
   public void errorMessage() {
       System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + CARD_TOTAL);
       System.out.println("Please enter a space-separated list of positive integers followed by newline:");
   }
  
}
