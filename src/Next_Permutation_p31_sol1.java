/*
31. Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 ¡ú 1,3,2
3,2,1 ¡ú 1,2,3
1,1,5 ¡ú 1,5,1
*/

/**
 * Math solution
 * 
 * There is a well-known algorithm to generate next permutation
 * Step 1. Find the longest non-increasing suffix in the input
 * like 4321 in input 14321 or 311 in input 1311 are all longest non-increasing suffix in the input
 * 
 * Step 2. Identify the pivot cell, which is the cell just before longest non-increasing suffix
 * (we may not have pivot cell if input is the largest permutation already)
 * like 1 in 14321 or 1 in 1311 are all pivot cell
 * 
 * Step 3. Find the rightmost cell in longest non-increasing suffix that > pivot
 * (since suffix is non-increasing, rightmost cell here also implies the smallest cell that > pivot)
 * like 2 in 14321 or 3 in 1311 are all rightmost cells that > pivot
 * 
 * Step 4. Swap pivot and rightmost cell
 * (we put the smallest number before longest non-increasing suffix while still meet the permutation requirement)
 * like 24311 or 3111
 * 
 * Step 5. reverse the longest non-increasing suffix to make suffix to be the increasing suffix
 * (since updated suffix is increasing (may have equal cells), it is the smallest suffix for the input)
 * like 21134 or 3111
 * 
 * 21134 is the next permutation of 14321
 * 3111 is the next permutation of 1311
 * 
 * Time complexity: O(N), as reverse and searching i and j are all linear
 * Space complexity: O(1) as we don't need extra space
 * 
 * @author hpPlayer
 * @date May 10, 2016 10:48:34 PM
 */
public class Next_Permutation_p31_sol1 {
    public void nextPermutation(int[] nums) {
        //math solution, found pivot the the rightmost cell that > pivot, then swap them and reverse the tail
        
        //i is the first cell in longest non-increasing suffix
        //i - 1 is the pivot that we are looking for
        int i = nums.length - 1;
        
        //updating the longest non-increasing suffix
        while(i > 0 && nums[i] <= nums[i-1]) i--;
        
        //if i is 0, i.e. we have no pivot, then the given input is the largest permutation, the next permutation
        //would be the start permutation
        if(i == 0){
            reverse(0, nums);
            return;
        }
        
        //j is the rightmost cell in longest non-increasing suffix that > pivot (smallest cell > pivot as well,
        //since suffix is non-increasing)
        int j = nums.length - 1;
        while(j > i - 1 && nums[i-1] >= nums[j]) j--;
        
        //swap nums[j] and nums[i-1]
        swap(i-1, j, nums);
        
        //reverse suffix to make the smallest suffix
        reverse(i, nums);
    }
    
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(int i, int[] nums){
        //since we may need to reverse part of nums[], we have to specify the start index
        int start = i, end = nums.length - 1;
        while(start < end){
            swap(start, end, nums);
            start++;
            end--;
        }
    }
}
