import java.util.*;

/**
 * Observation solution
 * 
 * In this solution we iteratively build the output of n from the result of n - 1
 * The main part is similar to sol1
 * 
 * Time complexity: exponential, O(n*2^n), where we have n loops, and max size of result list is O(2^n)
 * Space complexity: O(2^n)
 * @author hpPlayer
 * @date May 18, 2016 12:00:08 AM
 */
public class Gray_Code_p89_sol2 {
    public List<Integer> grayCode(int n) {
        //Observation solution. We build the nth output from (n-1)th output
        
        List<Integer> result = new ArrayList<Integer>();
        //base case if n = 0
        result.add(0);
        
        for(int i = 1; i <= n; i++){
            //since we will dynamically change result list, we need to record the size first
            int size = result.size() - 1;
            //first part of n output is same with n-1 output
            //second part of n output is from adding 1 to the head of n-1 output while reading backward
            for(int j = size; j >= 0; j--){
                //reading output of i - 1 backward and adding 1 to the head
                //index of head is just leftshifting 1 by i - 1
                result.add(result.get(j) | (1 << i-1) );
            }
        }
        
        return result;
    }
}
