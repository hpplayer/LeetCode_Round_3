/*
Longest Substring with At Most Two Distinct Characters

Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = ¡°eceba¡±,

T is "ece" which its length is 3.
*/

/**
 * Sliding window with count[] problem
 * 
 * We use a count[] to record the appearance of each char in the window, and we use another variable distinct to record the number of distinct
 * numbers we got in the window. If we found distinct > 2, we will begin move left pointer until we make distinct <= 2. Then we update the max
 * window size if necessary.
 * 
 * This algorithm costs O(n) time as each char will be at most visited twice
 * Space complexity is O(1) as we need a count[] with len 256
 * 
 * Remark:
 * This problem asks us to return the max length of substring that contains at most two distinct characters, which means a valid window
 * should only contain at most 2 distinct chars!
 * 
 * Sol1 is a standard sliding window solution
 * Sol2 is a variant of sliding window solution using three pointers, which is hard to understand, but does not require a count[]
 * 
 * Sol1 is more recommended
 * 
 * @author hpPlayer
 * @date Nov 9, 2015 2:23:27 PM
 */
public class Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol1 {
	public static void main(String[] args){
		String s = "abaac";
		System.out.println(new Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol1().lengthOfLongestSubstringTwoDistinct(s));
	}
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        //assume the input string only contains ASCII chars
        int[] count = new int[256];
        
        int result = 0;
        int left = 0;
        //counter to record how many distinct we have in current window
        int distinct = 0;
        
        //we will move right pointer to include more chars
        for(int right = 0; right < s.length(); right++){
            //we increase the counter for current char at right pointer
            //if this is the first time we meet this char, we increase the distinct counter
            if(count[s.charAt(right)]++ == 0){
                distinct++;
            }
            
            //if we found distinct variable > 2, then we have to move left pointer until we make distinct variable <= 2
            while(distinct > 2){
                //we decrease the counter for each char at left pointer
                //if we make the appearance to be 0, then we can decrease the distinct counter
                if(--count[s.charAt(left)] == 0){
                    distinct --;
                }
                
                left++;
            }
            
            //now we should have a valid window, we update result if necessary
            result = Math.max(result, right - left + 1);
        }
        
        return result;
    }
}
