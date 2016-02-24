import java.util.Arrays;
import java.util.Random;

/*
324. Wiggle Sort II

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

/**
 * QuickSort + 3-way partitioning + virtual array solution
 * 
 * This problem is actually the combination of Kth_Largest_Element_in_an_Array_p215_sol1 and Sort_Colors_p75_sol1, plus a virtual 
 * array conversion problem
 * 
 * The basic idea is to assign large numbers in odd indexes and small numbers to even indexes. 
 * We firstly use Kth_Largest_Element_in_an_Array_p215_sol1 method to find the median in O(n) time
 * Then we use Sort_Colors_p75_sol1 method to sort nums in the virtual array. larger numbers ahead, medians in middle, smaller numbers
 * in tail. In virtual array, its index will be mapped to real array by function A. In virtual array, the first half of indexes will be
 * mapped to odd numbers, so larger numbers will be assigned to odd numbers, second half of indexes will be assigned non-adjacent in
 * real array, smaller numbers will be assigned to even numbers
 * Example:
 * input: 	1 1 1 4 5 6
 * 
 * after process:
 * virtual: 	 0 1 2 3 4 5
 * real:	     1 3 5 0 2 4
 * odd numbers followed by even numbers, so after sort
 * we can put larger number in ahead, which will be assigned to odd numbers
 * we can put smaller number in back, which will be assigned to even numbers
 * as for numbers = median, they will be assigned together in virtual array, but they will be non-adjacency when converted to real
 * array cuz adjacent cells in virtual array have space 1
 * 
 * val(w/o sort):1 4 6 1 1 5
 * after 3-way partitioning sorting
 * val(w sort):  1 1 1 6 5 4
 * 
 * then in real array, we will get:
 * 6 1 5 1 4 1
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * sol2 lists a naive solution with O(NlogN) time complexity and O(N) space complexity
 * 
 * @author hpPlayer
 * @date Feb 14, 2016 11:21:04 PM
 */

public class Wiggle_Sort_II_p324_sol1 {
	public static void main(String[] args){
		//System.out.println( (10|1) );
		int[] nums = {1,5,1,1,6,4};
		//int[] nums = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
		new Wiggle_Sort_II_p324_sol1().wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	
    public void wiggleSort(int[] nums) {
        //we use O(n) method to find the median, then use virtual index to assign values based on the comparison with median
        //we will sort the virtual array with O(n) method from Dutch flag problem
        int median = findMedian(nums);
        len = nums.length;
        
        //sort the nums based on virtual conversion
        //In virtual array A, we want its final result to be larger number, medians, smaller numbers
        //Therefore, we want virtual index in A firstly matched to odd indexes in nums, which should be large
        //then match smaller numbers to even indexes in nums, which should be small
        //As regard to numbers = median, even they are adjacent in virtual array, they will finally assign apart in real array
        //Ex: after sort: In virtual array A we get: L1 L2 L3 M1 M2 M3 S1 S2 S3
        //In real array we will get  M2 L1 M3 L2 S1 L3 S2 M1 S3 where we have odd num > its two even num neighbors  
        int LargeEnd = 0, smallStart = nums.length - 1;
        for(int i = 0; i <= smallStart; i++){
            if(nums[A(i)] > median){
                //put ahead
                swap(nums, A(i),  A(LargeEnd++));
            }else if(nums[A(i)] < median){
                //put tail
                swap(nums, A(i), A(smallStart--));
                i--;
            }
        }
    }
    
    int len;
    
    public int A(int i){
        //nums.length | 1 will give the smallest odd number >= nums.length
        //if calling 0 - n in this virtual array, this conversion will firstly visit odd numbers in nums, then even numbers
        return (1 + 2 * i) % (len|1);
    }
    
    public int findMedian(int[] nums){
        //to handle extreme case, we will firstly shuffle the input array
        Random rand = new Random();
        
        for(int i = nums.length - 1; i > 0; i--){
            int temp = rand.nextInt(i + 1);
            swap(nums, temp, i);
        }
        
        
        int start = 0, end = nums.length - 1;
        while(true){
            int index = quickSort(nums, start, end);
            if(index == nums.length / 2){
                //there are nums.length/2 elements >= nums[index] in front, so we found the median
                return nums[index];
            }else if(index > nums.length / 2){
                //too many large elements in front, we need move end, so pivot will be larger
                end = index - 1;
            }else if(index < nums.length / 2){
                //too less large elements in front, we need move start, so pivot will be smaller
                start = index + 1;
            }
        }
    }
    
    public int quickSort(int[] nums, int start, int end){
        //for convenience, we will choose the first element in current range as pivot
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
                end--;
            }
        }
        
        //finally we swap end with pivot, to put the last element that is >= pivot in front
        swap(nums, index, end);
        
        return end;
    }
    
    public void swap(int[] nums, int start, int end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }     
}
