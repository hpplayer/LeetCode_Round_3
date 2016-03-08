/*
91. Decode Ways

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

/**
 * DP + observation solution
 * 
 * DP: We use a dp array to record the decode ways in previous indexes.
 * Observation: we need handle "0" specifically. 0 can only be second digit of a number, otherwise we cannot decode it
 * 
 * Here is the basic idea:
 * 
 * We will scan the input string char by char. For char at index i , if it is not "0", then it is a valid code, we just need to copy dp[i-1] to dp[i]
 * Then we check if we char at index i - 1 and at index i can be merged into one code, if it is a valid code, then we add dp[i-2] to dp[i]
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Since we only need previous 2 dp values, we can reduce the space complexity to O(1)
 * See sol2 for such solution
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 11:00:23 AM
 */
public class Decode_Ways_p91_sol1 {
	public static void main(String[] args){
		String s = "012";
		System.out.println(new Decode_Ways_p91_sol1().numDecodings(s));
	}
    public int numDecodings(String s) {
        //dp + observation solution. Use dp array to record previous decode ways, use observation to check if we can merge two digits
        //and if we need to handle special case '0'
        
        //boundary check
        if(s.length() == 0) return 0;
        
        //dp[i] means the decode ways in index i
        int[] dp = new int[s.length()];
        //initialize first cell, we need to check if first char is 0
        dp[0] = s.charAt(0) == '0'? 0 : 1;
        
        for(int i = 1; i < s.length(); i++){
            //decode current digit as single code
            if(s.charAt(i) != '0') dp[i] = dp[i-1];
            //if curr digit with prev digit is also a valid code
            if(isValid(s.substring(i-1, i+1))) dp[i] += (i - 2 >= 0)? dp[i-2] : 1;
        }
        
        return dp[s.length()-1];
    }
    
    public boolean isValid(String s){
        //we will only pass s with two digits into this function
        //if input s starts with 0, then return false
        if(s.charAt(0) == '0') return false;
        
        //we require value < 27
        return Integer.valueOf(s) < 27;
    }
}
