import java.util.*;

/*
244. Shortest Word Distance II

This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times
with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance
between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/


/**
 * Intuitive solution + optimization
 * 
 * We just use a HashMap to record word and the indexes it appears, this is very intuitive
 * The optimization is done in shortest(). Recall the way we calculate the shortest distance in Shortest_Word_Distance_I_p243_sol1, we just need to 
 * record the latest index to find the shortest distance. Similarly, here instead of comparing all indexes, we just need to compare index of one word
 * with the index of the other word that can make the distance closer, which means say now we have word1 appears in i1, word2 appears in i2, i2 > i1,
 * then we just need to move i1 to find next word1 until i1 passes i2, that's because move i2 will not make the distance become closer.
 * 
 * Time complexity: in constructor(): O(N), in shortest(): reduced from O(mn) -> O(m+n)
 * Space complexity: O(N) 
 * 
 * @author hpPlayer
 * @date Mar 8, 2016 4:31:08 PM
 */
public class Shortest_Word_Distance_II_p244_sol1 {
    //key is word, value is indexes it occurs
    HashMap<String, List<Integer>> hs = new HashMap<String, List<Integer>>();
    
    public Shortest_Word_Distance_II_p244_sol1(String[] words) {
        //The idea in constructor is intuitive we use a HashMap to record the occurences of each word
        for(int i = 0; i < words.length; i++){
            if(!hs.containsKey(words[i])){
                hs.put(words[i], new ArrayList<Integer>());
            }
            hs.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        //we will do optimization here, we will only examin indexes that can make the distance closer
        //say word1 appears at index i1, word2 appears at index2, and we have i1 < i2. until i1 pass i2, we will fix word2 at i2
        //and move index i1 to find the next word1
        int i1 = 0, i2 = 0;
        List<Integer> list1 = hs.get(word1);
        List<Integer> list2 = hs.get(word2);
        
        int result = Integer.MAX_VALUE;
        
        //we will iteratively find the shortest distance until one list is exhausted
        //since we only move the other index when it passes another index, so if we have i1/i2 reaches the limit, then 
        //it means we have compared all possible i2/i1 that is smaller than it, any larger i2/i1 will only increase the distance
        //so we can stop here
        while(i1 < list1.size() && i2 < list2.size()){
            //we don't which one is larger, so use Math.abs()
            result = Math.min(result, Math.abs(list1.get(i1) - list2.get(i2)));
            if(list1.get(i1) < list2.get(i2)) i1++;
            else i2++;
        }
        
        return result;
    }
}
//Your WordDistance object will be instantiated and called as such:
//WordDistance wordDistance = new WordDistance(words);
//wordDistance.shortest("word1", "word2");
//wordDistance.shortest("anotherWord1", "anotherWord2");