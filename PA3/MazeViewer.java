
import java.io.File;

// Name: Zhuoying Yi
// USC loginid: zhuoyiny
// CS 455 PA3
// Fall 2017


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;


/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 *      java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row, followed by the start location, 
 * and ending with the exit location. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze, with start location as the top left, and exit location as the bottom right
 * (we count locations from 0, similar to Java arrays):
 * 
 * 3 4 
 * 0111
 * 0000
 * 1110
 * 0 0
 * 2 3
 * 
 */

public class MazeViewer {
   
   private static final char WALL_CHAR = '1';
   private static final char FREE_CHAR = '0';

   public static void main(String[] args)  {

      String fileName = "";

      try {

         if (args.length < 1) {
            System.out.println("ERROR: missing file name command line argument");
         }
         else {
            fileName = args[0];
            
            JFrame frame = readMazeFile(fileName);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
         }

      }
      catch (FileNotFoundException exc) {
         System.out.println("ERROR: File not found: " + fileName);
      }
      catch (IOException exc) {
         exc.printStackTrace();
      }
   }

   /**
    readMazeFile reads in maze from the file whose name is given and 
    returns a MazeFrame created from it.
   
   @param fileName
             the name of a file to read from (file format shown in class comments, above)
   @returns a MazeFrame containing the data from the file.
        
   @throws FileNotFoundException
              if there's no such file (subclass of IOException)
   @throws IOException
              (hook given in case you want to do more error-checking --
               that would also involve changing main to catch other exceptions)
   */
   private static MazeFrame readMazeFile(String fileName) throws IOException {
       File inputFile = new File(fileName);            //read in file name
       Scanner in = new Scanner(inputFile);               
       return new MazeFrame(getMaze(in), getEntryExit(in), getEntryExit(in));     //get maze array and the entry and exit location of the maze
   }
   
   //get maze array 
   private static boolean[][] getMaze(Scanner in) {
       String str = in.nextLine();
       Scanner inLine = new Scanner(str);          
       int row = inLine.nextInt();              // get the row of the maze
       int col = inLine.nextInt();              // get the column of the maze
       boolean[][] maze = new boolean[row][col];       
       int i = 0;      
       while(in.hasNextLine()&&i<row) {
           String temp = in.nextLine();
           for(int j = 0; j < col; j++) {
               maze[i][j] = (temp.charAt(j) == '1'? true:false);  //'1' or '0' at certain location?
           }
           i++;
       }
       return maze; 
   }
   
   // get the entry and exit location of the maze
   private static MazeCoord getEntryExit(Scanner in) {
       String str = in.nextLine().trim();
       Scanner inLine = new Scanner(str);      
       int row = inLine.nextInt();
       int col = inLine.nextInt();           
       MazeCoord position = new MazeCoord(row, col);
       return position;
   }

}
