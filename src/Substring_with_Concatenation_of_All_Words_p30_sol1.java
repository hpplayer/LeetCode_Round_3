import java.util.*;
/*
Substring with Concatenation of All Words

You are given a string, s, and a list of words, words, that are all of the same length.
Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

/**
 * Sliding window with expected[] and real[] solution
 * 
 * Similar to other sliding window problem, we will use a data structure to record the appearance of each word in words, and the 
 * real appearance of each word in string s. In this problem, we use HashMap to count the appearance, since the key will be string.
 * If we found current word in s does not exist in words[], we will move our window skip this word. Otherwise we increase the count
 * of this word in visited hashMap. If we found the appearance of current word exceeds the expected times, we will keep moving left
 * pointer until we make the appearance of current word valid. Otherwise, we increase the global variable count to indicate we found
 * one more word in words. If count == words.length, then it means we have found a valid substring contains all word in words,
 * we just record the index of left pointer, and let left pointer skip the leftmost word in window and start next search.
 * Since both left and right pointers skip len each step, to cover all cases, we have to use an outer loop from 0 to len - 1 to try
 * each possible starting index
 * 
 * Time complexity is O(n), where n is the length of string s. This is because we only use two pointer to scan the string s, each
 * word in string s would be at most visited twice.
 * 
 * Space complexity: O(len(words)) as we need two hashMaps to record the appearance of each word in words[]
 * 
 * Remark:
 * 1. remember we always move left and right pointer len unit in each step!!!!!!!!!!!!!!
 * 2. This solution is similar to solution Minimum_Window_Substring_p76_sol1
 * 
 * @author hpPlayer
 * @date Nov 9, 2015 1:44:33 AM
 */
public class Substring_with_Concatenation_of_All_Words_p30_sol1 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        //boundary check
        if(words.length ==0 || s.length() == 0 || s.length() < words.length) return result;
        
        //get length of each word
        int len = words[0].length();
        
        Map<String, Integer> expected = new HashMap<String, Integer>();
        
        //update expected hashMap
        for(String word : words){
            if(!expected.containsKey(word)){
                expected.put(word, 1);
            }else{
                expected.put(word, expected.get(word)+1);
            }
        }
        
        //We update left/right pointer by len each time, to cover all cases, we choose different start point from 0 to len-1
        for(int i = 0; i < len; i++){
            int left = i;
            int count = 0;
            Map<String, Integer> visited = new HashMap<String, Integer>();
            
            for(int right = i; right + len<= s.length(); right+= len){
                //right pointer moves len each time
                String word = s.substring(right, right + len);
                //skip unrelated word
                if(!expected.containsKey(word)){
                    //if we found an unrelated word, then we know our substring will not start before this index
                    left = right + len;
                    count = 0;
                    visited = new HashMap<String, Integer>();
                    continue;
                }
                
                //update visitied map for current word
                if( !visited.containsKey(word) ){
                    visited.put(word, 1);
                }else{
                    visited.put(word, visited.get(word)+1);
                }
                
                //if current word appears more than exptected times
                //then we will move left pointer until we make it valid
                while(expected.get(word) < visited.get(word)){
                    String temp = s.substring(left, left + len);
                    visited.put(temp, visited.get(temp)-1);
                    count --;
                    left += len;
                }
                
                //now word is a valid part of substring, we increase the count
                count++;
                
                //if we have found exactly num of word in words, then we found a valid substring
                if(count == words.length){
                    result.add(left);
                    //then we move left pointer skip the leftmost word and search for next valid substring
                    String temp = s.substring(left, left + len);
                    visited.put(temp, visited.get(temp)-1);
                    count --;
                    left += len;
                }
                
            }
            
        }
        
        return result;
   }
}
