/*
340. Longest Substring with At Most K Distinct Characters

Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = ¡°eceba¡± and k = 2,

T is "ece" which its length is 3.
*/

/**
 * Sliding window solution
 * 
 * This solution is similar to Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol1,
 * We just need to replace "while(distinct > 2)" with "while(distinct > k)"
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 6, 2016 9:36:19 PM
 */
public class Longest_Substring_with_At_Most_K_Distinct_Characters_p340_sol1 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        //Sliding window solution. Use a count table to record the occurrences of each char, and use a distinct
        //variable to record how many distinct chars we have. We use right pointer to scan the input array, and
        //we will move left pointer once distinct count > k 
        
        //count table
        int[] count = new int[256];
        
        //distinct chars counter
        int distinct = 0;
        //left pointer
        int left = 0;
        int result = 0;
        
        for(int right = 0; right < s.length(); right++){
            //if we get another distinct char, update counter
            if( count[s.charAt(right)]++ == 0 ) distinct++;
            
            //if we found distinct counter > k, then we will keep move left pointer until counter == k
            //since distinct starts with 0, when left == right, our count[] will only have 0s, if this is 
            //still not good, then we will move left over right and stop there
            //In short words, we put left <= right here to cover the case that k < 0
            while( left <= right && distinct > k ){
                if(--count[s.charAt(left)] == 0) distinct--;
                left++;
            }
            
            result = Math.max(result,  right - left + 1);
        }
        
        return result;
    }
}
