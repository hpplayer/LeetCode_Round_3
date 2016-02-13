/*
323. Number of Connected Components in an Undirected Graph

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

/**
 * Union-find solution
 * 
 * We use a standard union-find method to find nodes that belong to each component
 * Here we use a trick. We initially set result = n, then each time we add a node to existing 
 * component, we will deduct 1 from result. The final val of result will be the num of roots, i.e. the num of components we have
 * 
 * Sol2 provides a DFS solution, which is slower than union-find solution
 * 
 * @author hpPlayer
 * @date Feb 12, 2016 12:28:26 PM
 */

public class Number_of_Connected_Components_in_an_Undirected_Graph_p323_sol1 {
	public static void main(String[] args){
		int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
		System.out.println(new Number_of_Connected_Components_in_an_Undirected_Graph_p323_sol1().countComponents(5, edges));
	}
    public int countComponents(int n, int[][] edges) {
        //classic Union-Find problem. We use union-find method to find different connected components
        int[] roots = new int[n];
        //initialize each node's root to be itself
        for(int i = 0; i < n; i++){
            roots[i] = i;
        }
        
        int result = n;
        
        for(int[] edge : edges){
            int root1 = find(edge[0], roots);
            int root2 = find(edge[1], roots);
            
            if(root1 != root2){
                //update root
                roots[root2] = root1;
                //edge[1] has not been visited before, now it is connected to root1, so deduct result by 1
                result --;
            }            
        }
        
        return result;
    }
    
    public int find(int target, int[] roots){
        if(roots[target] == target){
            return target;
        }
        
        roots[target] = find(roots[target], roots);
        
        return roots[target];
    }
}
