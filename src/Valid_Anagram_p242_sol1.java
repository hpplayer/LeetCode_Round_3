/*
Valid Anagram

Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/

/**
 * HashMap problem
 * 
 * We just use a count table to record the appearance of each char in two strings. for string s, we let count ++, for string t, we let count --
 * If two strings are anagram, then the final count for each char should be 0. If not, we return false, otherwise return true
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * For followup question, we can just use HashMap to replace array
 * 
 * @author hpPlayer
 * @date Nov 6, 2015 4:03:42 PM
 */
public class Valid_Anagram_p242_sol1 {
    public boolean isAnagram(String s, String t) {
        //boundary check
        if(s.length() != t.length()) return false;
        
        //for ascii code, we just use an array with 256 len
        //for unicode, we can use a HashMap instead
        int[] count = new int[256];
        
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i)]++;
            count[t.charAt(i)]--;
        }
        
        for(int i = 0; i < 256; i++){
            //if current char does not have same appearance in two strings
            if(count[i] != 0) return false;
        }
        
        return true;
    }
}
