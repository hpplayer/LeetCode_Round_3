import java.util.*;

public class Surrounded_Regions_p130_sol2 {
	public static void main(String[] args){
		String[] strs = {"OXXOX","XOOXO","XOXOX","OXOOO","XXOXO"};
		char[][] board = new char[strs.length][strs[0].length()];
		for(int i = 0; i < board.length; i++){
			board[i] = strs[i].toCharArray();
		}

		
		//char[][] board = {"XOXOXOOOXO","XOOXXXOOOX","OOOOOOOOXX","OOOOOOXOOX","OOXXOXXOOO","XOOXXXOXXO","XOXOOXXOXO","XXOXXOXOOX","OOOOXOXOXO","XXOXXXXOOO"};
		Surrounded_Regions_p130_sol2 test = new Surrounded_Regions_p130_sol2();
		test.solve(board);
	}
	
    public void solve(char[][] board) {
        //Union Find solution. We will firstly mark each edge O, then do union-find to each O, and check if it is in same union with 
        //any edge O
        
        //boundary check
        if(board.length == 0) return;
        int m = board.length, n = board[0].length;
        
        boolean[] isEdge = new boolean[m*n];
        int[] roots = new int[m*n];
        int[] xOffset = {0, 0, -1, 1};
        int[] yOffset = {1, -1, 0, 0};
        
        //firstly initalize the root[]
        for(int i = 0; i < m*n; i++){
        	int x = i/n, y = i%n;
            roots[i] = i;
            if(board[x][y] == 'O' && ( x == 0 || x == m - 1 || y == 0 || y == n - 1))
                	isEdge[i] = true;
        }
        
        //then do union and find to all Os
        for(int i = 0; i < m*n; i++){
        	int x = i/n, y = i%n;
            if(board[x][y] == 'O'){
                    for(int j = 0; j < xOffset.length; j++){
                        int newX = x + xOffset[j];
                        int newY = y + yOffset[j];
                        if( newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] == 'O' ){
                            int root1 = find(roots, newX * n + newY);
                            int root2 = find(roots, i);
                            roots[root2] = root1;
                            isEdge[root1] =  isEdge[root1] || isEdge[root2];
                        }
                }
            }
        }
        //System.out.println(Arrays.toString(isEdge));
        for(int i = 0; i < m * n; i++){
            int x = i/n, y = i%n;
            int root = find(roots, i);
            if(!isEdge[root]) board[x][y] = 'X';
        }
        
    }
    
    public int find(int[] roots, int root){
        if(roots[root] == root) return root;
        roots[root] = find(roots, roots[root]);
        return roots[root];
    }
}
