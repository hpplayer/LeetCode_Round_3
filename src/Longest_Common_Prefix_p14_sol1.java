/*
14. Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.
*/

/**
 * Math + observation solution
 * 
 * We observe that we can make use of the first string to find the longest common prefix since each input
 * string should contain this prefix which of course includes the first string
 * 
 * We check each char in first string and compare it with corresponding char in following strings. If following
 * strings do not have enough length or the corresponding char is not matched, then it means we found the
 * longest common prefix. Otherwise, we can continue the search.
 * If we can reach the end of first string, then it means the first string itself is the longest common prefix
 * 
 * Time complexity: 
 * O(L * N), where L is the longest string in input and N is the num of input
 * Space complexity: 
 * O(1) as we dont need extra space
 * 
 * @author hpPlayer
 * @date Apr 27, 2016 9:36:47 PM
 */

public class Longest_Common_Prefix_p14_sol1 {
    public String longestCommonPrefix(String[] strs) {
        //string + observation solution. We observe that the condition defined by the problen is actually very strict
        //the common prefix means all strings in input array should have this prefix, which means the length and
        //characters should all be same
        //so We make use of the first string in input array, and checking prefix in first string with late strings
        
        //boundary check
        if(strs.length == 0) return "";
        
        //look at first string in input to find the longest common prefix
        for(int i = 0; i < strs[0].length(); i++){
            //get curr char in first string
            char c = strs[0].charAt(i);
            //all other strings in input should also have this char in this index
            for(int j = 1; j < strs.length; j++){
                //if length is not enough or chars are not matched, then we found the longest common prefix
                if(i >= strs[j].length() || c != strs[j].charAt(i)) return strs[0].substring(0, i);
            }
        }
        
        //otherwise the first string itself is the longest common prefix
        return strs[0];
    }
}
