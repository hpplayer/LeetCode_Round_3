import java.util.*;

/**
 * DFS + topological solution 
 * 
 * This solution is similar to Course_Schedule_II_p210_sol2
 * We build the result string recursively. 
 * 
 * We use extra class to implement hasCycle() and we build string during the backtracking call.
 * 
 * Remark:
 * To include all chars into the result, we have to scan each input word char by char, and record all of them in the HashMap
 * 
 * Time complexity:
 * Build char<->Node table: O(kn) time where k is the avg length of word and n is the length of input[], when building the output
 * string, our longest path should have O(n-1) paths. so total running time should be O(kn)
 * 
 * Space complexity:
 * char<->Node table: O(1), prerequisites list of each node, max one should contains O(26) nodes, so O(26*n) = O(n) space complexity
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 3:55:17 PM
 */

public class Alien_Dictionary_p269_sol2 {
    public String alienOrder(String[] words) {
        //DFS + topological sort solution but we need build the prerequisites table by ourselves
        
        sb = new StringBuilder();
        //hs table record the mapping relationship between chars with nodes
        Map<Character, Node> hs = new HashMap<Character, Node>();
        
        String prev = "";
        for(String word : words){
            //Notice: we need firstly scan the input word and put all chars into the hashMap!!!!!!!!!!!!!!!
            for(char c : word.toCharArray()){
                if(!hs.containsKey(c)) hs.put(c, new Node(c));
            }
            
            //then compare prev string with current word, and search for the first unmatched pair
            for(int i = 0; i < Math.min(prev.length(), word.length()); i++){
                char a = prev.charAt(i);
                char b = word.charAt(i);
                if(a != b){
                    //we have a-> b relationship i.e. a is the prerequisites of b
                    hs.get(b).addNode(hs.get(a));
                    break;
                }
            }
            
            //update prev
            prev = word;
        }
        
        for(Character key : hs.keySet()){
            if( hs.get(key).hasCycle() ) return "";
        }
        
        return sb.toString();
    }
    
    StringBuilder sb;
    
    class Node{
        char c;
        boolean isFinished = false;
        boolean isVisited = false;
        //list that records prerequisites of current char
        List<Node> prerequisites = new ArrayList<Node>();
        
        Node(char c){
            this.c = c;
        }
        
        boolean hasCycle(){
            //previously visited clear node, skip it
            if(isFinished) return false;
            //if we visit a visited node in the same path, then we found a cycle
            if(isVisited) return true;
            isVisited = true;
            //recursively check prerequisites (DFS)
            for(Node temp : prerequisites){
                if(temp.hasCycle()) return true;
            }
            
            //we build string backward, from the bottom recursion to top
            sb.append(c);
            //update current node to be clear node
            isFinished = true;
            isVisited = true;
            return false;
        }
        
        void addNode(Node node){
            prerequisites.add(node);
        }
    }
}
