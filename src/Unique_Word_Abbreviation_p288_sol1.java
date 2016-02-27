import java.util.*;

/*
288. Unique Word Abbreviation

An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/

/**
 * HashMap solution
 * 
 * Since we allow at most one owner for an abbreviation, therefore we need HashMap to store the abbreviation and the owner 
 * The key is the abbreviation, the value is the owner i.e. the last word that generates this abbreviation
 * If we found another word can also generate same abbreviation, then we will update HashMap value to be "" means any other word that get this abbreviation
 * will not be unique
 * 
 * During the isUnique(), we just need to check if the abbreviation of input is not in HashMap or it is same with the owner of abbreviation.
 * If yes, then return true, otherwise return false
 * 
 * Time complexity: constructor: O(N) + isUnique: O(1)
 * Space complexity: O(N)
 * 
 * Remark:
 * In getKey(), we need add "" in first, so that char value will be treated as string, otherwise program will calculate those char values and give
 * incorrect output
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 5:25:01 PM
 */
public class Unique_Word_Abbreviation_p288_sol1 {
    //HashMap solution, key is abbreviation, value is the last words that generates this key
    //if new input is same with last word, then keep it otherwise, change val to empty string indicating any word have this key is not unique
    
    private HashMap<String, String> hs;
    
    public Unique_Word_Abbreviation_p288_sol1(String[] dictionary) {
        hs = new HashMap<String, String>();
        
        for(String str : dictionary){
            String key = getKey(str);
            if(!hs.containsKey(key)){
                hs.put(key, str);
            }else{
                //if last corresponding word is another word, then we will update value to be empty means this any word that have this key
                //are not unique
                if(!hs.get(key).equals(str)) hs.put(key, ""); 
            }
        }
    }
    
    public String getKey(String s){
        if(s.length() <= 2) return s;
        //Notice!!!!!!!!!!: we need add "" in head, so char values will be treated as string instead of val which may lead to unexpected calculation
        return "" + s.charAt(0) + (s.length() - 2) + s.charAt(s.length()-1);
    }
    
    public boolean isUnique(String word) {
        String key = getKey(word);
        
        //if this key is not in HashMap or the word in hashMap is same with word, then we return true
        return !hs.containsKey(key) || hs.get(key).equals(word);
    }
}

//Your ValidWordAbbr object will be instantiated and called as such:
//ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
//vwa.isUnique("Word");
//vwa.isUnique("anotherWord");