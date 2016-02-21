import java.util.*;

/*
332. Reconstruct Itinerary

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].

All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
*/

/**
 * DFS + graph problem
 * 
 * This problem can be solved by naive DFS solution, but in that way we will not utilize the given condition that each "edge" will be visited once
 * Actually we are told that there must be a valid itinerary from JFK to destination. And each edge (ticket) will be visited only once. So this path
 * is called Eulerian path. For such path, we can use DFS to visit the path, then add node when we finish its subpath.
 * 
 * Time complexity: each path will be visited only once, so time complexity should be O(n)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Feb 19, 2016 10:09:53 PM
 */

public class Reconstruct_Itinerary_p332 {
    public List<String> findItinerary(String[][] tickets) {
        //This is a pure graph problem. The graph has such properties that we have a valid path to connect all vetices and each edge will
        //be only visited once. Such path is called Eulerian path, and we can use hierholzer¡¯s algorithm to visit such path.
        //This alg uses DFS to visit each node, if we get stucked in one node, then we add this node to the head of list, and retreat.
        //We will keep retreat until we reach some node that has another paths that we haven't visited yet. So, actually we are adding
        //item to the result list backward
        
        //use pq to make the string are sorted as lexical order 
        Map<String, PriorityQueue<String>> hs = new HashMap<String, PriorityQueue<String>>();
        
        //update hs map
        for(String[] ticket : tickets){
            String str1 = ticket[0];
            String str2 = ticket[1];
            
            if(!hs.containsKey(str1)) hs.put(str1, new PriorityQueue<String>());
            hs.get(str1).offer(str2);
        }
        
        //since we will add new element in the head of list, it is better to use linkedlist to get O(1) head insertion time
        LinkedList<String> result = new LinkedList<String>();
        
        //then we do DFS starting from JFK
        DFS(hs, result, "JFK");
        
        return result;
    }
    
    public void DFS(Map<String, PriorityQueue<String>> hs, LinkedList<String> result, String str){
        //if we reach destination, then return back
        //In an Eulerian path, only dest does not have outgoing path, so we wouldn't have it as key in HashMap
        if(!hs.containsKey(str)){
            result.offerFirst(str);
            return;
        }
        
        PriorityQueue<String> pq = hs.get(str);
        
        while(!pq.isEmpty()){
            DFS(hs, result, pq.poll());
        }
        
        //we always insert new airport in the end and add it to the head of result list
        result.offerFirst(str);
    }
}
