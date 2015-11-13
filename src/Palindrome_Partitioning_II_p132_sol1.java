/*
Palindrome Partitioning II

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

/**
 * DP solution
 * 
 * 
 * We use a dp array to record the min cut for previous substring.
 * Firstly, we initialize the dp array with default value. For a string of len n, we need at most n - 1 cuts to partition to palindrome
 * substrings. Therefore based on this rule, we need initialize the array accordingly
 * 
 * Then we scan the string and try to find each palindrome substring. If a substring is palindrome starting from index i and ending at
 * index j, then to reach index j from i -1, we just need one cut. Based on this rule, we scan the array and update the array accordingly 
 * 
 * 
 * Remark:
 * our main loop is filling the dp[], whose len is n + 1. To query related char in string, we need -1. There to query the last char 
 * at index n - 1, we have to cover i == n in the loop as well!!!!!!!!!!!!!!!!!
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Nov 13, 2015 12:17:29 AM
 */
public class Palindrome_Partitioning_II_p132_sol1 {
    public int minCut(String s) {
        //dp approach
        
        int n = s.length();
        //the value in dp array means what is the minCut in substring from 0 to current index
        //we use an extra cell before index 0 to handle the boundary case that the whole substring is 
        //a palindrome so we need cut at all, i.e. 0 cut
        int[] dp = new int[n+1];
        
        //initialize the value in dp array.
        //for a substring with len j, we need at most j - 1 cuts to get our results i.e. we cut between each chars
        for(int i = 0; i < dp.length; i++){
            //Notice: for dp[0], we need fill it with -1 indicating we dont need cut for a palindrome substring
            dp[i] = i - 1;
        }
        
        
        //scan the array and check each palindrome substring
        //our array has len n, so we need include i == n case, when i == n, we are actually looking at n -1 index
        //also we don't need update i = 0, as it is a helper cell
        for(int i = 1; i <= n; i++){
            //this nested loop is used to fill dp[], so the index is 1 based. To query char in string
            //we have to use a - 1 && b - 1
            for(int a = i, b = i; a - 1 >= 0 && b -1 < n && s.charAt(a-1) == s.charAt(b-1); a--, b++){
                //palindrome substring expanded from single core
                //palindrome covers index b, so from index a - 1 to reach index b, we just need one cut 
                dp[b] = Math.min(dp[b], dp[a-1] + 1);
            }
            
            for(int a = i, b = i+1; a - 1 >= 0 && b -1 < n && s.charAt(a-1) == s.charAt(b-1); a--, b++){
                //palindrome substring expanded from double cores
                //palindrome covers index b, so from index a - 1 to reach index b, we just need one cut 
                dp[b] = Math.min(dp[b], dp[a-1] + 1);
            }
        }
        
        return dp[n];
    }
}
