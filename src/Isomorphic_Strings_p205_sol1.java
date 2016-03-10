/*
205. Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

/**
 * HashMap solution
 * 
 * We use two hashMaps to record the last appearance of curr char in s and t. If they are not same then we return false immediately
 * Here we always + 1 to each index value before put them into the HashMap, this is because we don't want to mix with default 0 in array, therefore 
 * char at index0 will have 1 in array instead of 0
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Remark: this solution is similar to Word_Pattern_p290_sol1, where we also record the index in HashMap to make sure correct mapping
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 6:14:12 PM
 */
public class Isomorphic_Strings_p205_sol1 {
    public boolean isIsomorphic(String s, String t) {
        //HashMap solution, we need one-on-one map relationship, and we have two input strings, so we use two hashMap to achieve that
        //we will record the last appearance index in the hashMap, if two chars appears in different indexes, then we will return false
        
        int[] s2t = new int[256];
        int[] t2s = new int[256];
        
        //if two inputs have diff len, then we return false immediately
        if(s.length() != t.length()) return false;
        
        for(int i = 0; i < s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            
            //if last appeared index is not same, return false
            if(s2t[a] != t2s[b]) return false;
            
            //To avoid the mix with default 0 in array, we add 1 to each index, so the char in index0 will have 1 in hashTable
            s2t[a] = i + 1;
            t2s[b] = i + 1;
        }
        
        return true;
    }
}
