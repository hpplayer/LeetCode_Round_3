import java.util.*;

/*
310. Minimum Height Trees

For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Hint:

How many MHTs can a graph have at most?
Ans: At most two MHTs. For nodes >= 3, we will at least has one node as a child, therefore it can be removed to reduce the height

Note:

(1) According to the definition of tree on Wikipedia: ¡°a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.¡±

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
*/

/**
 * Topological + BFS solution
 * 
 * First of all, we need to be clear that there will be only 2 MSTs in total. All other nodes will only be children of these one or two nodes.
 * The solution here is to scan nodes starting from leaves until the root node. The way we used to scan nodes is similar to doing topological sort
 * with BFS. We create an adjacency list using HashMap, and use an array to record the degrees for each node. When we finish current layer of leaves,
 * we will decrease the degree to all node that connected to leaves. If we find the updated node has degree = 1, then we will add it to our queue. 
 * We keep doing this until we have only one or two nodes. In either case, we have got the target root node of MHTs.
 * 
 * In this algorithm, we firstly create a HashMap for all nodes, it costs O(n-1) where n - 1 is num of edges
 * Then, we scan the tree following all edges and all nodes shall be visited only once, so it costs O(n)
 * Therefore the running time is O(n)
 * 
 * Space complexity should also be O(n)
 * 
 * @author hpPlayer
 * @date Feb 11, 2016 11:39:21 PM
 */
public class Minimum_Height_Trees_p310_sol1 {
	public static void main(String[] args){
		//int[][] edges = {{0,1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}  };
		int[][] edges = {};
		System.out.println(findMinHeightTrees(1, edges));
	}
	   public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
	        //the basic idea is to scan nodes from leaves to root
	        //we use topological way to scan nodes, and stop when we have one or two nodes left
	        
	        //boundary check
	        LinkedList<Integer> result = new LinkedList<Integer>();
	        if(n <= 1){
	            if(n == 1){
	                //return root itself
	                result.add(0);
	            }
	            
	            return result;
	        }
	        
	        //degree table
	        int[] degrees = new int[n];
	        
	        //adjacency list
	        Map<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
	        
	        for(int[] edge : edges){
	            int NodeA = edge[0];
	            int NodeB = edge[1];
	            
	            //update degree array
	            degrees[NodeA] ++;
	            degrees[NodeB] ++;
	            
	            //update adjacency list
	            if(!hs.containsKey(NodeA)) hs.put(NodeA, new ArrayList<Integer>());
	            if(!hs.containsKey(NodeB)) hs.put(NodeB, new ArrayList<Integer>());
	        
	            hs.get(NodeA).add(NodeB);
	            hs.get(NodeB).add(NodeA);
	        }
	        
	        //we scan degrees[] and add leaves node to result list (acutally it is a queue)
	        //leaves node are those nodes who has degree = 1
	        
	        for(int i = 0; i < degrees.length; i++){
	            if(degrees[i] == 1){
	                result.offerLast(i);
	            }
	        }
	        
	        //start topological BFS and scan tree from leave nodes to root
	        
	        //boundary condition is we have one or two nodes left. In either way we have found roots for MHT
	        //Notice: at most we can only have 2 MHTs
	        while(n > 2){
	            int size = result.size();
	            n -= size;
	            for(int i = 0; i < size; i++){
	                int curr = result.pollFirst();
	                //remove this node from scanning process
	                for(int node : hs.get(curr)){
	                    //if this removal makes other nodes have degree = 1, then we add those new "leaves" nodes to result list
	                    if(--degrees[node] == 1){
	                        result.offerLast(node);
	                    }
	                }
	            }
	        }
	        
	        return result;
	    }
}
