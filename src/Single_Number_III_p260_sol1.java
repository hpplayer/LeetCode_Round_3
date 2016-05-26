/*
260. Single Number III

Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

/**
 * Bit manipulation solution
 * 
 * 1. We firstly use ^ to get the combination of num1 ^ num2
 * 2. Then we use n &= -n to get the last 1 in the combination, and reset other bits to be 0.
 * It is 1 in xor result, so it means only one of num1 and num2 has a 1 in this index, we just use this as access point to split input
 * 3. We scan input array again, we will split input into two parts, one part has 1 in this index, the other part 
 * does not have 1 in this index. In this way other elements still appear twice, and target element still appears once.
 * So we can get results easily
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Remark:
 * -n is also known as 2's complement -n = ~n + 1
 * n & -n can be used to get the last 1 in n while reseting other 1s 
 * 
 * @author hpPlayer
 * @date May 25, 2016 11:12:44 PM
 */
public class Single_Number_III_p260_sol1 {
    public int[] singleNumber(int[] nums) {
        //Bit manipulation solution. We firstly use xor to get the xor result of output 1 and 2.
        //then use &-n to get the last index in xor result that has 1. Then we use this info to split input and
        //convert this problem to Single Number I
        
        int xor = 0;
        
        //get xor result of output 1 and 2
        for(int num : nums) xor ^= num;
        
        //get the last index in xor result that has 1
        //after &= -xor, only last 1 in xor result will be kept
        xor &= -xor;
        
        int[] result = new int[2];
        
        //we split input into two parts
        //one part has 1 in this index, other part does not have 1 in this index
        //then we can treat each part as a single number I problem
        for(int num : nums){
            if( (num&xor) == 0){
				//this part does not have 1 in that index
				result[0] ^= num;
            }else{
				//this part has 1 in that index. Since num&xor may not necessarily = 1, so we use else to cover this part
				result[1] ^= num;
            }
        }
        
        return result;
    }
}
