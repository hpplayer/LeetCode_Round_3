/*
200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/

/**
 * DFS solution
 * 
 * We just start doing DFS on all cells in grid marked with '1', then replace all connected '1' cells to '0'.
 * So that we wouldn't to visit those cells again. Next time when we see a '1' cell, it must indicates a start of new island
 * 
 * 
 * Time complexity: O(mn)
 * 
 * This problem can also be solved by BFS, we just need an extra class to store x and y indexes and use a queue to implement BFS
 * Sol2 uses a union-find solution
 * 
 * @author hpPlayer
 * @date Feb 10, 2016 9:08:34 PM
 */

public class Number_of_Islands_I_p200_sol1 {
    public int numIslands(char[][] grid) {
        //DFS solution, we will replace visited "1" by "2", so then we can finish an island and not visit it again
        int result = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    result ++;
                    grid[i][j] = '0';
                    //use DFS to visit each cell of this island
                    DFS(grid, i, j);
                }
            }
        }
        
        return result;
    }
    
    public void DFS(char[][] grid, int x, int y){
        //we use DFS to visit all cells of current island, so we need four directions
        //if only use right and bot direction, we will miss all cells in left direction and treat those cells as other islands
        int[] xOffset = {1, -1, 0, 0};
        int[] yOffset = {0, 0, 1, -1};
        
        for(int i = 0; i < 4; i++){
            int newX = x +xOffset[i];
            int newY = y + yOffset[i];
            
            if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '1'){
                grid[newX][newY] = '0';
                DFS(grid, newX, newY);
            }
        }
    }
}
