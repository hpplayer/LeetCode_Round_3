/*
88. Merge Sorted Array

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
The number of elements initialized in nums1 and nums2 are m and n respectively.
*/

/**
 * Two pointer solution
 * 
 * We fill the merged array backward from large to small, so that we don't need to swap cells
 * 
 * Time complexity: O(m+n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 5, 2016 1:40:12 PM
 */

public class Merge_Sorted_Array_p88_sol1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //two pointers solution, we fill the merged array backward from large to small
        
        //the last index of merged array will be m + n - 1
        for(int i = m + n - 1; i >= 0; i--){
            //to make the code clean, we will handle boundary case that one array inside the same loop
            
            //since we merge the array backward from large to small, if one array has reached the boundary, we can use int_min to fill the array
        	//so we will not pick this value. Also we use m to retrieve cell, but m is size remaining, to get the index we need to - 1
            int num1 = m <= 0? Integer.MIN_VALUE : nums1[m-1];
            int num2 = n <= 0? Integer.MIN_VALUE : nums2[n-1];
            
            //pick the one with larger value
            if(num1 > num2){
                nums1[i] = num1;
                m--;
            }else{
                nums1[i] = num2;
                n--;
            }
        }
    }
}
