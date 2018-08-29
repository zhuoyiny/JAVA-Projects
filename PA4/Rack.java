

// Name: Zhuoying Yi
// USC NetID: zhuoyiny
// CS 455 PA4
// Fall 2017

import java.util.ArrayList;

/**
 * A Rack of Scrabble tiles
 * Stores the current rack. Finding all subsets of a multiset using recursion.
 * 
 */
public class Rack {
    
    public static final int FIRST_PLACE = 0;  // The first position of the array
    public static final int INITIAL_NUM = 1;  // The minimum number of letters which are existed
    
    /**
     * The rack information to be in two parallel arrays: one has the unique letters, and the other has the multiplicity of that letter at the same array index.
     * @param original the original input string.
     * @param chars the array version of input string
     * @param temp temporary array to store unique letters after deleting
     * @param unique a string of unique letters
     * @param mult the multiplicity of each letter from unique.
     * @return allSubsets(unique, mult, FIRST_PLACE);
     */
    public static ArrayList<String> allConbinations(String original) {  // (i.e., create variables for the rack "aabbbd", String unique = "abd"; int[] mult = {2, 3, 1};)
        String sortedWord = AnagramDictionary.sortString(original);
        char[] chars = sortedWord.toCharArray();
        char[] temp = new char [chars.length];  // create a temporary array to store unique letters after deleting and initialize the array length
        int j = FIRST_PLACE;
        int num = INITIAL_NUM;
        int[] mult = new int [chars.length];  //  initialize the array length
        temp[FIRST_PLACE] = chars[FIRST_PLACE];
        mult[FIRST_PLACE] = INITIAL_NUM;
        for(int i = 0;i<chars.length-1;i++) {  // get the multiplicity of each unique letters
                                               //get the array of unique letters
            if(chars[i+1]!=chars[i]) {
                temp[j+1]=chars[i+1];
                j++;
                num=1;
            }
            else {
                num++;                
            }
            mult[j]=num;
        }
        String unique = new String(temp);  // Assigns the data in temp to unique
        return allSubsets(unique, mult, FIRST_PLACE);

    }
    

   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    *      0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
   
}
