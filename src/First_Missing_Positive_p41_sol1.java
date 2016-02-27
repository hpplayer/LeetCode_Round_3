/*
First Missing Positive

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

/**
 * Array swap solution.
 * 
 * We need to swap cells in array so that 1 is in index 0, 2 is in index 1, and so on. For negative/extra positive number, we just keep it there, since
 * if they occupied the index of a later positive number, they will be swapped later. Otherwise their index will indicate that number is the first 
 * missing positive
 * 
 * Remark:
 * 1. To cover all cases, we better put 1 in index 0 instead of index 1. Otherwise, if array length is zero, we have to add extra boundary checks
 * Ex: [0] vs [1]!!!!!!!!!
 * 2. We may have duplicate numbers in the array and if they need to be placed in the correct position, then we would get into an infinite loop as
 * after first duplicate is put into the location, the second duplicate will always be in the incorrect position. So we have to do check to avoid
 * such case!!!!!!!!!!!!
 * 3.firstMissingPositive2() convert if block to a while block 
 * 
 * Time complexity: O(n) since each for loop we should put an positive integer into the correct index. Even if we use i -- to recheck
 * the new value swapped to current cell, but that is not a redundant operation since we still put a positive integer into the correct 
 * index. So worst case n positive int in nums in incorrect positions, then we need to do swap O(n) time
 * 
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 14, 2015 1:31:15 AM
 */
public class First_Missing_Positive_p41_sol1 {
    public int firstMissingPositive(int[] nums) {
        //boundary check
        if(nums.length == 0) return 1;
        
        int len = nums.length;
        
        for(int i = 0; i < len; i++){
            //search for positive number n that are not in correct index
            //here we define correct index to be n - 1
            
            //if current num is in valid range not in correct position and do not have duplicates (no same number in target index)
            if(nums[i] > 0 && nums[i] <= len && i != nums[i]-1 && nums[i] != nums[nums[i]-1]){
                //swap correct number to target index
            	//put n to nums[n-1], i.e. put n to index n - 1, which is the correct index for n
                int temp = nums[i];
                nums[i] = nums[nums[i] -1];
                nums[temp-1] = temp;
                //recheck the value swapped into current cell!!!!!!!!!!!!
                i--;
            }
        }
        
        //search for the first missing positive integer.
        //It should be the in the first index that not comply with index = n - 1 
        for(int i = 0; i < len; i++){
            if(nums[i] -1 != i) return i+1;
        }
        
        //if all values in array have correct index, then the first missing positive int would be len + 1
        //ex: [1, 2, 3], return 3 + 1
        return  len + 1;
    }
    
    
    public int firstMissingPositive2(int[] nums) {
        //boundary check
        if(nums.length == 0) return 1;
        
        int len = nums.length;
        
        for(int i = 0; i < len; i++){
            while(nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1] && nums[i] != i + 1){
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
        }
        
        for(int i = 0; i < len; i++){
            if(nums[i] != i + 1) return i + 1;
        }
        
        return len + 1;
    }
}
