/*
5. Longest Palindromic Substring

Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest
palindromic substring.
*/

/**
 * Two pointer + observation solution
 * 
 * We will search the longest Palindrome substring from each possible core including single core and double cores
 * We will firstly pick the core, then extend the substring from two sides if the new string is still a valid palindrome
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 * 
 * Sol2 lists a DP solution that also starts with cores, but use extra space. However It is a good practice for DP method
 * 
 * Remark:
 * If we can modify extendFromMid() a bit, to compare the chars at (left - 1) and (right + 1), and we also modify
 * longestPalindrome() that we only call extendFromMid() with single mid case or two mid case (2 chars are same)
 * then the program can be faster!
 * 
 * @author hpPlayer
 * @date Mar 11, 2016 5:42:47 PM
 */
public class Longest_Palindromic_Substring_p5_sol1 {
    public String longestPalindrome(String s) {
        //two pointer + observation solution. We observe that a palindrome can has one or two core cells. Therefore we scan the string
        //and extend substring from one and two cells to find the longest palindrome substring
        
        String result = "";
        
        for(int i = 0; i < s.length(); i++){
            //extend from single core
            String s1 = extendFromMid(i, i, s);
            //extend from double cores, in case chars at i and i + 1 are not same (left + 1 == right), then extendFromMid() will still return a valid char
            //which will not be achieved by using i and i - 1, so here we still use i and i + 1 instead of i - 1 and i
            String s2 = extendFromMid(i, i+1, s);
            
            if(s1.length() > result.length()) result = s1;
            if(s2.length() > result.length()) result = s2;    
        }
        
        return result;
    }
    
    public String extendFromMid(int left, int right, String s){
        //we try to extend palindrome substring from mid
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)  ){
            left--;
            right++;
        }
        
        //we will exit the loop when left and right point to diff chars, therefore we use left + 1, and right to get the palindrome substring
        return s.substring(left + 1, right);
    }
}
