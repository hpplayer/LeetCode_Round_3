/**
 * Divide-and-Conquer solution
 * 
 * We find the mid cell and recursively divide the array into half and half. Then the max sum may come from one of the three places:
 * a subarray in left alone, a subarray extended from mid cell, that has partial cells in left and right, or a subarray in right alone
 * We use recursion to get the max sum from left part and right part alone. Then extend from mid cell to get the max sum in the consecutive subarray
 * Then return the max one from those three candidates
 * 
 * Time complexity: Similar to binary search solution, so the time complexity is O(NlogN)
 * Space complexity: at most our stack will be O(logN)
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 10:43:57 AM
 */
public class Maximum_Subarray_p53_sol2 {
    public int maxSubArray(int[] nums) {
        //divide-and-conquer solution, similar to binary-search, each time we divide the subarray into three parts, mid cell, the part
        //in left and the part in right. maxSum may come from left part, left + mid + right part or from right part
        return DFS(0, nums.length - 1, nums);
    }
    
    public int DFS(int start, int end, int[] nums){
        //reach boundary
        if(end < start) return Integer.MIN_VALUE;
        
        //get the mid index
        int mid = start + (end - start)/2;
        
        //max value from left part, not including mid cell
        int left_max = DFS(start, mid - 1, nums);
        //max value from right part, not including mid cell
        int right_max = DFS(mid + 1, end, nums);
        
        //now we need to get the max value from left+mid+right part
        int currSum = 0;
        //variable to record the max value from mid to left, not including mid cell
        int mid_to_left_max = 0;
        for(int i = mid - 1; i >= start; i--){
            currSum += nums[i];
            mid_to_left_max = Math.max(currSum, mid_to_left_max);
        }
        
        currSum = 0;
        //variable to record the max value from mid to right, not including mid cell
        int mid_to_right_max = 0;
        for(int i = mid + 1; i <= end; i++){
            currSum += nums[i];
            mid_to_right_max = Math.max(currSum, mid_to_right_max);
        }        
        
        
        //return the max value, which may come from left part alone, or from left-mid-right part, or from right part alone
        return Math.max( mid_to_left_max + nums[mid] + mid_to_right_max, Math.max(left_max, right_max));
    }
}
