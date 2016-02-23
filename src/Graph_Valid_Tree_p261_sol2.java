import java.util.*;

/**
 * BFS + topological solution
 * 
 * Based on input edges list, We starts visiting the tree from possible leaf node until we reach the root.
 * If we found the total nodes we visit is n, then it means all nodes can be visited in topological order, in other words there is no loop in the edges
 * Otherwise, we will return false
 * !!!!!!!!!
 * BUT there is one exception. If we only have one node and no edge at all, then we are not able to visit this node from edges[].
 * So we need check this boundary condition to cover all cases
 * 
 * 
 * Update HashMap O(E)
 * Check degree table O(V)
 * BFS O(V) (Arguable?)
 * So the total time complexity would be O(E + V)
 * 
 * Space complexity: O(V)
 * 
 * @author hpPlayer
 * @date Feb 22, 2016 12:21:25 AM
 */
public class Graph_Valid_Tree_p261_sol2 {
    public boolean validTree(int n, int[][] edges) {
        //Topological sort + BFS solution
        
        //boundary check 
        if(n != edges.length + 1 ) return false;
        
        //in case we don't have edges
        if(n == 1 && edges.length == 0) return true;
        
        //degree array that records the degrees of each node
        //we also use degrees to control the nodes that can be added into que.
        //Visited node will have degrees < 1, therefore we wouldn't repeat visited node
        int[] degrees = new int[n];
        //hashMap that records the connection among nodes
        Map<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        
        //update hashMap and degrees based on input array
        for(int[] edge : edges){
            int a1 = edge[0];
            int a2 = edge[1];
            
            degrees[a1]++;
            degrees[a2]++;
            
            if(!hs.containsKey(a1)) hs.put(a1, new ArrayList<Integer>());
            if(!hs.containsKey(a2)) hs.put(a2, new ArrayList<Integer>());
            
            hs.get(a1).add(a2);
            hs.get(a2).add(a1);
        }
        
        //use Que to visit nodes
        Queue<Integer> que = new LinkedList<Integer>();
        
        for(int i = 0; i <degrees.length; i++){
            //we add leaf node into the que
            if(degrees[i] == 1) que.offer(i);
        }
        
        //we compare nodes that can be added into que with n
        //we will not add nodes in loop into queue, so this comparsion is useful
        int count = 0;
        
        while(!que.isEmpty()){
            count++;
            int node = que.poll();
            if(hs.containsKey(node)){
                for(int nextNode : hs.get(node)){
                    if(--degrees[nextNode] == 1){
                        que.offer(nextNode);
                    }
                }
            }
        }
        
        //finally compare count with n
        return count == n;
    }
}
