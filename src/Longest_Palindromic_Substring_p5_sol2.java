/**
 * DP approach
 * 
 * We use a boolean[][] to help use find the longest palindromic substring
 * We will use a nested loop to update the matrix. The outer loop is for the len of substring, we will start from short substring to long substring
 * The inner loop is for the left and right of the substring. 
 * If we found chars at left and right are same, and dp[left+1][right-1] = true, then we will update dp[left][right] to be true
 * 
 * Time complexity: O(N^2)
 * Space complexity: O(N^2)
 * 
 * Another similar O(N^3) dp solution: Burst_Balloons_p312_sol1
 * 
 * @author hpPlayer
 * @date Mar 11, 2016 7:31:29 PM
 */
public class Longest_Palindromic_Substring_p5_sol2 {
    public String longestPalindrome(String s) {
        //dp approach, we build a boolean table, and search each len of substring, then return the longest palindromic string we found
        
        //dp[i][j] means whether the substring from i to j is a palindromic substring
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        //two variables to hold the longest palindrome
        int maxStart = 0;
        int maxLen = 1;
        
        //firstly we fill all cores to be true
        for(int i = 0; i < s.length(); i++){
            //single core 
            dp[i][i] = true;
            //double cores
            if(i + 1 < s.length() && s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                maxStart = i;
                maxLen = 2;
            }
        }
        
        //we will extend from cores to all substrings
        //we will starts with shorter substring then forward to longer substring
        //cores have len 2, therefore here the loop starts with len 3
        //the longest substring is input s itself, therefore we will end with len s.length()
        for(int len = 3; len <= s.length(); len++){
            for(int left = 0; left + len <= s.length(); left++){
                int right = left + len - 1;
                //if chars at left and right are same, and prev shorter substring from left + 1 to right - 1 is also a palindrome
                //then we will update dp[left][right] = true
                if(s.charAt(left) == s.charAt(right) && dp[left+1][right-1]){
                    dp[left][right] = true;
                    maxStart = left;
                    maxLen = len;
                }
            }
        }
        
        return s.substring(maxStart, maxStart + maxLen);
    }
}
