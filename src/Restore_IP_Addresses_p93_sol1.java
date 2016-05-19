import java.util.*;

/*
93. Restore IP Addresses

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

/**
 * DFS/backtracking solution
 * 
 * We recursively split the input string. Each DFS will handle one segment of the input.
 * A valid IP totally have 4 segments, so we need at least 5 nested DFS to find a valid IP (5th DFS to include
 * generated IP to the result list)
 * 
 * To make the program runs faster:
 * 1) we have a counter to track which segment we are currently in. We will stop parsing the input once
 * count > 4 (assuming 0 based) 
 * 2) we also stop the loop in DFS once we have scanned 3 digits in the input
 * 
 * Time complexity: exponential, but since len of an IP is fixed, our time complexity is actually O(1)
 * Space complexity: same as above, may actually O(1) since len is fixed
 * 
 * Sol1 is recursive version
 * Sol2 is iterative version
 * 
 * @author hpPlayer
 * @date May 18, 2016 10:54:35 PM
 */
public class Restore_IP_Addresses_p93_sol1 {
    public List<String> restoreIpAddresses(String s) {
        //DFS and backtracking solution. Each DFS will parse up to three digits from input
        
        List<String> result = new ArrayList<String>();
        DFS(result, "", s, 0);
        
        return result;
    }
    
    public void DFS(List<String> result, String ip, String s, int count){
        //we have had 4 segments of IP, stop parsing here
        if(count > 4) return;
        
        //we just parsed 4 segments, not we are in the DFS after 4th DFS
        //if all digits in input have been used, then it is a valid IP
        if(count == 4 && s.length() == 0){
            result.add(ip);
            return;
        }
        
        //we will parse 1 - 3 digits from input. we also need to consider the len of remaining input
        for(int i = 1; i <= Math.min(s.length(), 3); i++){
            //handling heading 0s 
            //if curr seg starts with 0, then we can only have 1 digit in curr seg, i.e. only have 0 in curr seg
            if(s.charAt(0) == '0' && i > 1) return;
            
            String seg = s.substring(0, i);
            
            if(!isValid(seg)) return;
            
            //valid seg, adding curr seg into generated ip string
            String temp = count == 0? seg : ip + "." + seg;
            DFS(result, temp, s.substring(i), count + 1);
        }
    }
    
    public boolean isValid(String s){
        //we will handle heading 0s in DFS
        return Integer.valueOf(s) < 256;
    }
}
