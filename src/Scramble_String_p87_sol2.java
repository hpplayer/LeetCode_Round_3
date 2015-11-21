/**
 * 3D DP solution
 * 
 * The outer loop is based on length. dp[k][i][j] means whether substring[i:i+k] in s1 is scramble string of substring[j:j+k] in s2
 * We will try all possible ways to make each substring pair with different length to be valid.
 * Finally we just need to look up value in dp[len-1][0][0]
 * 
 * Time complexity: O(n^4)
 * Space complexity: O(n^3)
 * 
 * @author hpPlayer
 * @date Nov 19, 2015 11:54:34 PM
 */
public class Scramble_String_p87_sol2 {
    public boolean isScramble(String s1, String s2) {
        //3D DP solution
        
        int len = s1.length();
        
        //len/s1/s2
        //value in dp[k][i][j] means whether we can match s1[i:i+k] with s2[j:j+k]
        boolean[][][] dp = new boolean[len][len][len];
        
        //boundary check, len 1
        //Only in this case we need to check real char in input strings
        //for other length, we just need to look up dp values
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(s1.charAt(i) == s2.charAt(j))dp[0][i][j] = true;
            }
        }
        
        //check each length
        for(int k = 1; k < len; k++){
            //check each start point in s1 and s2
            for(int i = 0; i + k< len; i++){
                for(int j = 0; j + k< len; j++){
                    //try each possible split index
                    for(int p = 0; p < k; p++){
                        //why we use k-p-1? Thats because k and p are both 0 based len, after substraction, the 
                        //result will be 1 based len, we have to -1 to convert it to 0 based len
                        //Why we use i+p+1? Similar to above, p is 0 based len, we want check char after len p + 1
                        //so we have to use i + p + 1
                        if( (dp[p][i][j] && dp[k-p-1][i+p+1][j+p+1]) || (dp[p][i][j+k-p] && dp[k-p-1][i+p+1][j]) ){
                            dp[k][i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        
        return dp[len-1][0][0];
    }
}
