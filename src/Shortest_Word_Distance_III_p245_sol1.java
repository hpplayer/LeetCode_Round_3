/*
245. Shortest Word Distance III

This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°makes¡±, word2 = ¡°coding¡±, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/

/**
 * Intuitive solution
 * 
 * This problem is an advanced version of Shortest_Word_Distance_I_p243_sol1 with a small modification.
 * We still use the idea of just comparing the latest index of two inputs
 * The main body is same, each time we found word1 or word2, we will calculate difference of current index with the index of other word
 * However now, we allow two inputs to have the same value. In our program, if two inputs are same, then they will always be caught in first if block, which
 * means we will always update index1 if two inputs are same. Therefore we just need to check if two inputs are same, then check if we have recorded index1
 * before, then our program will handle same input case. 
 * So just a small modification and we are good to go!
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 8, 2016 5:18:20 PM
 */
public class Shortest_Word_Distance_III_p245_sol1 {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        //intuitive idea. similar to Shortest_Word_Distance_I_p243_sol1, but now we need handle same input case in the first if block
    	//we still update the index each time we see a word appears, and check distance for each update, since keep old index will not help us get a shorter distance
        int index1 = -1, index2 = -1;
        int result = Integer.MAX_VALUE;
        
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
            	//index2 will only be updated when two inputs are different
            	//if two inputs are same, then we will only update index1, so if index2 is updated, we are sure two inputs are different
                if(index2 != -1) result = Math.min(result, i - index2);
                //Here we only compare index1 with i, if two inputs are same. If two inputs are different, we require comparing index1 with index2
                if(word1.equals(word2) && index1 != -1) result = Math.min(result, i - index1);
                index1 = i;
            }else if(words[i].equals(word2)){
                //we will come to this block only when two inputs are different, therefore the body will be same with Shortest_Word_Distance_I_p243_sol1
                if(index1 != -1) result = Math.min(result, i - index1);
                index2 = i;
            }
        }
        
        return result;
    }
}
