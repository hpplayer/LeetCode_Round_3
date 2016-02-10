/**
 * Union find solution
 * 
 * In this solution, we use union-find method to find the root cell for each "1" cell.
 * As long as "1" cells are connected, we will finally update their root cell to be a same cell.
 * So finally we just need to check how many different root cells we have in the result roots[] to find how many islands we have
 * 
 * Time complexity: O(mn)
 * 
 * Remark:
 * We convert 2D indexes to a 1D array. therefore the index in root[] will be i * n + j, where n is length of each row, and i, j is current x, y indexes
 * @author hpPlayer
 * @date Feb 10, 2016 9:30:57 PM
 */
public class Number_of_Islands_I_p200_sol2 {
    public int numIslands(char[][] grid) {
        //union find solution
        //the basic idea is to find the root cell for all '1' cells
        //for cells in same island, their root cell will be updated to be same root
        //finally, we just need to check how many roots we have in the end
        
        //boundary check
        if(grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        
        int[] roots = new int[m*n];
        
        //initialize roots[], for 1 cell, we update its initial root to be itself, for 0 cell, we set its val to -1
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    roots[n * i + j] = n * i + j;
                }else{
                    roots[n * i + j] = -1;
                }
            }
        }
        
        //now we will scan each 1 cell, so only need to look right and bot cell to avoid duplicate visit 
        int[] xOffset = {1, 0};
        int[] yOffset = {0, 1};
        
        //we scan the array again and find true root cell for each 1 root
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    for(int k = 0; k < 2; k++){
                        int newX = i + xOffset[k];
                        int newY = j + yOffset[k];
                        if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '1'){
                             //root cell for current cell
                             int root1 = find(i * n + j, roots);
                             //root cell for new cell
                             int root2 = find(newX * n + newY, roots);
                             //update root cell of new cell to be the root of old cell indicating they belong to same island
                             roots[root2] = roots[root1];
                        }                        
                    }
                }
            }
        }
        
        //finally scan the root[] and check how many roots we have
        int result = 0;
        
        for(int i = 0; i < roots.length; i++){
            if(roots[i] == i) result++;
        }
        
        return result;
    }
    
    public int find(int target, int[] roots){
        //find function can be used to find and update roots
        
        if(roots[target] == target){
            //boundary case
            return target;
        }
        
        //recursively find the root
        roots[target] = find(roots[target], roots);
        
        return roots[target];
    }
}
