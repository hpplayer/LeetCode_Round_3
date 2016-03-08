import java.util.HashMap;

/**
 * DFS solution
 * 
 * In this solution, instead of returning copy node from DFS(), we will firstly create the copy of current node, then doing DFS on our new copy
 * So our return type of DFS is void
 * 
 * Time complexity: O(N + E), which means all nodes and edges in the graph will be visited once
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 2:45:26 PM
 */
public class Clone_Graph_p133_sol2 {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        //boundary check
        if(node == null) return null;
        
        //for each node that put into DFS, we should already have the copy of it in hashMap
        //therefore we need firstly copy the start node, then start DFS
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hs = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        hs.put(node, new UndirectedGraphNode(node.label));
        DFS(node, hs);
        
        return hs.get(node);
    }
    
    public void DFS(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> hs){
        //get the copy of current node 
        UndirectedGraphNode curr = hs.get(node);
        
        //begin insert copy of neighbor nodes into the list
        for(UndirectedGraphNode temp : node.neighbors){
            if(!hs.containsKey(temp)){
                //if we have not visited this neighbor before, then we need use DFS to create and update it
                hs.put(temp, new UndirectedGraphNode(temp.label));
                DFS(temp, hs);
            }
            //insert the copy of neighbors into current copy
            curr.neighbors.add(hs.get(temp));  
        }
    }
}
