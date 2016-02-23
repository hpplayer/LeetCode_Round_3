/*
261. Graph Valid Tree

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: ¡°a tree is an undirected graph in which any two vertices are connected by exactly one path.
In other words, any connected graph without simple cycles is a tree.¡±
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

/**
 * Union-find solution
 * 
 * All nodes should be connected and treated as one component, therefore we can use union-find approach to check the inputs
 * We firstly set each node's root to be itself. Then update the root when we read edges If we found two nodes in new edge already have same root
 * then we know this new edge will make a cycle in the component marked by root, therefore we should return false in such case
 * 
 * Time complexity: In worst case, we have to go all vertices to get the root, and we have to do this for all nodes on edges, so the time complexity
 * should be O(VE)
 * Space complexity: O(V)
 * 
 * This problem can also be solved by DFS or Topological sort with BFS approach
 * But they are lengthy and slower than this approach probably due to the cost on stack and queue
 * 
 * Sol2 provides a BFS solution, but we need to take extra care of extreme case that we only have one node and zero edges
 * 
 * @author hpPlayer
 * @date Feb 21, 2016 5:21:51 PM
 */
public class Graph_Valid_Tree_p261_sol1 {
    public boolean validTree(int n, int[][] edges) {
        //the best & fast approach to this problem is union-find approach
        //Initially we assign each node's root to be itself, then we update roots[] based on edges
        //if we found two nodes of new edge share same root node, then we should return false as this new edge
        //will make a cycle of nodes.
        
        //precheck, a valid tree should have n nodes and n - 1 edges
        if(n != edges.length + 1) return false;
        
        int[] roots= new int[n];
        //initialize root to be node itself
        for(int i = 0; i < roots.length; i++) roots[i] = i;
        
        for(int[] edge : edges){
            int root1 = find(roots, edge[0]);
            int root2= find(roots, edge[1]);
            
            //if we found two nodes on new edge already belongs to one component, then new edge will make 
            //this component becomes a cycle, so return false
            if(root1 == root2) return false;
            
            //otherwise merge two components into one
            roots[root1] = root2;
        }
        
        //all edges checked
        return true;
    }
    
    public int find(int[] roots, int root){
        //found root, return
        if(roots[root] == root) return root;
        
        roots[root] = find(roots, roots[root]);
        
        return roots[root];
    }
}
