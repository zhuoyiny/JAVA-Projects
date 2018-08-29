
//Name: Zhuoying Yi
//USC NetID: zhuoyiny
//CS 455 PA4
//Fall 2017

import java.io.FileNotFoundException;
import java.util.Scanner;

/** This class has a main that's responsible for processing the command-line argument, and handling any errors processing. It also has the main command loop.
 *  Work on both lower-and-upper case versions of dictionaries and all processing are case-sensitive.
 *  Handle error if the dictionary file given is not found.
 */
public class WordFinder {
    
    
    public static void main(String args[]) throws FileNotFoundException {
        
        String dictionaryName;   //take an optional command-line argument for the dictionary file name
        if(args.length > 0) {
            dictionaryName = args[0];
        }
        else {
            dictionaryName = "sowpods.txt";  // If argument is left off, use the Scrabble dictionary file sowpods.txt.
        }
               
        AnagramDictionary anagramDictionary = null;
        try {                                     // handle the error: if the dictionary file given is not found
            anagramDictionary = new AnagramDictionary(dictionaryName);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            anagramDictionary.readDicFile(dictionaryName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }        
        Scanner input = new Scanner(System.in);
        System.out.println("Type . to quit.");
        System.out.print("Rack? ");
        
        while(input.hasNext()) {     // Waiting to read a string, accept any sequence of non-whitespace characters as a legal "rack"
            String strInput = input.next();            
            if(!strInput.trim().equals(".")) {  // if enter ".", the program exit
                ScoreTable.readString(strInput);  // read the input string
            }
            else {
                break;
            }
        }
    }        
}
