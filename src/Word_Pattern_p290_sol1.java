import java.util.*;

/*
290. Word Pattern

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
*/

/**
 * HashMap solution
 * 
 * In this problem, we have one on one relationship in both direction, so two hashMaps are needed
 * In the map, we record the index that current char/string last appeared. If they are connected, then they should appear in same index
 * Otherwise we return false
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Remark:
 * 1) Since we use Integer to record index, we need use equals() to compare two integers!
 * 2)Sol2 provides the solution that use char and string as map value, but it becomes more complicated than sol1 
 * 3) this solution is similar to Isomorphic_Strings_p205_sol1, where we also record the index in HashMap to make sure correct mapping
 * 
 * @author hpPlayer
 * @date Mar 2, 2016 10:17:58 PM
 */

public class Word_Pattern_p290_sol1 {
    public boolean wordPattern(String pattern, String str) {
        //HashMap solution. We need two hashMap since we have one on one relationship in both directions
        //Here to make thing simpler, we just record the 
        
        //key is char in pattern, value is last index that we have this char
        HashMap<Character, Integer> hs = new HashMap<Character, Integer>();
        //key is string in input str, value is last index that we have this string
        HashMap<String, Integer> hs2 = new HashMap<String, Integer>();
        //if char and string have one-on-one relationship then we should have same last appearance index in both map
        
        String[] strs = str.split(" ");
        if(strs.length != pattern.length()) return false;
        
        for(int i = 0; i < pattern.length(); i++){
            char c = pattern.charAt(i);
            //since we record the index instead of real corresponding char/string, we can feel free to update HashMaps as last apperance
            //index is an accurate indicator of whether current char/string are connected
            if(!hs.containsKey(c)) hs.put(c, i);    
            if(!hs2.containsKey(strs[i])) hs2.put(strs[i], i);
            
            //since integer is an object, we use equals instead of "==" to compare!!!!!!!!!!!!!!!!!!!!!!
            if(!hs.get(c).equals(hs2.get(strs[i]))) return false;
        }
        
        return true;
    }
}
