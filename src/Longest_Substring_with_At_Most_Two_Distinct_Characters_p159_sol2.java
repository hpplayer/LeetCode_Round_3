/**
 * A variant of sliding window problem
 * 
 * We use three pointer here. left pointer is the index of first distinct char, right pointer is the index of last previous distinct char
 * so it may point to the first distinct char or second distinct char in case we have remaining first distinct chars after second distinct chars.
 * We use third pointer i to scan the string. If we found two consecutive chars are not same, we will check the new char with char at right pointer
 * if they are not same, then we got three distinct chars, so we update window size accordingly and move left pointer skip right pointer
 * Finally, we will return Math.max(result, s.length() - left ) to cover the case that we don't have third char in the tail substring
 * 
 * This algorithm runs in O(n) time and costs O(1) time
 * @author hpPlayer
 * @date Nov 9, 2015 3:03:11 PM
 */
public class Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol2 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        //three pointers solution
        
        int left = 0;
        //right is the last index of previous distinct char before current distinct char
        //like aabbaaa, right pointer will point to last b
        //like aaaaabbbbb, right pointer will point to last a (initial case)
        //like aabbcc, right pointer will point to last b
        //like aaaa, right will still be -1        
        //Initially, we only have one char, so set its initial value to be -1
        int right = -1;
        int result = 0;
        
        for(int i = 1; i < s.length(); i++){
            //we only do things when two consecutive chars are not same
            if(s.charAt(i) == s.charAt(i-1)) continue;
            
            //firstly check if we have two distinct chars
            if(right != -1  && s.charAt(i) != s.charAt(right)){
                //if we have two distinct chars already and new char is the third distinct one
                //then update window size if necessary
                result = Math.max(result, i - left);
                
                //let left pointer skip the last index of previous distinct char
                //so we will build a new window with two distinct chars
                left = right + 1;
            }
            
            //now we get a valid window, we update right pointer to be the last index of previous distinct char
            right =  i - 1;
        }
        
        //we will always calculate the win size when we got the third distinct char
        //but for the tail substring, we may have only two distinct chars, so we need cover this case as well
        return Math.max(result, s.length() - left );
    }
}
