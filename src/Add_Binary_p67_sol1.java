/*
67. Add Binary

Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

/**
 * Math solution
 * 
 * We use two indexes to visit two inputs backward, and use a variable to record the carry from previous indexes. We will build the result string backward.
 * In the last, outside the loop, we also need to check if carry is 0. If it is, then we return result, otherwise we need add carry to current result
 * 
 * Time complexity: O(N), where N is length of longer input string
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 5:18:38 PM
 */

public class Add_Binary_p67_sol1 {
    public String addBinary(String a, String b) {
        //math solution, we read inputs backward and also build result string backward.
        //we use indexes to read inputs and use an extra variable to record the carry from previous indexes
        
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;
        
        StringBuilder sb = new StringBuilder();
        
        while(i >= 0 || j >= 0){
        	//we read input strings backward 
            int num1 = i >= 0? a.charAt(i--) - '0' : 0;
            int num2 = j >= 0? b.charAt(j--) - '0' : 0;
            
            //we use insert(0, n) to build result string backward
            //build result value using "%2"
            sb.insert(0, (num1 + num2 + carry)%2);
            //update carry using "/2"
            carry = (num1 + num2 + carry)/2;
        }
        
        //before return result don't forget to check if carry still > 0
        if(carry > 0) sb.insert(0, carry);
        
        return sb.toString();
    }
}
