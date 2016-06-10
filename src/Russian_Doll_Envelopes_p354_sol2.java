import java.util.*;

/**
 * DP solution
 * 
 * Pure dp solution, but it is close to brute-force solution
 * The basic idea is using a DP to record the max_value for all of previous results. 
 * Then for each new envelope, we just scan all previous results and update cell accordingly
 * 
 * Time complexity: O(N^2)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 9, 2016 9:26:37 PM
 */
public class Russian_Doll_Envelopes_p354_sol2 {
    public int maxEnvelopes(int[][] envelopes) {
        //pure DP solution, but it is close to brute-force solution
        
        //boundary check
        if(envelopes.length == 0) return 0;
        
        //firstly sort the matrix based on int[0]
        
        Arrays.sort( envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
            
        }  );
        
        int result = 0;
        //each cell records the max value ended at that index
        int[] dp = new int[envelopes.length];
        
        for(int i = 0; i < envelopes.length; i++ ){
            //at least we have 1 envelope for each case
            dp[i] = 1;
            
            for(int j = 0; j < i; j++){
                //in case int[1] == int[1], we need break the loop, as we don't count envelopes with same height
                if(envelopes[i][1] == envelopes[j][1]) break;
                //if envelopes[i][1] > envelopes[j][1], then we can add one more envelope to envelopes[j][1]
                if( envelopes[i][1] > envelopes[j][1] ){
                    dp[i] = Math.max(dp[i], dp[j] + 1  );
                }
                
            }
            
            result = Math.max( dp[i], result );
        }
        
        return result;
    }
}
