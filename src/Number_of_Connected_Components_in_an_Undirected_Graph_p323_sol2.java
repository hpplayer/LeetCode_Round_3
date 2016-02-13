import java.util.*;
/**
 * DFS solution
 * 
 * We create an adjacency list to record all connected nodes
 * Then scan from node 0 to n-1, and use a visited set to check if we have visited this node before
 * If not, then we use DFS to visit all nodes that are connected directly/indirectly to it.
 * If yes, then we do nothing, as we have visited this node before
 * So, in our main program, the num of times we found an unvisited node is the num of components we have in the input
 * 
 * 
 * @author hpPlayer
 * @date Feb 12, 2016 12:41:16 PM
 */
public class Number_of_Connected_Components_in_an_Undirected_Graph_p323_sol2 {
	public static void main(String[] args){
		int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
		System.out.println(new Number_of_Connected_Components_in_an_Undirected_Graph_p323_sol2().countComponents(5, edges));
	}
	
    public int countComponents(int n, int[][] edges) {
        //DFS solution. Since the given input is undirected edge. Given us a node in a connected component, we can find all nodes in this 
        //component by following the undirected edge. Therefore, we use a visited set to record nodes we visited, next time when we see an
        //unvisited node, we will know this is the a node in a new univisted component
        
        //we need adjacency list to record the node and its connected nodes
        Map<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        
        //update adjacency list
        for(int[] edge : edges){
            if(!hs.containsKey(edge[0])) hs.put(edge[0], new ArrayList<Integer>());
            if(!hs.containsKey(edge[1])) hs.put(edge[1], new ArrayList<Integer>());
            
            hs.get(edge[0]).add(edge[1]);
            hs.get(edge[1]).add(edge[0]);
        }
        
        int result = 0;
        
        //visited set to help us avoid going back to visited nodes and help us identify which node is new
        Set<Integer> visited = new HashSet<Integer>();
        
        //scan all nodes, and count num of components
        for(int i = 0; i < n; i++){
            //add node to visited list while check if we have visited this node before
            if(visited.add(i)){
                result ++;
                DFS(visited, i, hs);
            }
        }
        
        return result;
    }
    
    public void DFS(Set<Integer> visited, int curr, Map<Integer, List<Integer>> hs){
        //boundary check, to help handle boundary nodes that has no edge i.e. isolated node
        if(!hs.containsKey(curr)){
            return;
        }
        
        for(Integer temp : hs.get(curr)){
            //add node to visited list while check if we have visited this node before
            if(visited.add(temp)){
                DFS(visited, temp, hs);
            }
        }
    }
}
