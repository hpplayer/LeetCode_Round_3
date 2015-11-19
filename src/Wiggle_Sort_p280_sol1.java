/*
Wiggle Sort

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/

/**
 * Observation solution
 * 
 * We observe that the number in odd index must larger or equal to its two neighbor cells
 * Accordingly the number in even index must smaller or equal to its two neighbor cells
 * 
 * In this solution, we just check the left value and current value and swap them if they not obey above rules:
 * if index is even but large than prev value
 * or if index is odd but smaller than prev value
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 19, 2015 1:51:58 AM
 */
public class Wiggle_Sort_p280_sol1 {
    public void wiggleSort(int[] nums) {
        //we compare current value with left value
        //if current index is odd, then it must >= left value
        //if current index is even, then it must <= left value
        for(int i = 1; i < nums.length; i++){
            if( ((i&1) == 1 && nums[i] < nums[i-1] ) || ( (i&1) == 0 && nums[i] > nums[i-1]) ){
                //if odd and left value > current value
                //if even and left value < current value
                //then swap
                int temp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = temp;
            }
        }
    }
}
