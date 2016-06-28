/*
Shortest Palindrome

Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
*/


/**
 * To find the longest palindrome substring from index 0, we have to build a new String like: s + "#" + reverse(s)
 * We just use a standard KMP way to build look up table, then retrieve the value in last cell, it is the longest palindrome
 * substring from index 0
 * 
 * Then we reverse non-palindrome part and attach it before head, then we are done
 * 
 * See my explanation on leetCode:
 * https://leetcode.com/discuss/64309/clean-kmp-solution-with-super-detailed-explanation
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 10:54:15 PM
 */

public class Shortest_Palindrome_p214_sol1 {
    public String shortestPalindrome(String s) {
        //build a new string with form of s + "#" + reverse(s)
        //so we can get hte max len of palindrome substring from index 0 
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);
        
        return new StringBuilder(s.substring(table[table.length-1])).reverse().toString() + s;
    }
    
    public int[] getTable(String s){
        int[] table = new int[s.length()];
        
        int index = 0;
        
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == s.charAt(index)){
                //char match, so we update table[i] = index + 1
                //it means we have index + 1 len of prefix/postfix match
                index ++;
                table[i] = index;
            }else{
                //curr char not match, we shorten the len and try to find a match one
            	
            	//we have to start with table[i-1], otherwise we may encounter indexing problem (like case index == 0)
                index = table[i - 1];
                while(index > 0 && s.charAt(index) != s.charAt(i)){
                    index = table[index - 1];
                }
                
                //same as if(s.charAt(i) == s.charAt(index)), we update table[i] = index + 1
               if(s.charAt(i) == s.charAt(index)){
                   index ++;
               }
               
               table[i] = index;
            }
        }
        
        return table;
    }
}
