
//Name: Zhuoying Yi
//USC NetID: zhuoyiny
//CS 455 PA4
//Fall 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;


/** Give information about Scrabble scores for scrabble letters and words. In scrabble not every letter has the same value.
 *  Get the map whose key is score and whose value is an ArrayList for all strings whose word's total score is equal. 
 */
public class ScoreTable {
    
    public static Map<Integer, String> wordScoreMap = new HashMap<Integer, String>();
    public static final int INITIAL_SCORE = 0;
    
    /** Read input string, print all the anagram of the input subset which in the dictionary
     * @param currentString
     */
    public static void readString(String currentString) {

        try {
            if (currentString != ".") {
                
                String beforeLine = currentString;                
                StringBuffer str = new StringBuffer();  
                for(int i=0;i<currentString.length();i++) {  
                    if((currentString.charAt(i)>=97 && currentString.charAt(i)<=122) || (currentString.charAt(i)>=65 && currentString.charAt(i)<=90)) {
                        str.append(currentString.charAt(i));
                    } 
                }
                currentString = str.toString();
                addToMap(currentString);
                System.out.println("We can make "+ getTotalNum(currentString)+" words from \""+sortString(beforeLine)+"\"");                
                if(getTotalNum(currentString)!=0) {
                    System.out.println("All of the words with their scores (sorted by score):");
                }
                
                scoreAndWord(currentString);
                TreeMap<Integer, ArrayList<String>> sortedWords = new TreeMap<Integer, ArrayList<String>>(scoreAndWord(currentString));
                NavigableMap<Integer, ArrayList<String>> descendingMap = sortedWords.descendingMap();
               
                for (int key : descendingMap.keySet()) {                    
                    Collections.sort(descendingMap.get(key));
                    if(!descendingMap.get(key).isEmpty()) {                      
                        for(int i=0; i<descendingMap.get(key).size();i=i+2) {
                            System.out.println(key+": "+descendingMap.get(key).get(i));
                        }
                    }
                }
                System.out.print("Rack?");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /** Get the total number of strings that can be found 
     * @param currentString current string after removing special characters
     * @return num the total number of strings that can be found
     * @throws FileNotFoundException
     */
    private static int getTotalNum(String currentString) throws FileNotFoundException {

        TreeMap<Integer, ArrayList<String>> sortedWords = new TreeMap<Integer, ArrayList<String>>(scoreAndWord(currentString));
        NavigableMap<Integer, ArrayList<String>> descendingMap = sortedWords.descendingMap();
        int num = 0;
        for (int key : descendingMap.keySet()) {
            if(!descendingMap.get(key).isEmpty()) {
                num+= descendingMap.get(key).size()/2; 
            }
        }        
        return num;
    }
        
    
    /** Add the letters score together to get the total score of the string. Index an array with a char that is a letter by treating it as an int and subtracting 'a' or 'A' from it.
     * Work for both upper and lower case versions of the letters. e.g., 'a' and 'A' have the same score.
     * @param word input string
     * @return score the total score of the string
     */
    private static int getWordScore(String word) {
        // Define an array that scores the weight of every letter in the alphabet
        int alphabetWeights[] = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
        int score = INITIAL_SCORE;
        
        for (int i = 0; i < word.length(); i++) {     // Add the letters score together to get the total score of the string.
            if(word.charAt(i)>=65 && word.charAt(i)<=90) {
                score = score + alphabetWeights[word.charAt(i) - 'A'];
            }
            if(word.charAt(i)>=97 && word.charAt(i)<=122) {
                score = score + alphabetWeights[word.charAt(i) - 'a'];                
            }
        }
        return score;
    }
    
    /**
     * Sort input string according to the size of ASCII code of every characters
     * @param original
     * @return sorted: the sorted string
     */
    private static String sortString(String original) {
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }
        
    
    /** Determine whether the current key is in the HashMap, and add the string
     * 
     * @param word String to be added
     */
    private static void addToMap(String word) {
        int score = getWordScore(word); 
        if (wordScoreMap.containsKey(score) && !wordScoreMap.get(score).contains(word)) {
            wordScoreMap.put(score, wordScoreMap.get(score) + " " + word);
        }
        else if (!wordScoreMap.containsKey(score)) {
            wordScoreMap.put(score, word);
        }
    }
    
    /** Constructs a map whose key is score and whose value is an ArrayList for all strings whose word's total score is equal.
     * 
     * @param wordInput 
     * @return mapWords
     * @throws FileNotFoundException
     */
    private static Map<Integer,  ArrayList<String>> scoreAndWord(String wordInput) throws FileNotFoundException
    {
        AnagramDictionary anagramDictionary = new AnagramDictionary();
        ArrayList<String> allWords = Rack.allConbinations(wordInput);
        Map<Integer, ArrayList<String>> mapWords = new HashMap<Integer, ArrayList<String>>();
        for (String word: allWords) {            
            int wordScore = ScoreTable.getWordScore(word);
            ArrayList<String> anagramsName = anagramDictionary.getAnagramsOf(word);
            if (!anagramsName.isEmpty()) {

                if (mapWords.containsKey(wordScore)) {
                    mapWords.get(wordScore).addAll(anagramsName);
                    mapWords.put(wordScore, mapWords.get(wordScore));
                }
                else {
                    mapWords.put(wordScore, anagramsName);
                }
            }
        }        
        return mapWords;
    }        

}