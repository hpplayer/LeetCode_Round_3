
public class Surrounded_Regions_p130_sol2 {
    public void solve(char[][] board) {
        if(board.length == 0) return;
        
        int m = board.length, n = board[0].length;
        
        int[] roots = new int[m * n];
        boolean[] hasEdgeO = new boolean[m * n];
        
        for(int i = 0; i < m * n; i++){
            roots[i] = i;
            int x = i / n;
            int y = i % n;
            if(board[x][y] == 'O' && (x == 0 || x == m - 1 || y == 0 || y == n - 1)){
                hasEdgeO[i] = true;
            }
        }
        
        for(int i = 0; i < m * n; i++){
            int x = i / n;
            int y = i % n;
            if(board[x][y] == 'O'){
                if(x - 1 >= 0 && board[x-1][y] == 'O'){
                    union(i, i - n, roots, hasEdgeO);
                }
                
                if(y - 1 >= 0 && board[x][y-1] == 'O'){
                    union(i, i - 1, roots, hasEdgeO);
                }
            }
        }
        
        for(int i = 0; i < m*n; i++){
            int x = i / n;
            int y = i % n;
            if(board[x][y] == 'O'){
                int root = roots[i];
                if(!hasEdgeO[root]) board[x][y] = 'X';
            }
        }
    }
    
    public void union(int r1, int r2, int[] roots, boolean[] hasEdgeO){
        int root1 = find(r1, roots);
        int root2 = find(r2, roots);
        boolean EdgeO = hasEdgeO[root1] || hasEdgeO[root2];
        roots[root1] = root2;
        hasEdgeO[root1] = EdgeO;
    }
    
    public int find(int root, int[] roots){
        if(roots[root] == root) return root;
        roots[root] = find(roots[root], roots);
        return roots[root];
    }
}
