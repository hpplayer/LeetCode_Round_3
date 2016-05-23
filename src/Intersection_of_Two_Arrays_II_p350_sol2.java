import java.util.*;
/**
 * Two pointer + binary search solution
 * 
 * Similar to sol1. We will sort inputs first, then using pointers in inputs to search for the intersect 
 * But in sol2, we will use binary search to move pointer.
 * 
 * Time complexity:
 * O(nlgn + mlgm + max(m, n))
 * Sort: O(nlgn + mlgm)
 * Move pointer: O(max(m, n)), if no intersect exists, then we have to scan the whole list
 * (This algorithm should work better if intersect contains more duplicate elements)
 * 
 * Space complexity: O(max(m, n)), as we don't need extra space 
 * 
 * @author hpPlayer
 * @date May 22, 2016 10:52:24 PM
 */
public class Intersection_of_Two_Arrays_II_p350_sol2 {
	public static void main(String[] args){
		int[] nums1 = {1, 2, 2, 1};
		int[] nums2 = {2, 2};
		System.out.println(Arrays.toString(new Intersection_of_Two_Arrays_II_p350_sol2().intersect(nums1, nums2)));
	}
    public int[] intersect(int[] nums1, int[] nums2) {
        //two pointer + binary search solution
        
        //To apply binary search, we have to sort inputs first
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> list = new ArrayList<Integer>();
        
        for(int i = 0, j = 0; i < nums1.length && j < nums2.length; i++){
            //search for the first index of this num in nums2
            int firstIndexInNums2 = binary_search(nums2, j, nums1[i]);
            //search for the last index of this num in nums1
            int LastIndexInNums1 = binary_search(nums1, i, nums1[i] + 1)-1;
            
            //if all inputs are smaller than nums1[i], then firstIndexInNums2 will be out of boundary
            if(firstIndexInNums2 >= nums2.length || nums2[firstIndexInNums2] != nums1[i]){
                //no such element in nums2, we will update next search range in two inputs
                i = LastIndexInNums1;
                j = firstIndexInNums2;
                continue;
            }
            
            //search for the first index of next num in nums2
            int lastIndexInNums2 = binary_search(nums2, firstIndexInNums2, nums1[i] + 1);
            
            for(int k = 0; k < Math.min(lastIndexInNums2 - firstIndexInNums2, LastIndexInNums1 + 1 - i); k++){
                list.add(nums1[i]);
            }
            
            //update indexes in two inputs
            i = LastIndexInNums1;
            j = lastIndexInNums2;
        }
        
        int[] result = new int[list.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = list.get(i);
        }
        
        return result; 
    }
    
    public int binary_search(int[] nums, int start, int target){
        //this binary search will always return the lower bound of target range if we have several target duplicates
        int end = nums.length - 1;
        
        while(start <= end ){
            int mid = start + (end - start)/2;
            //Since our binary search is bias towards left and we want return the first of target duplicates,
            //we need to move end pointer and push it to the first target duplicate, therefore we may finally
            //push it out of boundary return, so we will return start pointer in the end. 
            if(nums[mid] < target ){
                //too small
                start = mid + 1;
            }else{
                //move end pointer when too large or equal, so we can get closer to first target 
                end = mid - 1;
            }
            
        }
        
        return start;
    }
}
