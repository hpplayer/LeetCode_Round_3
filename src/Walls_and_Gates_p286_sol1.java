import java.util.*;

/*
286. Walls and Gates

You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/

/**
 * BFS solution
 * 
 * This is a shortest path problem, BFS usually works great in such problem. We need update cell value of empty space to be the shortest path from a gate
 * So we start from all gates and update cell value when we firstly get there.
 * Notice: since the input has cell value for 0 gate, in our program the cell value itself will be the step counter. So we can directly use cell value
 * to count steps we need to get to the empty spot
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 8:26:38 PM
 */


public class Walls_and_Gates_p286_sol1 {
    private class MyNode{
        //since the input is optimized (cell value for gate is 1), we don't need count variable. The cell value itself will be step counter
        int x;
        int y;
        MyNode(int a, int b){
            x = a;
            y = b;
        }
    }
    
    
    public void wallsAndGates(int[][] rooms) {
        //BFS solution. The base level contains all gates. From all gates, we take a look and move one step further. Therefore the steps we 
        //need to find each empty space will be the shortest path
        
        //boundary check
        if(rooms.length == 0) return;
        
        Queue<MyNode> que = new LinkedList<MyNode>();
        
        int m = rooms.length, n = rooms[0].length;
        
        //we add all gate nodes into rooms
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(rooms[i][j] == 0) que.offer(new MyNode(i, j));
            }
        }
        
        //do bfs from gates
        int[] xOffset = {1, -1, 0, 0};
        int[] yOffset = {0, 0, -1, 1};
        
        while(!que.isEmpty()){
            MyNode curr = que.poll();
            for(int i = 0; i < xOffset.length; i++){
                int newX = curr.x + xOffset[i];
                int newY = curr.y + yOffset[i];
                
                //we only move and update in empty spaces
                if(newX >= 0 && newX < m && newY >= 0 && newY < n && rooms[newX][newY] == Integer.MAX_VALUE){
                    //since gate value is 0, cell value itself is count variable
                    rooms[newX][newY] = rooms[curr.x][curr.y] + 1;
                    que.offer(new MyNode(newX, newY));
                }
            }
        }
    }
}
