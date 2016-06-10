/*
351. Android Unlock Patterns

Given an Android 3x3 key lock screen and two integers m and n, where 1 ¡Ü m ¡Ü n ¡Ü 9, count the total number of
unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys,
the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.

Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.
*/

/**
 * Observation + Backtracking solution 
 * 
 * We need use backtracking to enumerate all patterns
 * The observation is that we have limited inputs, we can hard-code some patterns.
 * For example 1->3 and 3->1 will cross 2, 1->9 and 9->1 will cross 5. We record those patterns before 
 * we do the backtracking.
 * Another observation is that, the total num of patterns is same for (1,3,7,9) and (2,4,6,8) since they 
 * are symmetric. so we just need to go one input, then * 4 to create a shortcut. Besides, we also need to 
 * go with 5, which is unique
 * 
 * Time complexity: recursion, exponential
 * Space complexity: same as above
 * 
 * @author hpPlayer
 * @date Jun 9, 2016 10:07:51 PM
 */
public class Android_Unlock_Patterns_p351_sol1 {
    public int numberOfPatterns(int m, int n) {
        //observation + backtracking solution. We use backtracking to enumerate all patterns and use observation
        //to avoid same backtracking
        
        //skips record the cross information. For example skips[1][3] = 2 means 1 <-> 3 cross 2
        int[][] skips = new int[10][10];
        
        skips[1][3] = skips[3][1] = 2;
        skips[7][9] = skips[9][7] = 8;
        skips[1][7] = skips[7][1] = 4;
        skips[3][9] = skips[9][3] = 6;
        skips[1][9] = skips[9][1] = skips[3][7] = skips[7][3] = skips[2][8] = skips[8][2] = skips[4][6] = skips[6][4] = 5;
        
        //since we only have 1 - 9 buttons, we use a boolean array to record visited information
        boolean[] visited = new boolean[10];
        
        int result = 0;
        
        for(int i = m; i <= n; i++){
            //test 1, 3, 7, 9
            visited[3] = true;
            result += DFS(i - 1, 3, skips, visited) * 4;
            visited[3] = false;
            
            //test 2, 4, 6, 8
            visited[2] = true;
            result += DFS(i - 1, 2, skips, visited) * 4;
            visited[2] = false;
            
            //test 5
            visited[5] = true;
            result += DFS(i - 1, 5, skips, visited);
            visited[5] = false;            
        }
        
        return result;
    }
    
    
    public int DFS(int count, int curr, int[][] skips, boolean[] visited){
        //found one pattern return 1
        if(count == 0) return 1;
        
        int sum = 0;
        
        for(int i = 1; i <= 9; i++){
            //check all buttons that are either directly connected or have some cross buttons in middle
            if( !visited[i] && (skips[curr][i] == 0 || visited[ skips[curr][i] ])  ){
                //found a valid next button
                visited[i] = true;
                sum += DFS(count - 1, i, skips, visited);
                visited[i] = false;
            }
        }
        
        return sum;
    }
}
