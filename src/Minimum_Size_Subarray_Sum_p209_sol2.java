/**
 * Sliding window + binary search
 * 
 * We create an array to record the accumulated sum of array
 * Then we use a loop to extend the right boundary of window. If we found current window has sum >= s, then we will use binary search to move the 
 * left boundary to the as right as possible. How? since inputs are all positive, the sum will be accumulated. So we just need to find the last index j
 * that has sums[j] < sums[right] - s, in other words, we want make sums[j] as large as possible when satisfying <= sums[right] - s; Then, the left boundary
 * of window will be the index next to j
 * 
 * Time complexity: O(NlogN), in case each cell in sums array satisfy the requirement, then we need do binary search for each index 
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 4, 2016 11:05:24 AM
 */
public class Minimum_Size_Subarray_Sum_p209_sol2 {
    public int minSubArrayLen(int s, int[] nums) {
        //binary search version of sliding window. Using an accumulated sum array is a common method in the subarray sum problem.
        //we create the an extra array that records the accumulated sum, then we can reduce the sums to get the target sum
        //for accumulated sum at index i, if we found the sum value >= s, then we will try to move the left boundary to the rightest possible 
        //Since the input only contains positive integers, we are guaranteed the sum is ascending. So we will find the index j in sum array
        //that gives sums[j] = sums[i] - s or largest j that sum[j] < sums[i] - s, which means j is rightest possible, therefore our 
        //sliding window will be the min
        
        
        
        //boundary check 
        if(nums.length == 0) return 0;
        
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            sums[i] = sums[i-1] + nums[i];
        }
        
        int result = Integer.MAX_VALUE;
        int left = 0;
        
        for(int i = 0; i < sums.length; i++){
            //i is the right boundary of sliding window
            if(sums[i] >= s){
                //found a valid window that has sum >= s, now we need move left boundary to make the window smallest possible
            	//we are going to find the largest subarray sums[j] that makes sums[i] - sums[j] >= s
            	//We require sums[j] <= sums[i] - s, and the left boundary of win would just be the index after j
            	//in case we have sums[j] = sums[i] - s, then we will return j + 1 as the left boundary of new win
            	//in case we don't have sums[j] = sums[i] - s in the array, then we will return right + 1 in the binary search
            	//which is just left. Thats why we return left in the binarySearch instead of right
                left = binarySearch(left, i, sums[i] - s, sums);
                //update result if possible
                result = Math.min(result, i - left + 1);
            }
        }
        
        return result == Integer.MAX_VALUE? 0 : result;
    }
    
    public int binarySearch(int left, int right, int target, int[] sums){
        //in this binary search program, we will find the left boundary of sliding window
        
        while(left <= right){
            int mid = left + (right - left)/2;
            
            if(sums[mid] == target){
                //found the subarray sum a that gives sums[i] - s, so the left boundary of sliding window must be mid + 1
                //otherwise sliding window would not have sum >= s
                return mid + 1;
            }else if(sums[mid] < target){
                //too small
                left = mid + 1;
            }else{
                //too big
                right = mid - 1;
            }
        }
        
        //now left points to the left boundary of slidng window
        //and right points to the index just before the left boundary of sliding window
        return left;
    }
}
