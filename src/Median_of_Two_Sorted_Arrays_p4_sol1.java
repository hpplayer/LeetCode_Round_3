/*
Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/

/**
 * Math + divide-and-conquer problem
 * 
 * Here we use recursion to shrink the search range. Each time we will discard k/2 elements in inputs. 
 * We will stop the search when we have discarded all elements in one of the input array or k = 1, which means we just need to 
 * take the smaller num in current nums1[start1], nums2[start2]. Start1 and start2 here means the first element of new part
 * in nums1/nums2
 * 
 * Remark:
 * 1.To be convenient, we will use 1 based k instead of 0 based. Otherwise, it would be panic to deal with indexing between two
 * input strings. 
 * For example: [1], [1] in this example, if we using 0 based index, then we will get stuck of k = 1, since k/2 always = 0
 * So instead, we will use 1 based index and search 2th element as the median. 
 * 2. Since k/2 always take the floor value, when pass k value to next recursion, we should use k - k/2 to get the accurate 
 * value left in k
 * 3. Note when k == 1, we should return Math.min(nums1[start1], nums2[start2]), as we are not longer in the start point of 
 * inputs!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * Time complexity: each time we discard k/2 elements and k = (m + n), so the running time would be O(log(m+n))
 * 
 * Sol1 is divide-and-conquer solution
 * Sol2 is binary-search solution
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 3:17:08 PM
 */
public class Median_of_Two_Sorted_Arrays_p4_sol1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //divide and conquer approach
        int len = nums1.length + nums2.length;
        
        if( (len&1) == 1 ){
            //in this approach, we use 1 based index to search k, so we will + 1 to len/2
            //ex: [1] [2, 3], we will search k =2, i.e. the second element in inputs
            return findKthElement(nums1, nums2, 0, 0, len/2 + 1) * 1.0;
        }else{
            return (findKthElement(nums1, nums2, 0, 0, len/2 + 1) + findKthElement(nums1, nums2, 0, 0, len/2)) / 2.0;
        }
        
    }
    
    public int findKthElement(int[] nums1, int[] nums2, int start1, int start2, int k){
        //start1 and start2 are always the first index of unvisited part in nums1 and nums2 
        if(start1 >= nums1.length){
            //we have no place to search in nums1, so the search of kth element will be done in nums2
            //k is 1 based unti, so - 1
            return nums2[start2 + k - 1];
        }
        
        if(start2 >= nums2.length){
            //samve as above
            return nums1[start1 + k - 1];
        }
        
        //we need take one element from nums1/nums2, and we will pick smaller one
        if(k == 1) return Math.min(nums1[start1], nums2[start2]);
        
        int kthElementInNums1 = start1 + k/2 - 1 >= nums1.length? Integer.MAX_VALUE : nums1[start1 + k/2 - 1];
        int kthElementInNums2 = start2 + k/2 - 1 >= nums2.length? Integer.MAX_VALUE : nums2[start2 + k/2 - 1];
        
        if(kthElementInNums1 < kthElementInNums2){
            //next start point in nums1 will be the index after start1 + k/2 - 1, so it is start1 + k/2
            //since k/2 always take floor value, we need use k - k/2 to get remaining value
            return findKthElement(nums1, nums2, start1 + k/2, start2, k - k/2);
        }else{
            return findKthElement(nums1, nums2, start1, start2 + k/2, k - k/2);
        }
        
    }
}
