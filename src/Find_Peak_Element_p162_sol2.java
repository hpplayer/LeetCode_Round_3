/**
 * Three pass solution
 * 
 * First two pass are used to get the max value from left and right
 * In the third pass, we compare each value with its left max and right max value. If current value > both values, then current value is the peak
 * But we may have special case that input array only have one element, therefore we need include = sign into comparsion as well
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * This approach is very similar to solution Candy_p135_sol1
 * 
 * @author hpPlayer
 * @date Feb 24, 2016 2:41:46 PM
 */
public class Find_Peak_Element_p162_sol2 {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        
        left[0] = nums[0]; 
        right[len - 1] = nums[len - 1]; 
        
        for(int i = 1; i < len; i++){
            left[i] = Math.max(left[i], left[i-1]);
        }
        
        for(int i = len - 2; i >= 0; i--){
            right[i] = Math.max(right[i+1], right[i]);
        }
        
        for(int i = 0; i < len; i++){
            int leftE = i == 0? Integer.MIN_VALUE : nums[i-1];
            int rightE = i == len - 1? Integer.MIN_VALUE : nums[i+1];
            //if our input has a single value, then we would have case that nums[i] == leftE = rightE
            if(nums[i] >= leftE && nums[i] >= rightE) return i;    
        }
        
        throw new IllegalArgumentException();
    }
}
