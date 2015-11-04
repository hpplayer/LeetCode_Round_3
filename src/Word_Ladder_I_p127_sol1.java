import java.util.*;
/*
Word Ladder

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord,
such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

/**
 * BFS and DFS solution
 * 
 * We use bidirectional BFS to expand from two sides and look for shortest path. We will return the length, as long as we found
 * the shortest path.
 * Instead of scanning dict each time, we build new string from existing string and check if it is in dict
 * Regarding build string, We use char[] to build string, so it would be very fast
 * 
 * If we can't extend from one side, then we return 0 indicating we cannot connect two parts
 * 
 * Remark:
 * The final path will include the start and end words, so the actual length should start from 2
 * 
 * The time complexity:
 * We will expand from each string in path. If dict contains n words, and each word has len k
 * Also for HashSet, the add, remove, and contains methods has constant time complexity O(1).
 * So the total running time should be O(26kn)
 * 
 * Space complexity should be O(n), in case variation of one word can include all words in dict
 * @author hpPlayer
 * @date Nov 3, 2015 10:58:02 PM
 */

public class Word_Ladder_I_p127_sol1 {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> fwd = new HashSet<String>();
        fwd.add(beginWord);
        Set<String> bwd = new HashSet<String>();
        bwd.add(endWord);
        
        //start level is 2, so we can include start and end words
        return BFS(fwd, bwd, wordList, 2);
    }
    
    public int BFS(Set<String> forward, Set<String> backward, Set<String> dict, int level){
        //boundary check
        if(forward.isEmpty() || backward.isEmpty()){
            //if we can't extend from one side, then we are done, we just return 0
            return 0;
        }
        
        //we always extend from the side with less nodes
        //here we assume forward has less nodes, if not, we will swap them
        if(forward.size() > backward.size()){
            return BFS(backward, forward, dict, level);
        }
        
        //firstly remove words in forward/backward from dict to avoid duplicate addition
        dict.removeAll(forward);
        dict.removeAll(backward);
        
        //then create a new Set to include nodes from next layer
        Set<String> set = new HashSet<String>();
        
        //try to find each possible next node from forward side
        for(String str : forward){
            for(int i = 0; i < str.length(); i++){
                char[] ary = str.toCharArray();
                for(char c = 'a'; c <= 'z'; c++){
                    ary[i] = c;
                    
                    String temp = new String(ary);
                    
                    if(!backward.contains(temp) && !dict.contains(temp)) continue;
                    
                    //if temp is in backward, then we have connected two parts and found the shortest path
                    if(backward.contains(temp)){
                        return level;
                    }
                    
                    //it we have not connected two parts yet, we will continue search
                    
                    if(dict.contains(temp)){
                        set.add(temp);
                    }
                }
            }
        }
        
        //start next search
        return BFS(set, backward, dict, level + 1);
    }
}
