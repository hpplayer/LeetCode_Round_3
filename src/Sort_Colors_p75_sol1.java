/*
75. Sort Colors

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors
in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
		
*/

/**
 * Three-way partition sort problem
 * 
 * Famous Dutch flag problem
 * 
 * Time complexity: O(n)
 * Space complexity: O(1) (in-place)
 * 
 * @author hpPlayer
 * @date Feb 15, 2016 2:23:47 PM
 */
public class Sort_Colors_p75_sol1 {
    public void sortColors(int[] nums) {
        int zeroEnd = 0, twoStart = nums.length - 1;
        
        for(int i = 0; i <= twoStart; i++){
             if(nums[i] == 0){
                 swap(zeroEnd++, i, nums);
             }else if(nums[i] == 2){
                 swap(twoStart--, i, nums);
                 i--;
             }
        }
        
    }
    
    public void swap(int start, int end, int[] nums){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
