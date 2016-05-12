/*
35. Search Insert Position

Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 ¡ú 2
[1,3,5,6], 2 ¡ú 1
[1,3,5,6], 7 ¡ú 4
[1,3,5,6], 0 ¡ú 0

*/

/**
 * Binary search solution
 * 
 * Just use a general binary search to find the target
 * if target is not found, we need return left pointer as in the end it points to the expected insertion point
 * 
 * Time complexity: O(logN)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 11, 2016 9:38:13 PM
 */
public class Search_Insert_Position_p35_sol1 {
    public int searchInsert(int[] nums, int target) {
        //just a general binary search.
        //if target is found then return index of mid
        //if not, we will keep searching. In the end of loop, left pointer will point to the index after expected
        //index, and right pointer will point to the index before expected index
        
        int left = 0, right = nums.length - 1;
        
        //check each index to find target
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target){
                //too small
                left = mid + 1;
            }else if(nums[mid] == target){
                //found target
                return mid;
            }else{
                //too large
                right = mid - 1;
            }
        }
        
        //loop ends and we still not found result. Now left pointer points to the index after expected insert index
        //right pointer points to the index before expected insert index
        //so we return left pointer
        return left;
    }
}
