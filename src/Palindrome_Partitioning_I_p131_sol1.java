import java.util.*;
/*
Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

/**
 * DP table + backtracking 
 * 
 * Instead of naive backtracking, this solution preprocesses the data so we just need to look at palindrome substrings
 * 
 * We build a dp matrix to record palindrome information on substrings, then use backtracking to enumerate all valid partitioning ways,
 * then if our cursor reach the end of string s, we just add it to result
 * 
 * Remark:
 * When filling the dp matrix, we should use <= s.length() and <= n so we can include the whole string into consideration!!!!!!!!!!!!!!!!!!!
 * 
 * Time complexity: 
 * 
 * DP table: O(n^2)
 * Backtracking: hard to analyze...
 * Space complexity: O(n^2)
 * 
 * @author hpPlayer
 * @date Nov 12, 2015 11:07:46 PM
 */
public class Palindrome_Partitioning_I_p131_sol1 {
    public List<List<String>> partition(String s) {
        //use dp for early pruning, and use backtracking to build string list
        
        //get dp matrix
        boolean[][] dp = getMatrix(s);
        
        List<List<String>> result = new ArrayList<List<String>>();
        DFS(new ArrayList<String>(), result, 0, s, dp);
        
        return result;
    }
    
    public void DFS(List<String> temp, List<List<String>> result, int index, String s, boolean[][] dp){
        //backtracking
        if(index == s.length()){
            //reach end, found one valid partitioning
            result.add(new ArrayList<String>(temp));
            return;
        }
        
        for(int i = index; i < s.length(); i++){
            //we only start backtracking with valid substring
            if(dp[index][i]){
                temp.add(s.substring(index, i+1));
                DFS(temp, result, i+1, s, dp);
                temp.remove(temp.size()-1);
            }
        }
    }
    
    public boolean[][] getMatrix(String s){
        int n = s.length();
        //dp[i][j] means from index i to j in string s whether such substring is palindrome
        boolean[][] dp = new boolean[n][n];
        
        //len 1 (single core)
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }
        
        //len 2 (double cores)
        for(int i = 0; i + 1 < n; i++){
            if(s.charAt(i) == s.charAt(i+1)) dp[i][i+1] = true;
        }
        
        //from len 3 to len of whole string
        for(int len = 3; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;//we let i + len == n, so i + len - 1 is the last index in sting s
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]){
                    //we extend previous palindrome substring by two chars longer
                    dp[i][j] = true;
                }
            }
        }
        return dp;
    }
}
