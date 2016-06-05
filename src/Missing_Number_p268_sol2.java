/**
 * Math solution
 * 
 * Since we know input range is from 0 to n, we can firstly calculated sum, then remove elements in input array from 
 * expected sum. The remaining value would be the missing number
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 9:48:41 PM
 */
public class Missing_Number_p268_sol2 {
    public int missingNumber(int[] nums) {
        //math solution. Firstly get the expected sum, then scan input array, finally we can get missing value
        
        //sum from 0 to n would be (0 + n) * (n+1)/2, here n is nums.length
        int expectedSum = (0 + nums.length) * (nums.length + 1)/2;
        
        for(int num : nums){
            expectedSum -= num;
        }
        
        return expectedSum;
    }
}
