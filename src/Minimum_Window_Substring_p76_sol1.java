/*
Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

*/

/**
 * a classic sliding window problem
 * 
 * We firstly count the chars in string t, then we scan the string s. During the scan, we keep moving the right boundary of 
 * sliding window, if we found current window has contained all chars in t, then we can move left boundary to reduce the win
 * size if possible. Our left boundary can skip all redundant chars and chars not in t. Finally, we return min sliding window
 * we got during the scan.
 * 
 * Time complexity: 
 * We scan string t once, and scan string s once.
 * Both the left and right pointers in string scan advance at most N steps (where N is S¡®s size) in the worst case, adding to
 * a total of 2N times. Therefore, assume string s is longer than string t, then the run time complexity must be in O(N).
 * Space complexity: O(1) constant, since we only use two count[]s which are 256 in len
 * 
 * This solution is similar to solutions:
 * 1) Substring_with_Concatenation_of_All_Words_p30_sol1
 * 2) Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol1
 * 
 * 
 * @author hpPlayer
 * @date Nov 3, 2015 8:38:26 PM
 */
public class Minimum_Window_Substring_p76_sol1 {
    public String minWindow(String s, String t) {
        //char appears in t
        int[] expected = new int[256];
        //char appears in s
        int[] real = new int[256];
        
        //initialize expected[]
        for(int i = 0; i < t.length(); i++){
            expected[t.charAt(i)]++;
        }
        
        //minimum window info
        int minLeft = 0;
        int minSize = Integer.MAX_VALUE;
        
        //current window info
        int left = 0;
        
        //chars appears in s that also appears in t
        int count = 0;
        
        //scan string s
        for(int right = 0; right < s.length(); right++){
            //we skip chars that not appear in t
            if(expected[s.charAt(right)] == 0) continue;
            
            //otherwise we increase the char count in real[]
            real[s.charAt(right)] ++;
            
            //if current increment make us approach closer to t, we increase the count
            if(expected[s.charAt(right)] >= real[s.charAt(right)]){
                count ++;
            }
             
            //if chars in t all appear in s, then we can begin move left boundary to shrink the window size 
            if(count == t.length()){
                //we move left boundary until two boundary meet(size: 0) if we have redundant chars in s or 
                //current char does not appear in t
                while(left < right &&  (expected[s.charAt(left)] == 0 || real[s.charAt(left)] > expected[s.charAt(left)] )){
                    
                    //decrease the char count, then move left pointer
                    real[s.charAt(left)] --;
                    left ++;
                }
                
                //then we update minimumn window if necessary
                if(right - left + 1 < minSize){
                    //right and left are both 0 based index, so we need + 1 to convert to 1 based length 
                    minSize = right - left + 1;
                    minLeft = left;
                }
            }
        }
        
        
        if(count != t.length()){
            //we return "" if we have not enough chars in s that can compose string t
            return "";
        }
        
        //otherwise return substring in s based on minSize and minLeft
        return s.substring(minLeft, minLeft + minSize);
    }
}
