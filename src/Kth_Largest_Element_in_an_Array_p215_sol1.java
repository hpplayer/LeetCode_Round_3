import java.util.*;

/*
215. Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ¡Ü k ¡Ü array's length.
*/

/**
 * Quick Sort solution
 * 
 * In this solution we use quickSort to sort the input array. The advantage of quickSort is that we can return result immediately, once we have sort
 * and get k largest number ahead. 
 * To handle extreme case and give a more universal solution, we will shuffle the array in the beginning with Fisher¨CYates shuffle method
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Sol2 provides a max Heap solution
 * 
 * @author hpPlayer
 * @date Feb 13, 2016 12:12:05 AM
 */

public class Kth_Largest_Element_in_an_Array_p215_sol1 {
	public static void main(String[] args){
		int[] nums = {3, 1, 2, 4};

		System.out.println(new Kth_Largest_Element_in_an_Array_p215_sol1().findKthLargest(nums, 2));
	}
	
    public int findKthLargest(int[] nums, int k) {
        //modified quickSort solution, we will return result once we found it
        
        //first of all, we will shuffle the array to make our solution more stable (eliminate extreme case)
        int n = nums.length - 1;
        Random rand = new Random();
        for(int i = n; i > 0; i--){
        	//get an random index among 0 and i both inclusive
            int temp = rand.nextInt(i + 1);
            swap(nums, temp, i);
        }
        
        //then use quickSort to sort the array, we will stop the loop once we found k num of largest val ahead
        int start = 0, end = n;
        while(true){
            int pivot = quickSort(start, end, nums);
            if(pivot == k - 1){
                //k is 1 based while pivot is 0 based, so we need - 1
                //found kth largest num, return result
                return nums[pivot];
            }else if(pivot < k - 1){
                //previous pivot was too large, we need search smaller numbers 
                //exclude pivot as it is not our target answer
                start = pivot + 1;
            }else if(pivot > k - 1){
                //previous pivot was too small, we need search larger numbers
                //exclude pivot as it is not our target answer
                end = pivot - 1;
            }
        }
    }
    
    public int quickSort(int start, int end, int[] nums){
        //for convenience, we always choose nums[start] as pivot, and sort nums from start + 1 to end
        int pivot = nums[start];
        int index = start;
        start++;
        
        while(start <= end){
            if(nums[start] < pivot && nums[end] > pivot){
                swap(nums, start, end);
                start++;
                end--;
            }else if(nums[start] >= pivot){
                start++;
            }else if(nums[end] <= pivot){
                end --;
            }
        }
        
        //we need a large number come to front, so pick the last number >= pivotal, which is pointed by "end" 
        swap(nums, index, end);
        return end;
    }
    
    
    
    public void swap(int[] nums, int start, int end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
