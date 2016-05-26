import java.util.*;

/**
 * Union-Find solution
 * 
 * We will do union-find to all non-edge 'O' cells, and use a boolean[] to record whether a union has edge 'O'
 * Then we check the root of each 'O' and decide if this 'O' is in a union that has edge 'O'
 * Obviously, we need boolean[root] to be true in those unions that has edge 'O'
 * 
 * When update root of two nodes, it doesn't matter if we let root2 points to root1 or root1 points to root2,
 * since we will finally use find() to get the real root. As long as the direction is kept, we can get correct
 * union at last. But accordingly we need update boolean[] correspondingly. Ex. we let roots[root2] = root1, i.e.
 * use root1 as new root, then we will update boolean[root1]. If we let roots[root1] = root2, i.e. use root2 as
 * new root, then we will update boolean[root2] accordingly.
 * 
 * Time complexity: O(mn)
 * initialization of roots[]: O(mn), update non-edge 'O' O(mn), final update on board O(mn)
 * 
 * Space complexity: O(mn)
 * 
 * 
 * @author hpPlayer
 * @date May 25, 2016 9:35:06 PM
 */
public class Surrounded_Regions_p130_sol2 {
    public void solve(char[][] board) {
        //boundary check
        if(board.length == 0) return;
        int m = board.length, n = board[0].length;
        
        int[] roots = new int[m*n];
        boolean[] hasEdgeCell = new boolean[m*n];
        
        //initalize roots[] and hasEdgeCell[]
        for(int i = 0; i < m*n; i++){
            int x = i/n, y = i%n;
            roots[i] = i;
            if(board[x][y] == 'O' && (x == 0 || x == m-1 || y == 0 || y == n - 1)) hasEdgeCell[i] = true;
        }
        
        //since we only visit non-edge cells, we need check four directions to cover all connected cells
        int[] xOffset = {0, 0, -1, 1};
        int[] yOffset = {-1, 1, 0, 0};
        
        //visit non-edge 'O' to update root
        for(int i = 1; i < m - 1; i++){
            for(int j = 1; j < n - 1; j++){
                //skip non 'O' cell
                if(board[i][j] != 'O') continue;
                
                for(int k = 0; k < xOffset.length; k++){
                    int newX = i + xOffset[k];
                    int newY = j + yOffset[k];
                    if(board[newX][newY] == 'O'){
                        //we only update root when (newX, newY) is also a 'O' cell
                        
                        //get roots for two cells
                        int root1 = find(roots, i*n + j);
                        int root2 = find(roots, newX * n + newY);
                        
                        //since two unions are connected now, we need update one root to be another one
                        //it does not matter which root will be override, as long as we can keep order, we 
                        //will be fine
                        
                        //here we choose root1 as new root for two unions
                        roots[root2] = root1;
                        //since root1 is now the root for two unions, we need update its information based on
                        //the newly connected union
                        hasEdgeCell[root1] = hasEdgeCell[root1] || hasEdgeCell[root2];
                    }
                }
            }
        }
        
        //update board
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(board[i][j] == 'O'){
                    //find root for this cell
                	int root = find(roots, i * n + j);
                    //since root contains information for its union, we look up root to see if this union has
                    //edge O
                    if(!hasEdgeCell[root]) board[i][j] = 'X';
                }
            }
        }
        
    }
    
    public int find(int[] roots, int root){
        if(roots[root] == root) return root;
        roots[root] = find(roots, roots[root]);
        return roots[root];
    }
}
