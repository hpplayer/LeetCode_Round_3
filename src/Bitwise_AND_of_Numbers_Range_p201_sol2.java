/**
 * Bit manipulation solution
 * 
 * The basic idea is similar to sol1, but now we rightshift m and n to get the common prefix. We also needs to record
 * the nums of shift we used during the process. Then we will create the target number by leftshift the common prefix.
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 31, 2016 11:37:36 PM
 */
public class Bitwise_AND_of_Numbers_Range_p201_sol2 {
    public int rangeBitwiseAnd(int m, int n) {
        //we rightshift both m and n until the remaining part is same
        
        //counter we used to record the num of shifts we used
        //then we will use this counter to build the target number
        int count = 0;
        
        while(m != n){
            //leftshift m and n to find the common prefix
            m >>= 1;
            n >>= 1;
            count++;
        }
        
        //then build the solution by leftshift the common prefix
        return m << count;
    }
}
