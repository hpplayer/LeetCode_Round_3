/**
 * DP + observation solution
 * 
 * The basic idea is same to sol1, but now we use technique same to rolling row to update the array
 * We use variable code1 to represent dp[i-1], and use variable code2 to represent dp[i-2]
 * We use these two variables along with current char to update current dp[i]. 
 * After found dp[i], we update code1 and code2 and move forward
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 11:38:57 AM
 */
public class Decode_Ways_p91_sol2 {
	public static void main(String[] args){
		String s = "10";
		System.out.println(new Decode_Ways_p91_sol2().numDecodings(s));
	}
    public int numDecodings(String s) {
        //DP + observation solution 2. In this solution, we use similar technique to "rolling row" to reduce space complexity to O(1)
        
        //boundary check
        if(s.length() == 0) return 0;
        
        //code 1 is the code of prev char i.e. dp[i-1]
        int code1 = s.charAt(0) == '0'? 0 : 1;
        //code 2 is the code of second prev char i.e. dp[i-2]
        int code2 = 0;
        
        for(int i = 1; i < s.length(); i++){
            //temp is decode ways for current index
            int temp = 0;
            if(s.charAt(i) != '0') temp = code1;
            //if current char can be decoded by merging with prev char
            //if index is not in bounary: we add code2 or dp[i-2] to temp
            //if index is in boundary: then we need add 1 to temp
            if( isValid(s.substring(i-1, i+1))) temp += (i - 2 >= 0)? code2 : 1;
            
            //we have done current index, need move forward
            //then we update backup value in i- 2 to i - 1
            code2 = code1;
            //we update backup value in i - 1 to i 
            code1 = temp;
        }
        
        //since code1 = temp, when loop exits, code1 will be the dp value in last number
        return code1;
    }
    
    public boolean isValid(String s){
        //if input starts with 0, then return false
        if(s.charAt(0) == '0') return false;
        return Integer.valueOf(s) < 27;
    }
}
