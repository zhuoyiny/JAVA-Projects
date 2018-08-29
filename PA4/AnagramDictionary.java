

import java.io.BufferedReader;

// Name: Zhuoying Yi
// USC NetID: zhuoyiny
// CS 455 PA4
// Fall 2017

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
    
    private static Map<String, String> anagramMap = new HashMap<String, String>();
   


   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
       readDicFile(fileName);
   }
       
   public AnagramDictionary() {
    
   }

   /**
    * Read each line in anagram dictionary
    * @param fileName
    * @throws FileNotFoundException
    */
   public void readDicFile(String fileName) throws FileNotFoundException {
       BufferedReader br = null;
       String currentLine;
       br = new BufferedReader(new FileReader(fileName));
       try {
           while ((currentLine = br.readLine()) != null) {
               this.addToHash(currentLine);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

   }
   
   /**
    * For the HashMap anagramMap, the sorted words are keys, and all anagrams in the anagram map are values.
    * @param word
    */
   private void addToHash(String word) {
       String sortedWord = sortString(word);
       if (anagramMap.containsKey(sortedWord)) {     // Add all anagrams of sorted string to HashMap anagramMap
           anagramMap.put(sortedWord, anagramMap.get(sortedWord) + " " + word);
       } else {
           anagramMap.put(sortedWord, word);
       }
    
   }

   /**
    * Sort input string according to the size of ASCII code of every characters
    * @param original
    * @return sorted: the sorted string
    */
    public static String sortString(String original) {
        char[] chars = original.toCharArray();
        Arrays.sort(chars);      // Sort input string
        String sorted = new String(chars);
        return sorted;
    }

    
   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
       ArrayList<String> ary = new ArrayList<String>();
       String key = sortString(s);
       String wordAnagrams = anagramMap.get(key);
       if(wordAnagrams!=null) {
           String[] name = wordAnagrams.split(" ");   // Add each anagrams of the given string to Arraylist
           for(int i=0; i<name.length; i++) {
               ary.add(name[i]);
           }
       }       
       return ary;
   }   
   
}
