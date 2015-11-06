import java.util.*;
/*
Word Break II

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

/**
 * DP + backtracking problem
 * 
 * We firstly use dp approach to fill dp array. But this time, we will fill the dp array backward, so the value in dp[] means whether
 * we have a valid substring from current index to tail
 * Then we use backtracking approach to build the string. If we found we have substring from 0 to i that is in worDict while dp[i] = true
 * then we know substring from 0 to i can be included in the result, so we just start DFS on it 
 * 
 * Remark
 * Be careful with the indexing, here we mainly use indexing on string s, i.e. from 0 to s.length - 1
 * 
 * Time complexity: building dp array causes O(n^2). to calculate the cost of backtracking, we have to use Master's theorem, and it
 * is too hard to analyze it here.
 * 
 * Space complexity: O(n) for dp, and it is also too hard to analyze it with backtracking.
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 9:42:46 PM
 */
public class Word_Break_II_p140_sol1 {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        //we use dp array to guide us where to start backtracking, and use backtracking to build target string
        int len = s.length();
        
        //value in dp array means whether we have a valid substring from index i to tail
        boolean[] dp = new boolean[len];
        
        //we will fill the dp table backward so we can build the string forward while knowing info in the right part
        for(int i = len-1; i >= 0; i--){
            //fill each cell of dp table
            for(int j = len-1; j >= i; j--){
                //check each dp cell before until we found a cell that is true, and we also have substring(j, i+1)
                //int dict
                if( (j+1 == len || dp[j+1]) && wordDict.contains(s.substring(i, j+1))){
                    dp[i] = true;
                    break;
                }
            }
        }
        
        List<String> result = new ArrayList<String>();
        if(!dp[0]) return result;
        
        //since the value in dp[i] means whehter we have a valid substring from i to tail
        //if we also have a substring(0, i) in the dict, we can start backtracking to build string
        for(int i = 0; i < len; i++){
            String newStr = s.substring(0, i+1);
            //we will do backtracking if we have substring from 0 to i, and we also know there is a way to build
            //strings from i to tail
            if(wordDict.contains(newStr) &&  (i == len -1 || dp[i+1]) ){
                DFS(result, newStr, s, i+1, dp, wordDict);    
            }
        }
        
        return result;
    }
    
    public void DFS(List<String> result, String temp, String s, int index, boolean[] dp, Set<String> wordDict){
        if(index == s.length()){
            result.add(temp);
            return;
        }
        
        for(int i = index; i < s.length(); i++){
            String newStr = s.substring(index, i+1);
            if(wordDict.contains(newStr) && (i == s.length() -1 || dp[i+1])){
                DFS(result, temp + " " + newStr, s, i+1, dp, wordDict);
            }
        }
        
    }
}
