import java.util.*;

/*

Alien Dictionary

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
1. You may assume all letters are in lowercase.
2. If the order is invalid, return an empty string.
3. There may be multiple valid order of letters, return any one of them is fine.
		
*/	

/**
 * BFS + topological solution
 * 
 * This solution is very similar to Course_Schedule_I_p207_sol2, but now we need to create the prerequisites table before 
 * topological sort
 * 
 * The prerequisites table can be created by scanning the input array and searching the first unmatched char of a pair. 
 * Also, in this problem, our inputs are characters so it is better to use HashMap instead of array.
 * 
 * Like Course_Schedule_I_p207_sol2, we use a hashMap to record the basic char and advanced chars that depend on basic char.
 * We use another hashMap to record the numbers of incoming edges. During the BFS process, we will insert char with zero incoming
 * edge into the queue. Then we pop chars from queue, cut outgoing edges, and add new chars if they have zero incoming edge now
 * 
 * Finally we just need to check if output string has same length with degree table, which records the count of individual char
 * 
 * Time complexity:  create prerequisites table costs O(kn) assume average length of word is k, input array has length n.
 * There would be totally O(n-1) edges, we will visit each of them in BFS process. So total time complexity should be O(kn)
 * 
 * Space complexity: degrees costs O(1) space, hs costs O(n-1) space, so totally O(n-1) spaces
 * 
 * Sol1 is BFS solution
 * Sol2 is DFS solution
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 2:59:46 PM
 */
public class Alien_Dictionary_p269_sol1 {
	public static void main(String[] args){
		System.out.println(new Alien_Dictionary_p269_sol1().alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
	}
    public String alienOrder(String[] words) {
    	//BFS + topological sort solution but we need build the prerequisites table by ourselves
    	
        //map records the basic char and advanced char that depends on it
        Map<Character, List<Character>> hs = new HashMap<Character, List<Character>>();
        //degrees records the num of incoming edges, since we don't know which chars appear in the input, we have to
        //use a HashMap instead of array[]
        Map<Character, Integer> degrees = new HashMap<Character, Integer>();
        
        //part 1, create prerequisites table 
        String prev = "";
        
        for(String word : words){
            //for each word in words[], we will scan it once and record chars in it
            for(char c : word.toCharArray()){
                if(!degrees.containsKey(c)) degrees.put(c, 0);
            }
            
            for(int i = 0; i < Math.min(prev.length(), word.length()); i++){
                char a = prev.charAt(i);
                char b = word.charAt(i);
                //looking for the first pair of chars that are not matched
                if(a!=b){
                    //a is basic char and b is an advanced char that depends on a
                    
                    //add a-> b relationship into the hs table
                    if(!hs.containsKey(a)) hs.put(a, new ArrayList<Character>());
                    hs.get(a).add(b);
                    //update degrees table for advanced char
                    degrees.put(b, degrees.get(b)+1);
                    break;
                }
            }
            
            //update prev to be word
            prev = word;
        }
        
        //part 2, use BFS approach to topologically visit the graph
        Queue<Character> que = new LinkedList<Character>();
        StringBuilder sb = new StringBuilder();
        
        //scan the hs table and add char with zero incoming edge into the queue 
        for(Character key : degrees.keySet()){
            if(degrees.get(key) == 0) que.offer(key);
        }
        
        while(!que.isEmpty()){
            int size = que.size();
            
            for(int i = 0; i < size; i++){
                //poll a char from que and cut its outgoing edges
                Character c = que.poll();
                sb.append(c);
                //not necessarily each char has outgoing edges, so we need check first 
                if(hs.containsKey(c)){
                    for(char temp : hs.get(c)){
                        degrees.put(temp, degrees.get(temp)-1);
                        //if we found a new char that has zero incoming edge, then add it into the queue
                        if(degrees.get(temp) == 0) que.offer(temp);
                    }
                }
            }
        }
        
        //we check if result string has same length with degrees.size(), which indicates the total individual chars we have in input
        //if length equals, then we return sb.toString(), otherwise return empty string
        return sb.length() == degrees.size()? sb.toString() : "";
    }
}
