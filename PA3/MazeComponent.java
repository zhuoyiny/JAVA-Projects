

import java.awt.Color;

// Name: Zhuoying Yi
// USC loginid: zhuoyiny
// CS 455 PA3
// Fall 2017

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;


/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{

   private static final int START_X = 10; // top left of corner of maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   private Maze maze;

   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
      this.maze = maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g) {       
       Graphics2D g2 = (Graphics2D)g;       
       paintMaze(g2);       // paint the maze frame
       paintPath(g2);       // paint the path that is found
   }
   
   // paint the maze frame
   private void paintMaze(Graphics2D g2) {
       Rectangle mazeRec = new Rectangle(START_X , START_Y, maze.numCols() * BOX_WIDTH , maze.numRows() * BOX_HEIGHT);
       g2.draw(mazeRec);
       for(int i = 0; i < maze.numRows(); i++) {
           for(int j = 0; j < maze.numCols(); j++) {
               if(maze.hasWallAt(new MazeCoord(i, j)) == true){
                   Rectangle wallRec = new Rectangle(START_X+j*BOX_WIDTH, START_Y+i*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                   g2.setColor(Color.BLACK);
                   g2.fill(wallRec);
               }              
           }           
       }
       MazeCoord entry = maze.getEntryLoc();   // paint the entry location into yellow
       Rectangle entryRec = new Rectangle(START_X+(entry.getCol())* BOX_WIDTH + INSET, START_Y+(entry.getRow())* BOX_HEIGHT+ INSET, BOX_WIDTH-INSET*2, BOX_HEIGHT-INSET*2);
       g2.setColor(Color.YELLOW);
       g2.fill(entryRec);
       
       MazeCoord exit = maze.getExitLoc();    // paint the exit location into green
       Rectangle exitRec = new Rectangle(START_X+(exit.getCol())* BOX_WIDTH+ INSET, START_Y+(exit.getRow())* BOX_HEIGHT+ INSET, BOX_WIDTH-INSET*2, BOX_HEIGHT-INSET*2);
       g2.setColor(Color.GREEN);
       g2.fill(exitRec);
   }
   
   // paint the path that is found into blue
   private void paintPath(Graphics2D g2) {
       LinkedList<MazeCoord> mazePath = maze.getPath();
       Iterator<MazeCoord> iter = mazePath.iterator();
       if(iter.hasNext()) {
           MazeCoord before = iter.next();                  
           while (iter.hasNext()) {
               MazeCoord after = iter.next();
               Point2D.Double from = new Point2D.Double(START_X+BOX_WIDTH*(before.getCol())+0.5*BOX_WIDTH, START_Y+BOX_HEIGHT*(before.getRow())+0.5*BOX_HEIGHT);
               Point2D.Double to = new Point2D.Double(START_X+BOX_WIDTH*(after.getCol())+0.5*BOX_WIDTH, START_Y+BOX_HEIGHT*(after.getRow())+0.5*BOX_HEIGHT);
               Line2D.Double segment = new Line2D.Double(from, to);
               g2.setColor(Color.BLUE);
               g2.draw(segment);
               before = after;
           }
       }
   }
   
}



