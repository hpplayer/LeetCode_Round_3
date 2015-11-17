import java.util.*;
/*
Word Break

Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*/

/**
 * DP solution
 * 
 * We create a dp array to record the information whether we have a valid substring end at that index, for a longer substring, we just need
 * to find a shorter substring starts from 0 end at index j, and the dict contains the substring from j+1 to current index. So for the whole
 * string, we just need to check the dp value in the last cell.
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 * 
 * This solution is similar to Perfect_Squares_p279_sol1m, where we also need to use a dp[] and scan all cells before current index
 * @author hpPlayer
 * @date Nov 5, 2015 8:06:00 PM
 */

public class Word_Break_I_p139_sol1 {
    public boolean wordBreak(String s, Set<String> wordDict) {
        //value in dp[] means whether we have a valid substring ends at this index
        boolean[] dp = new boolean[s.length()];
        for(int i = 1; i <= s.length(); i++){
            if(wordDict.contains(s.substring(0, i))){
                //in case we have whole substring in dict
                dp[i-1] = true;
            }else{
                //for other cases, we have to scan all previous dp cells to find match string
                for(int j = 0; j < i; j ++){
                    //if we have a valid substring ends at index j, also we have another substring from j+1 to i-1
                    //remember i is 1 based, any we should exclude it
                    if(dp[j] &&  wordDict.contains(s.substring(j+1, i)) ){
                        dp[i-1] = true;
                    }
                }
            }   
        }
        return dp[s.length()-1];
    }
}
