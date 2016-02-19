/*
329. Longest Increasing Path in a Matrix

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary
(i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

/**
 * DFS and top-down DP solution
 * 
 * We create a cache matrix to record all cells and paths that we have visited, so we will not revisit past paths.
 * For the direction, we can just pick the ascending direction, and still able to find the global longest path.
 * This is because, if we get a descending longest path, then if we look from another way, it will become a ascending longest path. So they are same 
 * Other parts are very intuitive.
 * 
 * Time complexity: O(mn), as we will not visit visited cells i.e. each cell will be visited only once
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Feb 17, 2016 4:34:43 PM
 */

public class Longest_Increasing_Path_in_a_Matrix_p329_sol1 {
    public int longestIncreasingPath(int[][] matrix) {
        //boundary check
        if(matrix.length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        
        int[][] cache = new int[m][n];
        int maxLen = 0;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(cache[i][j] == 0){
                    //we only start with unvisited cells
                    //get the max path from current cell, and update global max path if possible
                    maxLen = Math.max(maxLen, DFS(i, j, matrix, cache));
                }
            }
        }
        
        return maxLen;
    }
    
    public int DFS(int x, int y, int[][] matrix, int[][] cache){
        if(cache[x][y] != 0){
            //we have previously visited current cell, we just need to return the previous val
            return cache[x][y];
        }
        
        int[] xOffset = {1, -1, 0, 0};
        int[] yOffset = {0, 0, 1, -1};
        int maxLen = 0;
        
        //set cache value, so we will not come back
        cache[x][y] = 1;
        
        //check four neighbor cells to see if our current path can extend
        for(int i = 0; i < xOffset.length; i++){
            int newX = x + xOffset[i];
            int newY = y + yOffset[i];
            
            if(newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length && matrix[newX][newY] > matrix[x][y]){
                //as long as new val is larger than curr val, then it will be a valid path
                //we will check all neighbors to get the max len
                maxLen = Math.max(maxLen, DFS(newX, newY, matrix, cache)); 
            }
        }
        
        cache[x][y] += maxLen;
        return cache[x][y];
    } 
}
