/*
154. Find Minimum in Rotated Sorted Array II

Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/

/**
 * Binary search solution
 * 
 * We still directly return the nums[start] if array if sorted
 * But this time rotated array may include the case that head and tail may be the same, like[3, 3, 4, 3, 3]
 * In such case, we can't decide which part left or right is rotated, therefore we treat it as a special rotated
 * case and still need use binary search to find next rotated array.
 * 
 * We still compare nums[mid] with nums[end]. 
 * In case that nums[mid] = nums[end], we will skip two boundary cells, since we have nums[mid] in middle
 * we can safely skip nums[end] while still keep a same value in mid index. For nums[start], if nums[start] = nums[end],
 * then nums[mid] can also be a backup value for nums[start], if nums[start] > nums[end], then we also need to skip nums[start]
 * 
 * Time complexity: O(N), in worst case all cells have same value, therefore we need to scan each cell once
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 13, 2016 10:09:40 PM
 */

public class Find_Minimum_in_Rotated_Sorted_Array_II_p154_sol1 {
    public int findMin(int[] nums) {
        //binary search solution. This time we will include nums[start] = nums[end] as a special form of rotated array
        //we will handle special case that nums[end] == nums[mid] in the binary search 
        
        int start = 0, end = nums.length - 1;
        
        //we will end the loop as long as we found the target start == end
        //or we found a sorted array where nums[start] < nums[end]
        
        while(start < end && nums[start] >= nums[end]){
            int mid = start + (end - start)/2;
            
            //we still compare nums[end] with nums[mid], so we wouldn't consider the special case that
            //nums[mid] = nums[start] when there are only two cells left
            if(nums[mid] > nums[end]){
                //rotated array in right, search right and skip mid
                start = mid + 1;
            }else if(nums[mid] < nums[end]){
                //rotated array in left, search left and include mid
                end = mid;
            }else if(nums[mid] == nums[end]){
                //special case, we can't decide rotated part, we just skip two boundary cells
                //we skip leftmost cell since nums[start] >= nums[end], we skip rightmost cell since
                //nums[mid] == nums[end], and we have a backup value of nums[end] in mid index
                
                start++;
                end--;
            }
        }
        
        //we should exit the loop either because we found target nums[start] == nums[end]
        //or because the array is sorted where nums[start] < nums[end], therefore return nums[start] is more safe
        return nums[start];
    }
}
