import java.util.*;

/*
89. Gray Code

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of
gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

/**
 * Observation solution
 * 
 * We observe that two consecutive n follow a pattern in output
 * 
 * outputs of n + 1 is actually output of n and a new set of output by adding 1 to the head of outputs of n
 * reading backward
 * ex:
 * grey code of 1 is 
 * 0
 * 1
 * grey code of 2 is 
 * 0
 * 1
 * 11
 * 10
 * 
 * First part of 2 is same with 1
 * Second part of 2 is built by adding 1 to the head, and reading backward
 * 
 * So in this solution, we just need to build the output backward from the base case 0 to the given n
 * 
 * Time complexity: recursion, exponential, probably O(2^n)
 * Space complexity: same as above
 * 
 * Sol1 is recursive solution
 * Sol2 is iterative solution
 * 
 * @author hpPlayer
 * @date May 17, 2016 11:40:23 PM
 */
public class Gray_Code_p89_sol1 {
    public List<Integer> grayCode(int n) {
        //Observation solution. We build output of curr n based on output of n - 1
        
        //base case
        if(n == 0){
            List<Integer> result = new ArrayList<Integer>();
            result.add(0);
            return result;
        }
        
        //get output of n - 1
        List<Integer> result = grayCode(n-1);
        
        //first part of current output is same with n-1
        //second part of current output is adding 1 to the head of output of n - 1 while reading backward
        for(int i = result.size() - 1; i >= 0; i--){
            //index of head is n - 1. To put 1 to the head, we just leftshift 1 for (n-1) 
        	//we need leftshift 1 by (n-1) to set head index is also from observation
            result.add( result.get(i) | (1 << (n-1)) );
        }
        
        return result;
    }
}
