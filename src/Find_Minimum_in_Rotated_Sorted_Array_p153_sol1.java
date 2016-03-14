/*
153. Find Minimum in Rotated Sorted Array

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

/**
 * Binary search solution
 * 
 * We observe that if the array is sorted, then we just need to return nums[start]
 * If the array is rotated, then the smallest number must in the rotated part, otherwise the array should be sorted
 * So our approach it to use binary-search in rotate part to find the rotate part until we find the min element or
 * we find curr range of array is sorted
 * 
 * 
 * Time complexity: O(logN)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 13, 2016 10:01:26 PM
 */
public class Find_Minimum_in_Rotated_Sorted_Array_p153_sol1 {
    public int findMin(int[] nums) {
        //binary search solution. if curr array is not rotated, then we just need to return the leftmost cell
        //If curr array is rotated, then we will use binary search to find the rotated part 
        //We observe that if the array is rotated, then the min number will always be in the rotated part,
        //otherwise we wouldn't have rotation affect. Comparing 12345 vs 45123
        
        int start = 0, end = nums.length - 1;
        
        //binary search requirement: 1) curr range of array is valid i.e. start < end 2) curr range of array is rotated
        while(start < end && nums[start] > nums[end]){
            int mid = start + (end - start)/2;
            
            //we will compare nums[mid] with nums[end], since the array does not contain duplicate, we will only
            //have two cases. By contrast, if we use nums[start], then we may have the case nums[start] = nums[mid]
            //(two cell array)
            if(nums[mid] > nums[end]){
                //right part is rotated, search right, skip mid
                start = mid + 1;
            }else if(nums[mid] < nums[end]){
                //right is sorted, left part is rotated, search left, include mid
                end = mid;
            }
        }
        
        //in binary search we will return nums[start] = nums[end]
        //but now we will also exit the loop when the array is sorted where nums[start] < nums[end]
        //so we return nums[start] for safety
        return nums[start];
     }
}
