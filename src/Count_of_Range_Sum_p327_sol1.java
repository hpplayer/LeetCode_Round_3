import java.util.*;

/*
327. Count of Range Sum

Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ¡Ü j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
*/


/**
 * Divide and conquer solution
 * 
 * The naive scanning sum array solution costs O(n^2) time, and will give TLE error
 * This solution reduce the time complexity to O(nlogN) by using divide-and-conquer aka merge-sort solution
 * 
 * here is the idea:
 * 
 * we firstly convert the nums[] to a sums[] with n + 1 length, so that we can easily calculate the sum of subarray by deducting the sum
 * Then we recursively split the sums[] by half until we reach the bottom, which is left >= right - 1, i.e. only two sums left 
 * Then we scan the left subarray and try to find the range in right subarray which define the boundary of given range. So that we only 
 * need to find two boundary sums instead scan all ranges in both subarray. We iteratively do this to all cells in left part.
 * The next step is updating the count for current two subarrays and merge them by using a temp[]. To avoid unnecessary merge action, we 
 * use a pointer to keep track of all sums in right part that are less than left part. For the remaining part in right part, we don't 
 * need to move them as they are already in right places.
 * lastly we just need to return the count of whole array which will be our answer
 * 
 * The time complexity will be O(NLogN). As we have logN layers during merge and split and at most N cells to merge in each layer
 * 
 * Remark:
 * This problem is similar to problem Count_of_Smaller_Numbers_After_Self_p315_sol1
 * 
 * @author hpPlayer
 * @date Jan 23, 2016 10:51:54 PM
 */
public class Count_of_Range_Sum_p327_sol1 {
	public static void main(String[] args){
		int[] nums = {-2, 5, -1};
		System.out.println(new Count_of_Range_Sum_p327_sol1().countRangeSum(nums, -2, 2));
	}
    public int countRangeSum(int[] nums, int lower, int upper) {
        //precompute the sums of each subarray
        //we need an array with len(nums) + 1 so we can include the sum of total array as well
        //In other words, with len(nums) + 1, we can use first dummy head sum, which is 0, to get the sum of total array
    	//Use long[] to handl extreme large sum case
        long[] sums = new long[nums.length + 1];
        
        for(int i = 0; i < nums.length; i++){
            sums[i + 1] = sums[i] + nums[i];
        }
        
        return mergeSort(sums, 0, sums.length, lower, upper);
    }
    
    public int mergeSort(long[] sums, int left, int right, int lower, int upper){
        //With the extra cell in head, we can always split the array at index (start + end)/2
        //Each subpart can include the cell at this index.
        //To avoid the stack overflow problem and count mid index only once, we will begin return and merge 
        //subarrays once start + 1 >= end
    	//In other words, the boundary case is we only have two sums in the subarray
        if(left + 1>= right) return 0;
        
        int mid = left + (right - left)/2;
        
        int count = mergeSort(sums, left, mid, lower, upper) + mergeSort(sums, mid, right, lower, upper);
        
        //for current range from start to end, we will summary the range by pairing left and right part
        //using pointer a to look for the first cell in right part that gives sums[right] - sums[left] >= lower
        //using pointer b to look for the first cell in right part that gives sums[right] - sums[left] > upper
        //using pointer c to look for all cells that < current cell in left part, so we can merge them later
        int a = mid, b = mid, c = mid;
        
        //we will use temp[] to help merge subarrays, index is the pointer in temp[]
        long[] temp = new long[right-left];        
        int index = 0;
        
        //scan left part and try to pair with right part
        for(int i = left; i < mid; i++){
            //look for the first cell in right part that gives delta_sum >= lower
            while(a < right && sums[a] - sums[i] < lower) a++;
            //look for the first cell in right part that gives delta_sum > upper
            while(b < right && sums[b] - sums[i] <= upper) b++;
            //look for all cells that < current cell in left part
            while(c < right && sums[c] < sums[i]) temp[index++] = sums[c++];
            //include sums[i] to temp[]
            temp[index++] = sums[i];
            //update count
            count += b - a;
        }
        
        //merge subarrays with the help of temp
        //we only merge the subarray of length (index - start), so that we will not move cells that are larger
        //than the largest cell in left part, which are already in their right places
        
        System.arraycopy(temp, 0, sums, left, c - left);
        
        return count;
    }
}
