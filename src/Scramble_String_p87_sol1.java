import java.util.*;
/*
Scramble String

Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/


/**
 * Backtracking + early-pruning solution
 * 
 * We use standard backtracking to try each possible split index.
 * For each split index, we may have two ways to match string
 * Like:
 * 
 * aa bcd
 * ef ghi
 * 
 * or 
 * 
 * aa bcd
 * hi efg
 * 
 * If one of the ways help us match the string, then we can return true.
 * Otherwise we will try other split index
 * 
 * The most important part of this solution is early-pruning. Otherwise we couldn't pass large data tests
 * Early pruning includes 1) checking length of two input strings 2) we count the chars in two strings to see if they have same
 * chars 
 * 
 * Time complexity: ? exponential
 * Space complexity: ? exponential
 * 
 * Sol1 is backtracking + early-pruning solution
 * Sol2 is 3D-DP solution
 * 
 * @author hpPlayer
 * @date Nov 19, 2015 10:07:27 PM
 */
public class Scramble_String_p87_sol1 {
   public boolean isScramble(String s1, String s2) {
        //backtracking solution, to make solution pass large-test-cases, we need use early-pruning
        
        //if length are not same, then we should return false directly
        if(s1.length() != s2.length()) return false;
        //if two input strings are same, then we should return true directly
        if(s1.equals(s2)) return true;
        
        //early pruning, key part of backtracking
        int[] count = new int[256];
        
        for(int i = 0; i < s1.length(); i++){
            count[s1.charAt(i)] ++;
            count[s2.charAt(i)] --;
        }
        
        for(int i = 0; i < 256; i++){
            if(count[i] != 0) return false;
        }
        
        //main part of backtracking, try each split index
        //Note: don't include whole string into s11/s12, otherwise we will fall into endless loop
        for(int i = 1; i < s1.length(); i++){
            //if two strings split in same index
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i);
            
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i);
            
            if( isScramble(s11, s21) && isScramble(s12, s22) ) return true;
            
            //if two strings split in reversed index
            s21 = s2.substring(s1.length() - i);
            s22 = s2.substring(0, s1.length() - i);
            
            if( isScramble(s11, s21) && isScramble(s12, s22) ) return true;
        }
        
        //all split indexes have been tried, and still not successful
        return false;
    }
}
