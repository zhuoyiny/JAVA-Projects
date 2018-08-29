

import java.util.Arrays;

// Name: Zhuoying Yi
// USC loginid: zhuoyiny
// CS 455 PA3
// Fall 2017

import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls

 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   
   public static final int START_ROW = 0;      // start row is 0
   public static final int START_COL = 0;      // start column is 0
   
   private boolean[][] mazeData;   // a 2D array to store maze data
   private MazeCoord startLoc;    // maze start location
   private MazeCoord exitLoc;     // maze exit location
   private boolean wallExist;
   private LinkedList<MazeCoord> mazePath;
   private boolean[][] visited;    // use a 2D array visited to keep track of positions we've already visited
   private boolean[][] visitedCopy;
  

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments above for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param exitLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
       this.mazeData = mazeData;
       this.startLoc = startLoc;
       this.exitLoc = exitLoc;    
       mazePath = new LinkedList<MazeCoord>();
       visited = new boolean[mazeData.length][mazeData[0].length];
       visitedCopy = new boolean[mazeData.length][mazeData[0].length];
   }


   /**
      Returns the number of rows in the maze
      @return number of rows
   */
   public int numRows() {
       return mazeData.length; 
   }

   
   /**
      Returns the number of columns in the maze
      @return number of columns
   */   
   public int numCols() {
       return mazeData[0].length;  
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {     
       wallExist = false;
       if(mazeData[loc.getRow()][loc.getCol()] == WALL) {  // check if there is a wall existing here
           wallExist = true;
       }
       return wallExist;
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {      // get entry location
       return startLoc;
   }
   
   
   /**
     Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {      // get exit location
       return exitLoc;
   }

   
   /**
      Returns the path through the maze. First element is start location, and
      last element is exit location.  If there was not path, or if this is called
      before a call to search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {           
       return mazePath;
   }
   

   /**
      Find a path from start location to the exit location (see Maze
      constructor parameters, startLoc and exitLoc) if there is one.
      Client can access the path found via getPath method.

      @return whether a path was found.
    */
   public boolean search()  {
       
      if(!mazePath.isEmpty()) {   // if the path has been found, return true
           if (mazePath.getLast().equals(exitLoc)) {
               return true;
           }
      }
      
      if (mazeData[startLoc.getRow()][startLoc.getCol()] == WALL) {   // if the entry is wall, return false
          return false;
      }
            
      if (startLoc.getRow()==exitLoc.getRow()&&startLoc.getCol()==exitLoc.getCol()){   // if the entry and exit are in the same location, return true
          mazePath.add(startLoc);
          return true;
      }
         
      visited[startLoc.getRow()][startLoc.getCol()]=true;     // set the current position as visited
      mazePath.add(startLoc);
      
      search(visited, startLoc.getRow(),startLoc.getCol());     // Call the recursive function to get the path
      
      if(mazePath.getLast().equals(exitLoc)) {      // if the last location in mazePath is already the exit location
          return true;
      }
      else {
          return false;
      }            
   }
   
   
   // Call the recursive function to get the path
   private void search(boolean[][] visited, int i, int j) {
       visited[i][j]=true;       
       for(int m=0; m<visited.length; m++) {     // Save the previous visited array
           for(int n=0; n<visited[0].length; n++) {
               visitedCopy[m][n] = visited[m][n];
           }
       }  
       LinkedList<MazeCoord> tempPath=new LinkedList<MazeCoord>(mazePath);         // Save the previous mazePath
       if(!(i==exitLoc.getRow()&&j==exitLoc.getCol())) {       // When the current position is not at the end, begin recursion process 
           if( i+1 < mazeData.length && mazeData[i+1][j] == FREE && visited[i+1][j]==false) {   // go down
               mazePath.add(new MazeCoord(i+1,j));               
               search(visited,i+1,j);
               if(mazePath.getLast().equals(exitLoc)) {
                   return;
               }
               goBack(tempPath,visitedCopy);       // go back to previous bifurcation
           }
           if( i-1>=START_ROW && mazeData[i-1][j] == FREE && visited[i-1][j]==false) {      // go up
               mazePath.add(new MazeCoord(i-1,j));
               search(visited,i-1,j);
               if(mazePath.getLast().equals(exitLoc)) {
                   return;
               }
               goBack(tempPath,visitedCopy);       // go back to previous bifurcation
           }
           if( j+1 < mazeData[0].length && mazeData[i][j+1] == FREE && visited[i][j+1]==false) {   // go right
               mazePath.add(new MazeCoord(i,j+1));
               search(visited,i,j+1);
               if(mazePath.getLast().equals(exitLoc)) {
                   return;
               }
               goBack(tempPath,visitedCopy);       // go back to previous bifurcation
           }
           if( j-1>=START_COL && mazeData[i][j-1] == FREE && visited[i][j-1]==false) {    // go left
               mazePath.add(new MazeCoord(i,j-1));
               search(visited,i,j-1);
               if(mazePath.getLast().equals(exitLoc)) {
                   return;
               }
             goBack(tempPath,visitedCopy);       // go back to previous bifurcation
           }
       }                
  }
   
   // go back to previous bifurcation, get the visited array and mazePath of the previous location
   private void goBack(LinkedList tempPath, boolean[][] visitedCopy) {
       for(int m=0; m<visited.length; m++) {
           for(int n=0; n<visited[0].length; n++) {
               visited[m][n] = visitedCopy[m][n];
           }
       }
       mazePath=new LinkedList<MazeCoord>(tempPath);
   }
   
   
}
