import java.util.*;

/*
321. Create Maximum Number

Given two arrays of length m and n with digits 0-9 representing two numbers.
Create the maximum number of length k <= m + n from digits of the two.The relative order of the digits from the same array must be preserved.
Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
		
*/

/**
 * Greedy solution
 * 
 * Here is the basic idea:
 * 
 * We can decompose this problem into three parts:
 * 
 * 1st part is to build the max number from a single array. We just need to check each digit and make sure our number starts with the max digit while
 * we have enough digits left behind.
 * 
 * 2nd part is to merge the result from 1st part from two single arrays and build the max number. It is very similar to merge sort, we always put
 * the larger digit from two arrays in result array. If two numbers are same, then we need to check the digits behind, and put the one with larger
 * digit behind in front.
 * 
 * 3nd part is to enumerate all possible sub-arrays from inputs and merge them as 2nd part then do comparison. We need to try all possible ways
 * to build the max number with k digits. For the split, we also need to consider the numbers of digits. For example if array2 has 3 digits while
 * the input k = 5, then array1 need provide 2 digits no matter the value of digits. So we use Math.max(0, k - nums2.length) to meet this requirement
 * 
 * 
 * Part 1 costs O(n)time
 * Part 2 costs O(mn) since we have to check duplicate before filling result array. In the worst case, we need do this to all digits
 * Part 3 costs O(kmn) time since in the worst case, we need check all k split ways, each way costs O(mn) time to get the merged array.
 * 
 * @author hpPlayer
 * @date Jan 21, 2016 9:50:09 PM
 */

public class Create_Maximum_Number_p321_sol1 {
	   public int[] oneArray(int[] nums, int k){
	        int[] result  = new int[k];
	        
	        for(int i = 0, j = 0; i < nums.length; i++){
	            //as long as we have enough digits left, we always try to put larger digit ahead
	            while(nums.length - i > k - j && j > 0 && nums[i] > result[j-1]) j--;
	            //we will fill current digit in result[] if j < k
	            if(j < k) result[j++] = nums[i];
	        }
	        
	        return result;
	    }
	    
	    public int[] merge(int[] nums1, int[] nums2, int k){
	        int[] result = new int[k];
	        int a = 0, b = 0;
	        for(int i = 0; i < k; i++){
	            //check if current digit in nums1 is larger or nums2 is larger
	            result[i] = isLarger(nums1, a, nums2, b)? nums1[a++] : nums2[b++];
	        }
	        
	        return result;
	    }
	    
	    public boolean isLarger(int[] nums1, int i, int[] nums2, int j){
	        //skip over equal digits (here we just check the array with larger number value, and we don't want to acutally skip digits in later step )
	        while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j]){
	            i++;
	            j++;
	        }
	        
	        //return true if j reaches end of nums2 or curruent nums1[i] > nums2[j]
	        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
	    }
	    
	    
	    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        int[] result = new int[k];
	        
	        //in the case digits in nums2 are not enough, we have to force use certain digits in nums1, which is reflected by k - nums2.length
	        //i is pointer that scan nums1
	        for(int i = Math.max(0, k - nums2.length); i <= nums1.length && i <= k; i++){
	            //check all possible array with k digits
	            int[] temp = merge(oneArray(nums1, i), oneArray(nums2, k - i), k);
	            //update result array, if temp[] is larger
	            if(isLarger(temp, 0, result, 0)) result = temp;
	        }
	        
	        return result;
	    }
}
