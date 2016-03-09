/*
243. Shortest Word Distance

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/**
 * Intuitive solution
 * 
 * We observed that the min difference between word1 and word2 will only appears in the latest index
 * Say word1 firstly appears in i1, word2 appears in i2, if word1 appears in i3 again, then the latest difference will be Min(i2 - i1, i3 - i2), so we just 
 * need to update the index as long as we see an input appear, then we can get the solution 
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 8, 2016 4:01:22 PM
 */
public class Shortest_Word_Distance_I_p243_sol1 {
    public int shortestDistance(String[] words, String word1, String word2) {
        //intuitive solution, update index as long as we see a word appears, then update result if needed
        int index1 = -1, index2 = -1;
        int result = Integer.MAX_VALUE;
        
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                //if we have recorded word2 before
                if(index2 != -1) result = Math.min(result, i - index2);
                //update index
                index1 = i;
            }else if(words[i].equals(word2)){
                //if we have recorded word1 before
                if(index1 != -1) result = Math.min(result, i - index1);
                //update index
                index2 = i;
            }
        }
        
        return result;
    }
}
