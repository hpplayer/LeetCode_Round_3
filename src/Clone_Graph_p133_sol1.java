import java.util.*;

/*
133. Clone Graph

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/        
         
/**
 * DFS + HashMap solution
 * 
 * We use HashMap to record the copies of nodes, and we use DFS to visit this graph
 * If current node has not been copied before, then we will firstly create one copy in the HashMap, then visiting its neighbors and using DFS() to return
 * the copy of each neighbor node. Therefore if we found we already have a copy in the HashMap, then it means this node has been fully copied before, and
 * we don't need to visit and copy it again.
 *  
 * 
 * Time complexity: O(N + E) Each node and edge will be visited once
 * Space complexity: O(N) we need store copies of all nodes in the end
 * 
 * Sol1 will use DFS to return the copy of nodes. We may have created the copy before or we may not
 * Sol2 will firstly create the copy of node, then use DFS to make the node more accurate i.e. add copy of neighbors into the list 
 * Both solution works good in the performance, but sol1 is more neat and clean
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 3:05:15 PM
 */
public class Clone_Graph_p133_sol1 {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        //hashMap + DFS solution we will use DFS() to return the copy of current node
        
        //boundary check
        if(node == null) return null;
        
        return DFS(node, new HashMap<UndirectedGraphNode, UndirectedGraphNode>());
    }
    
    public UndirectedGraphNode DFS(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> hs){
        //if we have already created copy of curr node before, then we just return it
        if(hs.containsKey(node)) return hs.get(node);
        
        //if we have not visited this node before, then we need create one 
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        hs.put(node, newNode);
        
        //After the newNode is created, then we insert copy of neighbors into newNode's neighbor list
        for(UndirectedGraphNode temp : node.neighbors){
            //DFS will return copy of node, so we just use DFS to return copies
            newNode.neighbors.add(DFS(temp, hs));
        }
        
        //we have finished the copy of current node, we need return the copy node
        return newNode;
    }
}
