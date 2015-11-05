/**
 * Binary search solution
 * 
 * In this solution we are looking for an index i in nums1 such that this index will also be the index of median.
 * To get this index, we firstly calculate the index of median if we merge two input arrays. Then we shrink the search range in nums1
 * by putting nums2 in left or right of nums1. Then we use binary search to find the index in nums1. Based on the index in nums1, we can
 * can calculate how many elements we need in nums2 before nums1. Then adjust our search range accordingly.
 * In case we have two median numbers, the median value found in above process will be the second value as we get it from len/2
 * so we just need to compare nums[i-1] and nums[j] to find the first median value
 * 
 * This algorithm costs logM to search nums1, if failed, costs logN to search nums2
 * So the running time would be log(m) + log(n) or log(mn)
 * 
 * Remark:
 * we need to examine each cell in nums1, so the binary search stop condition would be left > right, i.e. we include left == right!!!!!!!!!!!
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 5:34:54 PM
 */
public class Median_of_Two_Sorted_Arrays_p4_sol2 {
	public static void main(String[] args){
		int[] nums1 = new int[10000000];
		int[] nums2 = {1};
		
		System.out.println(new Median_of_Two_Sorted_Arrays_p4_sol2().findMedianSortedArrays(nums1, nums2));
	}
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        //calculate the index of median value if we combine nums1 and nums2
        int mid = (m + n)/2;
        
        boolean odd = ((m + n)&1) == 1;
        
        //assume median is in nums1, let's calcualte its range
        
        //left boundary is putting nums2 on the left of nums1
        //example: [1], [2,3], mid = 1, n = 1, so index 0 in nums1 is median, which is correct 
        int left = Math.max(0, mid - n);
        
        //right boundary is putting nums2 on the right of num1
        //example: [1, 2], [3], mid = 1, so index 1 in nums1 is median, which is correct
        int right = Math.min(m - 1, mid);
        
        //use binary-search to search in calculated range to find the target num
        while(left <= right){
            //i is cursor in nums1[]
            int i = (left + right) /2;
            //j is cursor in nums2[]
            //we need mid - 1 elements before i, now we have got i elements before i
            //so we still need mid - 1 - i elements before j, and we need to look at nums2[mid - 1 -i]
            int j = mid - 1 - i;
            
            //we want to find an "i" that, the corresponding value in other array nums2[j] <= nums1[i]
            //and nums2[j + 1] > nums1[i], so we would have exactly mid - 1 values before nums1[i], which means 
            //nums1[i] will be the median
            
            if(j >= 0 && nums1[i] < nums2[j]){
                //too small
                left = i + 1;
            }else if (j + 1 < n && nums2[j+1] < nums1[i]){
                //too large
                right = i - 1;
            }else{
                //target found
                
                if(odd){
                    //if odd, just return nums1[i]
                    return nums1[i];
                }else{
                    //otherwise we would have two median values
                    //nums1[i] would be the second median value since it is retrived by calculating len/2
                    //so we are looking for the first median value
                    //now we have two candidates: nums1[i-1] and nums2[j]
                    
                    if(j < 0 || j > n){
                        //j is out of range, first median is nums1[i-1]
                        return (nums1[i] + nums1[i-1])/2.0;
                    }else if (i - 1 < 0){
                        //i - 1 is out of range, first median is nums2[j]
                        return (nums1[i] + nums2[j])/2.0;
                    }else{
                        //otherwise we have to take the one that is closer to nums1[i]
                        //it should be the larger value
                        return (nums1[i] + Math.max(nums1[i-1], nums2[j]))/2.0;
                    }
                }
            }
        }
        
        //we can't find ideal i in nums1, so the i must be in nums2, we just swap them and start new search
        return findMedianSortedArrays(nums2, nums1);
    }
}
